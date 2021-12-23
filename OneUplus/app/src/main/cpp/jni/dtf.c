#include <stdio.h>
#include <time.h>

int main(int argc, char* argv[]) {
    struct timespec ts;
    clock_gettime(CLOCK_REALTIME, &ts);
    struct tm now;
    localtime_r(&ts.tv_sec, &now);
    char buf[32];
    strftime(buf, sizeof(buf), "%Y%m%d-%H%M%S", &now);
    printf("%s-%09ld %d %ld %s\n", buf, ts.tv_nsec, now.tm_isdst, now.tm_gmtoff, now.tm_zone);
    return 0;
}
