/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <stdio.h>
#include <opencv2/opencv.hpp>

using namespace cv;
using namespace std;
/* Header for class com_cryptenet_thanatos_opencvtest001_utils_OpenCVNative */

#ifndef _Included_com_cryptenet_thanatos_opencvtest001_utils_OpenCVNative
#define _Included_com_cryptenet_thanatos_opencvtest001_utils_OpenCVNative
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_cryptenet_thanatos_opencvtest001_utils_OpenCVNative
 * Method:    convertGray
 * Signature: (JJ)I
 */
int toGray(Mat img, Mat& gray);

JNIEXPORT jint JNICALL Java_com_cryptenet_thanatos_opencvtest001_utils_OpenCVNative_convertGray
  (JNIEnv *, jclass, jlong, jlong);

#ifdef __cplusplus
}
#endif
#endif
