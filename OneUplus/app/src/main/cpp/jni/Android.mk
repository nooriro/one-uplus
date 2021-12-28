LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := dtf
LOCAL_SRC_FILES := dtf.c
LOCAL_LDFLAGS += -Wl,--build-id=none
include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)
LOCAL_MODULE := hello
LOCAL_SRC_FILES := hello.c
LOCAL_CFLAGS += -Os
LOCAL_LDFLAGS += -Wl,--build-id=none
include $(BUILD_EXECUTABLE)
