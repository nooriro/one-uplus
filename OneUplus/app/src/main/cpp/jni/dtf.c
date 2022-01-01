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
    char*     key;
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
    char*     key;
    int       precision;
};

struct precisionpair pps[] = {
        {"", MAX_PRECISION},
        {"-ns", 9}, {"-us", 6}, {"-ms", 3},
        {"-nano", 9}, {"-micro", 6}, {"-milli", 3},
        {"9", 9}, {"8", 8}, {"7", 7}, {"6", 6}, {"5", 5},
        {"4", 4}, {"3", 3}, {"2", 2}, {"1", 1}, {"0", 0}
};

struct order {
    clockid_t clockid;
    int precision;
    struct timespec prev;
    struct timespec now;
};

struct config {
    char* sep;
    bool nulsep;
    bool newline;
};

void print_orders(struct order orders[], int count, struct config* c);
void print_order(struct order* o);
int print_resolution();
int print_usage();

int parse_argv(char* argv[], struct order* orders, int* count, struct config* c);
int parse_command(const char* str, clockid_t* clockid, int* precision);
int startswith(const char* str, const char* pre);
int parse_time(const char* str, struct timespec* ts);


int main(int argc, char* argv[]) {
    if (argc >= 2) {
        struct order* orders = malloc(sizeof(struct order) * (argc - 1));
        int count;
        struct config c;
        int ret = parse_argv(argv, orders, &count, &c);
        if (!ret) {
            print_orders(orders, count, &c);
        }
        free(orders);
        return ret;
    }

    struct timespec now;
    clock_gettime(CLOCK_REALTIME, &now);
    struct tm nowtm;
    localtime_r(&now.tv_sec, &nowtm);
    char buf[32];
    strftime(buf, sizeof(buf), "%Y%m%d-%H%M%S", &nowtm);
    printf("%s-%09ld %d %ld %s\n", buf, now.tv_nsec,
           nowtm.tm_isdst, nowtm.tm_gmtoff, nowtm.tm_zone);
    return 0;
}

void print_orders(struct order orders[], int count, struct config* c) {
    for (int i = 0; i < count; i++) {
        orders[i].now.tv_sec = 0;
        orders[i].now.tv_nsec = 0;
    }
    for (int i = 0; i < count; i++) {
        clock_gettime(orders[i].clockid, &orders[i].now);
    }

    for (int i = 0; i < MIN(count, 1); i++) {
        print_order(&orders[i]);
    }
    if (c->nulsep) {
        for (int i = 1; i < count; i++) {
            putchar('\0');
            print_order(&orders[i]);
        }
        if (c->newline) { putchar('\0'); }
    } else {
        for (int i = 1; i < count; i++) {
            fputs(c->sep, stdout);
            print_order(&orders[i]);
        }
        if (c->newline) { putchar('\n'); }
    }
}

void print_order(struct order* o) {
    struct timespec diff;
    diff.tv_sec = o->now.tv_sec - o->prev.tv_sec;
    diff.tv_nsec = o->now.tv_nsec - o->prev.tv_nsec;
    if (diff.tv_nsec < 0) {
        diff.tv_nsec += 1000000000;
        diff.tv_sec--;
    }
    char buf[MAX_PRECISION + 2];
    if (o->precision > 0) {
        sprintf(buf, ".%0*ld", MAX_PRECISION, diff.tv_nsec);
        buf[MIN(o->precision, MAX_PRECISION) + 1] = '\0';
    } else {
        buf[0] = '\0';
    }
    printf("%lld%s", (long long) diff.tv_sec, buf);
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
          "    OPTION: -h|-r|res|-d DELIMETER|-0|-n\n"
          "    COMMAND: CLOCK[PRECISION]\n"
          "             CLOCK: real|mono|pcpu|tcpu|monoraw|realcoa|monocoa|boot or 0c..15c\n"
          "             PRECISION: -ns|-us|-ms|-nano|-micro|-milli or 0..9\n"
          "    TIME: SECONDS[.NANOSECONDS]\n"
          "          print time diff (now - TIME) instead of current time\n"
          "    If no argument is provided, dtf prints current local time as human-readable format.\n",
          stderr);
    return 1;
}

int parse_argv(char* argv[], struct order* orders, int* count, struct config* c) {
    c->sep = "\n";
    c->nulsep = false;
    c->newline = true;
    *count = 0;

    struct order* o = orders;
    bool allowtime = false;
    char** p = &argv[1];
    char* arg = *p++;
    for (; arg; arg = *p++) {
        if (!strcmp(arg, "-h")) {
            return print_usage();
        } else if (!strcmp(arg, "-r") || !strcmp(arg, "res")) {
            return print_resolution();
        } else if (!strcmp(arg, "-d")) {
            arg = *p++;
            if (!arg) {
                fprintf(stderr, "dtf: no option parameter for '-d' (must provide \"-d DELIMETER\")\n");
                return 1;
            }
            c->sep = arg;
        } else if (!strcmp(arg, "-0")) {
            c->nulsep = true;
        } else if (!strcmp(arg, "-n")) {
            c->newline = false;
        } else if (!parse_command(arg, &o->clockid, &o->precision)) { // arg is command?
            o->prev.tv_sec = 0;
            o->prev.tv_nsec = 0;
            o++;
            (*count)++;
            allowtime = true;
        } else if (!allowtime) {   // time is not allowed?
            fprintf(stderr, "dtf: invalid command: '%s' (see \"dtf -h\")\n", arg);
            return 1;
        } else if (!parse_time(arg, &(o - 1)->prev)) { // arg is time?
            allowtime = false;
        } else {
            fprintf(stderr, "dtf: invalid command or time: '%s' (see \"dtf -h\")\n", arg);
            return 1;
        }
    }
//    printf("c->sep='%s'\n", c->sep);
//    printf("c->nulsep=%s\n", c->nulsep ? "true" : "false");
//    printf("c->newline=%s\n", c->newline ? "true" : "false");
//    for (o = orders; o < orders + *count; o++) {
//        printf("clockid=%d  precision=%d  sec=%lld  nsec=%09ld\n",
//               o->clockid, o->precision, (long long) o->prev.tv_sec, o->prev.tv_nsec);
//    }
    if (*count == 0) {
        fprintf(stderr, "dtf: no command (see \"dtf -h\")\n");
        return 1;
    }
    return 0;
}

int parse_command(const char* str, clockid_t* clockid, int* precision) {
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

int startswith(const char* str, const char* pre) {
    int lenstr = strlen(str);
    int lenpre = strlen(pre);
    return (lenstr < lenpre) ? -1 : memcmp(str, pre, lenpre) ? -1 : lenpre;
}

int parse_time(const char* str, struct timespec* ts) {
    const char* s = str;
    int c = *s++;
    int neg = 0;
    if (c == '-') {
        neg = 1;
        c = *s++;
    } else if (c == '+') {
        c = *s++;
    }

    unsigned long cutoff = neg ? -(unsigned long) LONG_MIN : LONG_MAX;
    int cutlim = cutoff % 10UL;
    cutoff /= 10UL;
    unsigned long acc;
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

    long tv_sec = neg ? -acc : acc;

    if (c == '\0') {
        if (ts) {
            ts->tv_sec = tv_sec;
            ts->tv_nsec = 0;
        }
    } else {  // c == '.'
        int i = 0;
        c = *s++;
        for (acc = 0, any = 0;; c = *s++, i++) {
            if (c >= '0' && c <= '9') {
                c -= '0';
            } else {
                break;
            }
            any = 1;
            if (i < MAX_PRECISION) {
                acc *= 10;
                acc += c;
            }
        }
        if (any == 0 || c != '\0') return ERROR_NUMBER_FORMAT_INVALID_NSEC;
        for (; i < MAX_PRECISION; i++) { acc *= 10; }
        if (ts) {
            ts->tv_sec = tv_sec;
            ts->tv_nsec = acc;
        }
    }
    return 0;
}
