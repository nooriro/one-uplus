#include <stdio.h>
#include <time.h>

int main(int argc, char* argv[]) {
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
