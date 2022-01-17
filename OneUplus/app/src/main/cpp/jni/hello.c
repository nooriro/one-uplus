#include <stdio.h>        // fprintf() fputs() putc() stdout stderr
#include <string.h>       // strlen()
#include <unistd.h>       // usleep()
#include <stdlib.h>       // getenv()
#include <stdbool.h>      // true false
#include <wchar.h>        // mbrtowc()
//#include <limits.h>       // INT_MIN

#define ANSI_START "\033[46m\033[48;5;30m"
#define ANSI_END   "\033[0m"

int intlen(int n);
inline __attribute__((always_inline)) void in_esc(bool *state, bool ansi, FILE *fp);
inline __attribute__((always_inline)) void out_esc(bool *state, bool ansi, FILE *fp);
int printarg(char *arg, bool ansi, FILE *fp, int *cbesc);


int main(int argc, char *argv[]) {
    const char *term = getenv("TERM");   // term == NULL if $TERM is not set
    bool ansi = term && *term && strcmp(term, "dumb");
    // totallen <-- intlen( maximum of i ) + num of minimal spaces
    const int totallen = intlen(argc - 1) + 1;
    for (int i = 0; i < argc; i++) {
        // for all i,  intlen(i) + num of spaces == totallen (constant)
        int numsp = totallen - intlen(i);
        fprintf(stderr, "%*sargv %d: [", numsp, "", i);
        int cbesc;                                          // cbsec == count of escaped bytes
        int ret = printarg(argv[i], ansi, stderr, &cbesc);  // ret == strlen(argv[i])
        fprintf(stderr, (ansi && cbesc > 0) ? "] %d %d\033[K\n" : "] %d %d\n", ret, cbesc);
    }
    usleep(5000);
    for (int i = 0; i < argc; i++) {
        fputs(argv[i], stdout);
        putc('\0', stdout);
    }
    return 0;
}

int intlen(int n) {
//    if (n < 0) return (n == INT_MIN) ? 11 : intlen(-n) + 1;   // including minus sign
    // Assume n is non-negative
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

inline __attribute__((always_inline))
void in_esc(bool *state, bool ansi, FILE *fp) {
    if (*state == false) {
        if (ansi) { fputs(ANSI_START, fp); }
        *state = true;
    }
}

inline __attribute__((always_inline))
void out_esc(bool *state, bool ansi, FILE *fp) {
    if (*state == true) {
        if (ansi) { fputs(ANSI_END, fp); }
        *state = false;
    }
}

int printarg(char *arg, bool ansi, FILE *fp, int *cbesc) {
    if (cbesc) { *cbesc = 0; }  // count of escaped bytes
    char *end = arg + strlen(arg);   // *end == '\0'
    bool in = false;  //  Are we in the middle of escape sequences?
    int ret = 0;      //  return value of mbrtowc()
    mbstate_t mbstate = {{0}};  // state variable for mbrtowc()
                                // should be initialized with 0
    for (char c, *s = arg; (c = *s); s++) {
        bool esc = false;  // Should char c be escaped?
        // "There are three character types, designated as char , signed char, and unsigned char."
        // "The three types char, signed char, and unsigned char are collectively called the character types."
        // "char is a separate type from the other two and is not compatible with either"
        //
        // It seems that 'char' is treated as 'unsigned char' on ARM & ARM64 Android NDK (ver. 21.0.6113669).
        //   for (char c = 127; ++c != 0; ) {
        //       printf("<%d,%u>\n", c, c);    // produces <128,128> to <255,255> on ARM & ARM64 Android
        //   }
        //
        // See: C comparison char and int
        //      https://stackoverflow.com/questions/1010330/c-comparison-char-and-int
        //
        // ret : last return value of mbrtowc()
        // ret == -2   means  "(Previous) s does not contain any complete multibyte charters until the end."
        //     See: https://man7.org/linux/man-pages/man3/mbrtowc.3.html
        //          https://en.cppreference.com/w/cpp/string/multibyte/mbrtowc
        //          https://docs.microsoft.com/en-us/cpp/c-runtime-library/reference/mbrtowc?view=msvc-170
        //          https://www.ibm.com/docs/en/i/7.1?topic=functions-mbrtowc-convert-multibyte-character-wide-character-restartable
        // In this case, do not run mbrtowc() anymore and work with only the c value until the end.
        if (ret != -2 && (signed char) c < 0) {   //  (signed char) c < 0  iff  (unsigned char) c >= 128
            wchar_t wc;
            ret = mbrtowc(&wc, s, end - s, &mbstate);
            if (ret < 0) {   // invalid multibyte sequence before next complete character (-1), or
                             // do not contain a complete multibyte character until the end (-2)
                esc = true;
            } else if ((wc >= 0x80 && wc < 0xa0)    // Unicode C1 controls (0080–009F)
                       || wc == 0x2028              // Line Separator      (2028)
                       || wc == 0x2029) {           // Paragraph Separator (2029)
                // ---- Print multibyte character as escape sequence ---- //
                in_esc(&in, ansi, fp);
                // Print wc as "\uhhhh" or "\Uhhhhhhhh"
                fprintf(fp, wc < 0x10000 ? "\\u%04x" : "\\U%08x", wc);
                // 0000-007F:    1-byte  0xxxxxxx                             (7 bits of code point)
                // 0080-07FF:    2-byte  110xxxxx 10xxxxxx                    (11 bits of code point)
                // 0800-FFFF:    3-byte  1110xxxx 10xxxxxx 10xxxxxx           (16 bits of code point)
                // 10000-1FFFFF: 4-byte  11110xxx 10xxxxxx 10xxxxxx 10xxxxxx  (21 bits of code point)
                // See: https://wiki.sei.cmu.edu/confluence/display/c/MSC10-C.+Character+encoding%3A+UTF8-related+issues
                int cb = wc < 0x80 ? 1 : wc < 0x800 ? 2 : wc < 0x10000 ? 3 : wc < 0x200000 ? 4 : wc < 0x4000000 ? 5 : 6;
                s += cb - 1;
                if (cbesc) { *cbesc += cb; }
                continue;
            } else {
                // ---- Print multibyte character as raw UTF-8 bytes ---- //
                out_esc(&in, ansi, fp);
                char *next = s + ret;
                while (s < next) {
                    putc(*s++, fp);
                }
                s--;    // s == next - 1
                continue;
            }
        } else if ((signed char) c < 32 || c == 127) {  // (signed char) c < 32   iff
                                                        // (unsigned char) c < 32 || (unsigned char) c >= 128
            esc = true;
        } // end of multibyte character printing
        //
        if (esc) {
            // ---- Print single byte character as escape sequence ---- //
            in_esc(&in, ansi, fp);
            if (c >= 7 && c < 14) {
                // Print c as "\a" "\b" "\t" "\n" "\v" "\f" "\r"
                char *str = "abtnvfr";
                putc('\\', fp);
                putc(str[c - '\a'], fp);
            } else {
                // Print c as "\ooo"
                fprintf(fp, "\\%03o", (unsigned char) c);
            }
            if (cbesc) { (*cbesc)++; }
        } else {
            // ---- Print single byte character as raw byte ---- //
            out_esc(&in, ansi, fp);
            putc(c, fp);
        } // end of single byte character printing
        //
    } // end of for loop
    out_esc(&in, ansi, fp);
    return end - arg;
}
