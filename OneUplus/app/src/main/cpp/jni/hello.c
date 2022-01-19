#include <stdio.h>        // fprintf() fputs() putc() stdout stderr
#include <string.h>       // strlen()
#include <unistd.h>       // usleep()
#include <stdlib.h>       // getenv()
#include <stdbool.h>      // true false
#include <wchar.h>        // mbrtowc()
//#include <limits.h>       // INT_MIN

#define ES_NONE    0    // non-escaped character - print raw byte(s)
#define ES_TYPE1A  1    // "\a" "\b" "\t" "\n" "\v" "\f" "\r" and "\033"
#define ES_TYPE1B  2    // other 1-byte ascii escaped character ("\000" to "\177")
#define ES_TYPE1C  3    // 1-byte non-ascii escaped character   ("\200" to "\377")
#define ES_TYPE2   4    // multibyte (2-byte) escaped character ("\uhhhh")
#define ES_TYPE3   5    // multibyte (3-byte) escaped character ("\uhhhh")
#define ES_TYPE4   6    // multibyte (4-byte) escaped character ("\Uhhhhhhhh")

// See: 256 (8-bit) color ANSI
//      https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit
//      foreground color: ESC[38;5;NUMBERm
//      background color: ESC[48;5;NUMBERm
//      NUMBER in 16-231: 16 + 36*r + 6*g + b (0 <= r,g,b <= 5) : 216 colors (6x6x6 cube)
// --------------------------------------------------------------------------------------
//              +95           +40           +40           +40           +40
//   0->0(0x00)    1->95(0x5f)   2->135(0x87)  3->175(0xaf)  4->215(0xd7)  5->255(0xff)
//      0/51    +19   19/51    +8   27/51    +8   35/51    +8   43/51    +8   51/51
//      0.000         0.372         0.529         0.686         0.843         1.000
const char *const ANSI[] = {
        "\033[0m",          // ES_NONE
        "\033[48;5;90m",    // ES_TYPE1A : #870087 (L=31 a=58 b=-38) : "Magenta" (like "\033[45m", #800080)
        "\033[48;5;30m",    // ES_TYPE1B : #008787 (L=51 a=-32 b=-9) : "Cyan"    (like "\033[46m", #008080)
        "\033[48;5;59m",    // ES_TYPE1C : #5F5F5F (L=40 a=0 b=0)    : gray
        "\033[48;5;141m",   // ES_TYPE2  : #AF87FF (L=64 a=35 b=-55) : light purple
        "\033[48;5;176m",   // ES_TYPE3  : #D787D7 (L=67 a=40 b=-28) : light pink
        "\033[48;5;173m"    // ES_TYPE4  : #D7875F (L=64 a=29 b=35)  : light orange
};

int intlen(int n);
inline __attribute__((always_inline)) void update_es(int *es, int newes, bool ansi, FILE *fp);
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

// Call this function to update 'es'(escape state variable) before printing each character.
// Do not update 'es' directly.
inline __attribute__((always_inline)) //
void update_es(int *es, int newes, bool ansi, FILE *fp) {
    if (*es != newes) {
        if (ansi) { fputs(ANSI[newes], fp); }
        *es = newes;
    } //
} //

int printarg(char *arg, bool ansi, FILE *fp, int *cbesc) {
    char *end = arg + strlen(arg);     // *end == '\0'
    int es = ES_NONE;            // escape state
    if (cbesc) { *cbesc = 0; }   // count of escaped bytes
    mbstate_t mbstate = {{0}};   // state for mbrtowc()
    for (char c, *s = arg; (c = *s); s++) {
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
        // See: mbrtowc() reference
        //      https://man7.org/linux/man-pages/man3/mbrtowc.3.html
        //      https://en.cppreference.com/w/cpp/string/multibyte/mbrtowc
        //      https://docs.microsoft.com/en-us/cpp/c-runtime-library/reference/mbrtowc?view=msvc-170
        //      https://www.ibm.com/docs/en/i/7.1?topic=functions-mbrtowc-convert-multibyte-character-wide-character-restartable
        //
        // See: UTF-8 encoding by code point
        //      https://wiki.sei.cmu.edu/confluence/display/c/MSC10-C.+Character+encoding%3A+UTF8-related+issues
        //      0000-007F:    1-byte  0xxxxxxx                             (7 bits of code point)
        //      0080-07FF:    2-byte  110xxxxx 10xxxxxx                    (11 bits of code point)
        //      0800-FFFF:    3-byte  1110xxxx 10xxxxxx 10xxxxxx           (16 bits of code point)
        //      10000-1FFFFF: 4-byte  11110xxx 10xxxxxx 10xxxxxx 10xxxxxx  (21 bits of code point)
        //
        if ((unsigned char) c >= 128) {   //  equivalent to    (signed char) c < 0
            wchar_t wc;
            int ret = mbrtowc(&wc, s, end - s, &mbstate);
            if (ret < 0) {   // invalid multibyte sequence before next complete character (-1), or
                             // do not contain a complete multibyte character until the end (-2)
                update_es(&es, ES_TYPE1C, ansi, fp);
            } else if ((wc >= 0x80 && wc < 0xa0)    // Unicode C1 controls (0080–009F)
                       || wc == 0x2028              // Line Separator      (2028)
                       || wc == 0x2029) {           // Paragraph Separator (2029)
                // ---- Print multibyte character as escape sequence ---- //
                //               ("\uhhhh" or "\Uhhhhhhhh")               //
                update_es(&es, ret == 2 ? ES_TYPE2 : ret == 3 ? ES_TYPE3 : ES_TYPE4, ansi, fp);
                fprintf(fp, wc < 0x10000 ? "\\u%04x" : "\\U%08x", wc);
                s += ret - 1;
                if (cbesc) { *cbesc += ret; }
                continue;
            } else {
                // ---- Print multibyte character as raw UTF-8 bytes ---- //
                update_es(&es, ES_NONE, ansi, fp);
                char *next = s + ret;
                while (s < next) {
                    putc(*s++, fp);
                }
                s--;    // s == next - 1
                continue;
            }
        } else if (c < 32 || c == 127) {
            update_es(&es, (c >= 7 && c < 14) || c == 27 ? ES_TYPE1A : ES_TYPE1B, ansi, fp);
        } else {
            update_es(&es, ES_NONE, ansi, fp);
        }
        //
        if (es != ES_NONE) {
            // ---- Print single byte character as escape sequence ---- //
            //      ("\a" "\b" "\t" "\n" "\v" "\f" "\r" or "\ooo")      //
            if (c >= 7 && c < 14) {
                char *str = "abtnvfr";
                putc('\\', fp);
                putc(str[c - '\a'], fp);
            } else {
                fprintf(fp, "\\%03o", (unsigned char) c);
            }
            if (cbesc) { (*cbesc)++; }
        } else {
            // ---- Print single byte character as raw byte ---- //
            putc(c, fp);
        }
        //
    } // end of for loop
    update_es(&es, ES_NONE, ansi, fp);
    return end - arg;
}
