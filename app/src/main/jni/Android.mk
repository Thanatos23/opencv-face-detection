LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

#opencv
OPENCVROOT:= /home/thanatos/Development/opencv-android-sdk
OPENCV_CAMERA_MODULES:=on
OPENCV_INSTALL_MODULES:=on
OPENCV_LIB_TYPE:=SHARED
include ${OPENCVROOT}/sdk/native/jni/OpenCV.mk

LOCAL_SRC_FILES := com_cryptenet_thanatos_opencvtest001_utils_OpenCVNative.cpp

LOCAL_LDLIBS += -llog
LOCAL_MODULE := OpenCVLibs


include $(BUILD_SHARED_LIBRARY)