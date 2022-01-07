APP_ABI := armeabi-v7a arm64-v8a x86 x86_64
APP_CFLAGS := -Wall -Oz -flto -fno-unwind-tables -fomit-frame-pointer   # -D_FORTIFY_SOURCE=0 -Wno-macro-redefined  -fno-stack-protector
APP_LDFLAGS := -flto -Wl,--build-id=none   # -Wl,-z,max-page-size=4096
# APP_STRIP_MODE := --strip-all
