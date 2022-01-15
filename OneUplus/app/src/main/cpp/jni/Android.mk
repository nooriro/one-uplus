LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := dtf
LOCAL_SRC_FILES := dtf.c
# LOCAL_CFLAGS +=
# LOCAL_LDFLAGS +=
include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)
LOCAL_MODULE := hello
LOCAL_SRC_FILES := hello.c
LOCAL_CFLAGS += -D_FORTIFY_SOURCE=0 -Wno-macro-redefined  -fno-stack-protector
# LOCAL_LDFLAGS +=
include $(BUILD_EXECUTABLE)
