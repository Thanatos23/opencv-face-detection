package com.cryptenet.thanatos.opencvtest001;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import com.cryptenet.thanatos.opencvtest001.utils.CryptApplication;
import com.cryptenet.thanatos.opencvtest001.utils.OpenCVNative;
import com.cryptenet.thanatos.opencvtest001.utils.TagProvider;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCamera2View;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
    implements CameraBridgeViewBase.CvCameraViewListener2{
    private static final String TAG = TagProvider.getDebugTag(MainActivity.class);
    private final int REQUEST_CODE = 123;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.camera_view) JavaCameraView cameraView;

    BaseLoaderCallback loaderCallback;
    Mat rgbaMat, grayMat;

    //cc nasif

    static {
        System.loadLibrary("OpenCVLibs");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        ((CryptApplication) getApplication()).getAppComponents().inject(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        loaderCallback = new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case BaseLoaderCallback.SUCCESS:
                        cameraView.enableView();
                        break;
                    default:
                        super.onManagerConnected(status);
                        break;
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }

        cameraView.setVisibility(SurfaceView.VISIBLE);
        cameraView.setCvCameraViewListener(this);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        rgbaMat = new Mat(height, width, CvType.CV_8SC4);
        grayMat = new Mat(height, width, CvType.CV_8SC1);
    }

    @Override
    public void onCameraViewStopped() {
        rgbaMat.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        rgbaMat = inputFrame.rgba();

        OpenCVNative.convertGray(rgbaMat.getNativeObjAddr(), grayMat.getNativeObjAddr());

        return grayMat;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Log.d(TAG, "Granted!!");
        } else
            Log.d(TAG, "Denied!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()) {
            Log.d(TAG, "load success!!");
            loaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            Log.d(TAG, "load failed!!");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0, this, loaderCallback);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cameraView != null) {
            cameraView.disableView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraView != null) {
            cameraView.disableView();
        }
    }
}
