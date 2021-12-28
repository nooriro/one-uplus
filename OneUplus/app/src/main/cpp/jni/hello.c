#include <stdio.h>
#include <unistd.h>
//#include <limits.h>

int intlen(int n) {
//    if (n < 0) return (n == INT_MIN) ? 11 : intlen(-n) + 1;   // including minus sign
    // n must be non-negative
    if (n < 10) return 1;
    if (n < 100) return 2;
    if (n < 1000) return 3;
    if (n < 10000) return 4;
    if (n < 100000) return 5;
    if (n < 1000000) return 6;
    if (n < 10000000) return 7;
    if (n < 100000000) return 8;
    if (n < 1000000000) return 9;
    return 10; // INT_MAX == 2147483647 is ten-digit
}

int main(int argc, char* argv[]) {
    // totallen <-- intlen( maximum of i ) + num of minimal spaces
    const int totallen = intlen(argc - 1) + 1;
    // intlen(i) + num of spaces == totallen  (constant for all i)
    for (int i = 0; i < argc; i++) {
        int numsp = totallen - intlen(i);
        fprintf(stderr, "%*sargv %d: [%s]\n", numsp, "", i, argv[i]);
    }
    usleep(5000);
    for (int i = 0; i < argc; i++) {
        fputs(argv[i], stdout);
        putc('\0', stdout);
    }
    return 0;
}
