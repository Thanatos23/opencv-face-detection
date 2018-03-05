#include <com_cryptenet_thanatos_opencvtest001_utils_OpenCVNative.h>

JNIEXPORT jint JNICALL Java_com_cryptenet_thanatos_opencvtest001_utils_OpenCVNative_convertGray
  (JNIEnv *, jclass, jlong adrRgbaMat, jlong adrGrayMat){
    Mat& rgbaMat = *(Mat*) adrRgbaMat;
    Mat& grayMat = *(Mat*) adrGrayMat;

    int convert;
    jint retVal;

    convert = toGray(rgbaMat, grayMat);

    retVal = (jint) convert;

    return retVal;
  }

  int toGray(Mat img, Mat& gray) {
    cvtColor(img, gray, CV_RGBA2GRAY);
    if (img.rows == gray.rows && img.cols == gray.cols)
        return 1;
    return 0;
  }