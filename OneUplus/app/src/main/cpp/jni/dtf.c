#include <stdio.h>
#include <time.h>
#include <stdbool.h>
#include <string.h>
#include <limits.h>

#define MAX_PRECISION 9
#define MIN_PRECISION 0

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
        {"0", 0}, {"1", 1}, {"2", 2}, {"3", 3},
        {"4", 4}, {"5", 5}, {"6", 6}, {"7", 7},
        {"8", 8}, {"9", 9}, {"a", 10}, {"b", 11},
        {"c", 12}, {"d", 13}, {"e", 14}, {"f", 15}
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

int print_time(clockid_t clockid, int precision);
int print_timediff(clockid_t clockid, int precision, const char *prevtime);
int print_resolution();
int print_usage();

int startswith(const char* str, const char* pre);
int str2ts(struct timespec* ts, const char* str);


int main(int argc, char* argv[]) {
    if (argc >= 2) {
        clockid_t clockid = -1;
        int precision = MAX_PRECISION;
        bool fres = false;
        bool fusage = false;

        for (int i = 0; i < ARRAYSIZE(cps); i++) {
            int len = startswith(argv[1], cps[i].key);
            if (len < 0) continue;
            for (int j = 0; j < ARRAYSIZE(pps); j++) {
                if (strcmp(argv[1] + len, pps[j].key) != 0) continue;
                clockid = cps[i].clockid;
                precision = pps[j].precision;
                // break two nested for loops and additional argv[1] checks
                goto init_done;
            }
        }
        if (!strcmp(argv[1], "res")) { fres = true; }
        else if (!strcmp(argv[1], "-h")) { fusage = true; }
        init_done:

        if (clockid >= 0) {
            if (argc == 2) {
                return print_time(clockid, precision);
            } else { // argc >= 3
                return print_timediff(clockid, precision, argv[2]);
            }
        } else if (fres) {
            return print_resolution();
        } else if (fusage) {
            return print_usage();
        }
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

int print_time(clockid_t clockid, int precision) {
    struct timespec now;
    now.tv_nsec = now.tv_sec = 0;
    clock_gettime(clockid, &now);
//    printf("NOW:  %lld.%09ld\n", (long long)now.tv_sec, now.tv_nsec);
    char buf[MAX_PRECISION + 2];
    precision = (precision > MAX_PRECISION)
                ? MAX_PRECISION
                : (precision < MIN_PRECISION) ? MIN_PRECISION : precision;
    if (precision > 0) {
        sprintf(buf, ".%0*ld", MAX_PRECISION, now.tv_nsec);
        buf[precision + 1] = '\0';
    } else {
        buf[0] = '\0';
    }
    printf("%lld%s\n", (long long)now.tv_sec, buf);
    return 0;
}

int print_timediff(clockid_t clockid, int precision, const char* prevtime) {
    struct timespec prev;
    int ret = str2ts(&prev, prevtime);
    if (ret != 0) {
        switch (ret) {
            case ERROR_NUMBER_FORMAT_INVALID_SEC:
            case ERROR_NUMBER_FORMAT_INVALID_NSEC:
                fprintf(stderr, "invalid number format: '%s' (%d)\n", prevtime, ret);
                break;
            case ERROR_NUMBER_OUTOFRANGE_ABOVE:
            case ERROR_NUMBER_OUTOFRANGE_BELOW:
                fprintf(stderr, "number out of range: '%s' (%d)\n", prevtime, ret);
                break;
            default:
                fprintf(stderr, "invalid number: '%s' (%d)\n", prevtime, ret);
        }
        return ret;
    }
    struct timespec now;
    now.tv_nsec = now.tv_sec = 0;
    clock_gettime(clockid, &now);
//    printf("NOW:  %lld.%09ld\n", (long long)now.tv_sec, now.tv_nsec);
//    printf("PREV: %lld.%09ld\n", (long long)prev.tv_sec, prev.tv_nsec);
    struct timespec diff;
    diff.tv_sec = now.tv_sec - prev.tv_sec;
    diff.tv_nsec = now.tv_nsec - prev.tv_nsec;
    if (diff.tv_nsec < 0) {
        diff.tv_nsec += 1000000000;
        diff.tv_sec--;
    }
//    printf("DIFF: %lld.%09ld\n", (long long)diff.tv_sec, diff.tv_nsec);
    char buf[MAX_PRECISION + 2];
    precision = (precision > MAX_PRECISION)
                ? MAX_PRECISION
                : (precision < MIN_PRECISION) ? MIN_PRECISION : precision;
    if (precision > 0) {
        sprintf(buf, ".%0*ld", MAX_PRECISION, diff.tv_nsec);
        buf[precision + 1] = '\0';
    } else {
        buf[0] = '\0';
    }
    printf("%lld%s\n", (long long)diff.tv_sec, buf);
    return 0;
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
    fputs("Usage: dtf [COMMAND [PREVTIME]]\n"
          "    Print current time (or time diff to current time) of given clock.\n"
          "    COMMAND: CLOCK[PRECISION] or res|-h\n"
          "             CLOCK: real|mono|pcpu|tcpu|monoraw|realcoa|monocoa|boot or 0..9|a..f\n"
          "             PRECISION: -ns|-us|-ms|-nano|-micro|-milli or 0..9\n"
          "    PREVTIME: SECONDS[.NANOSECONDS]\n"
          "              print time diff (now - PREVTIME) instead of current time\n"
          "    If no COMMAND is given, dtf prints current local time as human-readable format.\n", stderr);
    return 1;
}

int startswith(const char* str, const char* pre) {
    int lenstr = strlen(str);
    int lenpre = strlen(pre);
    return (lenstr < lenpre) ? -1 : memcmp(str, pre, lenpre) ? -1 : lenpre;
}

int str2ts(struct timespec* ts, const char* str) {
    const char *s = str;
    int c = *s++;
    int neg = 0;
    if (c == '-') {
        neg = 1;
        c = *s++;
    } else if (c == '+') {
        c = *s++;
    }

    unsigned long cutoff = neg ? -(unsigned long)LONG_MIN : LONG_MAX;
    int cutlim = cutoff % 10UL;
    cutoff /= 10UL;
    unsigned long acc;
    int any;
    for (acc = 0, any = 0; ; c = *s++) {
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
        ts->tv_sec = tv_sec;
        ts->tv_nsec = 0;
    } else {  // c == '.'
        int i = 0;
        c = *s++;
        for (acc = 0, any = 0; ; c = *s++, i++) {
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
        ts->tv_sec = tv_sec;
        ts->tv_nsec = acc;
    }
    return 0;
}
