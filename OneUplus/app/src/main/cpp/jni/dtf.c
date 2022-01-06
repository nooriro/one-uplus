#include <stdio.h>
#include <time.h>
#include <stdbool.h>
#include <string.h>
#include <limits.h>
#include <stdlib.h>     // malloc() free()
#include <sys/param.h>  // MIN(a,b) macro

#define MAX_PRECISION 9

#define ERROR_NUMBER_FORMAT_INVALID_SEC  10
#define ERROR_NUMBER_FORMAT_INVALID_NSEC 11
#define ERROR_NUMBER_OUTOFRANGE_ABOVE    12
#define ERROR_NUMBER_OUTOFRANGE_BELOW    13

#define ARRAYSIZE(x)     (sizeof(x) / sizeof((x)[0]))

struct clockpair {
    char *key;
    clockid_t clockid;
};

struct clockpair cps[] = {
        {"real", CLOCK_REALTIME},
        {"mono", CLOCK_MONOTONIC},
        {"pcpu", CLOCK_PROCESS_CPUTIME_ID},
        {"tcpu", CLOCK_THREAD_CPUTIME_ID},
        {"monoraw", CLOCK_MONOTONIC_RAW},
        {"realcoa", CLOCK_REALTIME_COARSE},
        {"monocoa", CLOCK_MONOTONIC_COARSE},
        {"boot", CLOCK_BOOTTIME},
        {"0c", 0}, {"1c", 1}, {"2c", 2}, {"3c", 3},
        {"4c", 4}, {"5c", 5}, {"6c", 6}, {"7c", 7},
        {"8c", 8}, {"9c", 9}, {"10c", 10}, {"11c", 11},
        {"12c", 12}, {"13c", 13}, {"14c", 14}, {"15c", 15}
};

struct precisionpair {
    char *key;
    int precision;
};

struct precisionpair pps[] = {
        {"", MAX_PRECISION}, {"+", -1},
        {"-ns", 9}, {"-us", 6}, {"-ms", 3},
        {"-nano", 9}, {"-micro", 6}, {"-milli", 3},
        {"9", 9}, {"8", 8}, {"7", 7}, {"6", 6}, {"5", 5},
        {"4", 4}, {"3", 3}, {"2", 2}, {"1", 1}, {"0", 0}
};

struct order {
    clockid_t clockid;
    int precision;
    long long prev_sec;
    long prev_nsec;
    struct timespec now;
};

struct config {
    char *sep;
    bool nulsep;
    bool newline;
    bool ultime;
};

void set_default_config(struct config *conf);
int parse_argv(char *argv[], struct order *orders, int *count, struct config *conf);
int parse_command(const char *str, clockid_t *clockid, int *precision);
int startswith(const char *str, const char *pre);
int parse_time(const char *str, long long *sec, long *nsec);

void print_orders(struct order orders[], int count, struct config *conf);
void print_order(struct order *o, struct config *conf);
int print_resolution();
int print_usage();


int main(int argc, char *argv[]) {
    if (argc >= 2) {
        struct order *orders = malloc(sizeof(struct order) * (argc - 1));
        int count;
        struct config conf;
        int ret = parse_argv(argv, orders, &count, &conf);
        if (!ret && count > 0) {
            print_orders(orders, count, &conf);
        }
        free(orders);
        return ret;
    }

    struct order ord = {CLOCK_REALTIME, -1, 0, 0, {0, 0}};
    struct config con;
    set_default_config(&con);
    print_orders(&ord, 1, &con);
    return 0;
}

void set_default_config(struct config *conf) {
    if (conf) {
        // default config values
        conf->sep = "\n";        // field separator(=delimiter) string
        conf->nulsep = false;    // use nul('\0') character instead of conf->sep
        conf->newline = true;    // print trailing newline (or nul) at the end of record
        conf->ultime = true;     // treat system time as unsigned long
    }
}

int parse_argv(char *argv[], struct order *orders, int *count, struct config *conf) {
    set_default_config(conf);
    *count = 0;

    struct order *o = orders;
    bool allowopt = true;
    bool allowtime = false;
    char **p = &argv[1];
    char *arg = *p++;
    for (; arg; arg = *p++) {
        if (allowopt && arg[0] == '-' && arg[1] != '\0') { // arg is option (candidate)?
            char *s = &arg[1];
            char c = *s++;
            // Process "--" option first
            if (c == '-') {  // arg starts with "--"?
                if (!(*s)) {  // arg is exactly "--"?
                    allowopt = false;
                } else if (!strcmp(s, "help")) {
                    return print_usage();
                } else if (!strcmp(s, "res") || !strcmp(s, "resolution")) {
                    *count = 0;
                    return print_resolution();
                } else {
                    fprintf(stderr, "dtf: invalid option '%s' (see \"dtf -h\")\n", s);
                    return 1;
                }
                continue;
            }
            // Do not treat negative time value as an option
            if (parse_time(arg, NULL, NULL) != 0) {
                // Check invalid option character before processing
                for (; c; c = *s++) {
                    switch (c) {
                        case 'h':
                        case 'r':
                        case 'z':
                        case 'n':
                        case 's':
                            break;
                        case 'd':
                            s += strlen(s);  // Do not check extra characters
                            break;
                        default:
                            fprintf(stderr, "dtf: invalid option '%c' (see \"dtf -h\")\n", c);
                            return 1;
                    }
                }
                // Process each option character
                s = &arg[1];
                c = *s++;
                for (; c; c = *s++) {
                    switch (c) {
                        case 'h': return print_usage();
                        case 'r': *count = 0; return print_resolution();
                        case 'd':
                            if (*s != '\0') {  // has extra characters?
                                conf->sep = s;
                                s += strlen(s);
                            } else if (*p) {  // has next arg?
                                arg = *p++;
                                conf->sep = arg;
                            } else {
                                fprintf(stderr, "dtf: option 'd' requires DELIMITER (see \"dtf -h\")\n");
                                return 1;
                            }
                            break;
                        case 'z': conf->nulsep = true; break;
                        case 'n': conf->newline = false; break;
                        case 's': conf->ultime = false; break;
                        default: break;
                    }
                }
            }
        } else if (!parse_command(arg, &o->clockid, &o->precision)) { // arg is command?
            if (o->precision == -1 && o->clockid != 0) {
                fprintf(stderr, "dtf: invalid command: '%s' ('+' is only available with 'real')\n", arg);
                return 1;
            }
            o->prev_sec = 0;
            o->prev_nsec = 0;
            o++;
            (*count)++;
            allowtime = true;
        } else if (!allowtime) {   // time is not allowed?
            fprintf(stderr, "dtf: invalid command: '%s' (see \"dtf -h\")\n", arg);
            return 1;
        } else if (!parse_time(arg, &(o - 1)->prev_sec, &(o - 1)->prev_nsec)) { // arg is time?
            allowtime = false;
        } else {
            fprintf(stderr, "dtf: invalid command or time: '%s' (see \"dtf -h\")\n", arg);
            return 1;
        }
    }
//    printf("conf->sep='%s'\n", conf->sep);
//    printf("conf->nulsep=%s\n", conf->nulsep ? "true" : "false");
//    printf("conf->newline=%s\n", conf->newline ? "true" : "false");
//    for (o = orders; o < orders + *count; o++) {
//        printf("clockid=%d  precision=%d  sec=%lld  nsec=%09ld\n",
//               o->clockid, o->precision, (long long) o->prev_sec, o->prev_nsec);
//    }
    if (*count == 0) {
        fprintf(stderr, "dtf: no command (see \"dtf -h\")\n");
        return 1;
    }
    return 0;
}

int parse_command(const char *str, clockid_t *clockid, int *precision) {
    for (int i = 0; i < ARRAYSIZE(cps); i++) {
        int len = startswith(str, cps[i].key);
        if (len < 0) continue;
        for (int j = 0; j < ARRAYSIZE(pps); j++) {
            if (strcmp(str + len, pps[j].key) != 0) continue;
            if (clockid) { *clockid = cps[i].clockid; }
            if (precision) { *precision = pps[j].precision; }
            return 0;
        }
    }
    return 1;
}

int startswith(const char *str, const char *pre) {
    int lenstr = strlen(str);
    int lenpre = strlen(pre);
    return (lenstr < lenpre) ? -1 : memcmp(str, pre, lenpre) ? -1 : lenpre;
}

int parse_time(const char *str, long long *sec, long *nsec) {
    const char *s = str;
    int c = *s++;
    int neg = 0;
    if (c == '-') {
        neg = 1;
        c = *s++;
    } else if (c == '+') {
        c = *s++;
    }

    unsigned long long cutoff = neg ? -(unsigned long long) LONG_LONG_MIN : LONG_LONG_MAX;
    int cutlim = cutoff % 10ULL;
    cutoff /= 10ULL;
    unsigned long long acc;
    int any;
    for (acc = 0, any = 0;; c = *s++) {
        if (c >= '0' && c <= '9') {
            c -= '0';
        } else {
            break;
        }
        if (any < 0 || acc > cutoff || (acc == cutoff && c > cutlim)) {
            any = -1;
        } else {
            any = 1;
            acc *= 10;
            acc += c;
        }
    }

    if (any == 0 || (c != '.' && c != '\0')) return ERROR_NUMBER_FORMAT_INVALID_SEC;
    if (any < 0) return neg ? ERROR_NUMBER_OUTOFRANGE_BELOW : ERROR_NUMBER_OUTOFRANGE_ABOVE;

    if (c == '\0') {
        if (sec) { *sec = neg ? -acc : acc; }
        if (nsec) { *nsec = 0; }
    } else {  // c == '.'
        long acc2;
        int i = 0;
        c = *s++;
        for (acc2 = 0, any = 0;; c = *s++, i++) {
            if (c >= '0' && c <= '9') {
                c -= '0';
            } else {
                break;
            }
            any = 1;
            if (i < MAX_PRECISION) {
                acc2 *= 10;
                acc2 += c;
            }
        }
        if (any == 0 || c != '\0') return ERROR_NUMBER_FORMAT_INVALID_NSEC;
        for (; i < MAX_PRECISION; i++) { acc2 *= 10; }
        if (sec) { *sec = neg ? -acc : acc; }
        if (nsec) { *nsec = acc2; }
    }
    return 0;
}

void print_orders(struct order orders[], int count, struct config *conf) {
    for (int i = 0; i < count; i++) {
        orders[i].now.tv_sec = 0;
        orders[i].now.tv_nsec = 0;
    }
    for (int i = 0; i < count; i++) {
        clock_gettime(orders[i].clockid, &orders[i].now);
    }

    if (conf->nulsep) {
        int i = 0;
        for (; i < count - 1; i++) {
            print_order(&orders[i], conf);
            putchar('\0');
        }
        for (; i < count; i++) {
            print_order(&orders[i], conf);
            if (conf->newline) { putchar('\0'); }
        }
    } else {
        int i = 0;
        for (; i < count - 1; i++) {
            print_order(&orders[i], conf);
            fputs(conf->sep, stdout);
        }
        for (; i < count; i++) {
            print_order(&orders[i], conf);
            if (conf->newline) { putchar('\n'); }
        }
    }
}

void print_order(struct order *o, struct config *conf) {
    long long diff_sec;
    if (conf->ultime) {
        diff_sec = (long long) (unsigned long) o->now.tv_sec - o->prev_sec;
    } else {
        diff_sec = (long long) o->now.tv_sec - o->prev_sec;
    }
    long diff_nsec = o->now.tv_nsec - o->prev_nsec;
    if (diff_nsec < 0) {
        diff_sec--;
        diff_nsec += 1000000000;
    }
    if (o->precision == -1) {
        time_t t = (time_t) diff_sec;
        struct tm tm;
        localtime_r(&t, &tm);
        char buf[32];
        strftime(buf, sizeof(buf), "%Y%m%d-%H%M%S", &tm);
        printf("%s-%09ld %d %ld %s", buf, diff_nsec,
               tm.tm_isdst, tm.tm_gmtoff, tm.tm_zone);
        return;
    }
    char buf[MAX_PRECISION + 2];
    if (o->precision > 0) {
        sprintf(buf, ".%0*ld", MAX_PRECISION, diff_nsec);
        buf[MIN(o->precision, MAX_PRECISION) + 1] = '\0';
    } else {
        buf[0] = '\0';
    }
    printf("%lld%s", diff_sec, buf);
}

int print_resolution() {
    struct clockpair clocks[] = {
            {"CLOCK_REALTIME", CLOCK_REALTIME},
            {"CLOCK_MONOTONIC", CLOCK_MONOTONIC},
            {"CLOCK_PROCESS_CPUTIME_ID", CLOCK_PROCESS_CPUTIME_ID},
            {"CLOCK_THREAD_CPUTIME_ID", CLOCK_THREAD_CPUTIME_ID},
            {"CLOCK_MONOTONIC_RAW", CLOCK_MONOTONIC_RAW},
            {"CLOCK_REALTIME_COARSE", CLOCK_REALTIME_COARSE},
            {"CLOCK_MONOTONIC_COARSE", CLOCK_MONOTONIC_COARSE},
            {"CLOCK_BOOTTIME", CLOCK_BOOTTIME},
            {"CLOCK_REALTIME_ALARM", CLOCK_REALTIME_ALARM},
            {"CLOCK_BOOTTIME_ALARM", CLOCK_BOOTTIME_ALARM},
            {"CLOCK_SGI_CYCLE", CLOCK_SGI_CYCLE},
            {"CLOCK_TAI", CLOCK_TAI}
    };
    for (int i = 0; i < ARRAYSIZE(clocks); i++) {
        struct timespec res;
        res.tv_nsec = 0;
        clock_getres(clocks[i].clockid, &res);
        printf("%s(%d): %ldns\n", clocks[i].key, clocks[i].clockid, res.tv_nsec);
    }
    return 0;
}

int print_usage() {
    fputs("Usage: dtf [OPTION]... [COMMAND [TIME]]...\n"
          "    Print current time (or time diff to current time) of given clock(s).\n"
          "    OPTION: -h|-r|-d DELIMITER|-z|-n|-s|--help|--res|--resolution\n"
          "    COMMAND: CLOCK[PRECISION]\n"
          "             CLOCK: real|mono|pcpu|tcpu|monoraw|realcoa|monocoa|boot or 0c..15c\n"
          "             PRECISION: -ns|-us|-ms|-nano|-micro|-milli or 0..9 or +\n"
          "             '+' is special PRECISION & only for 'real'\n"
          "    TIME: SECONDS[.NANOSECONDS]\n"
          "          print time diff (now - TIME) instead of current time\n"
          "    If no argument is given, dtf behaves as \"dtf real+\".\n",
          stderr);
    return 1;
}
