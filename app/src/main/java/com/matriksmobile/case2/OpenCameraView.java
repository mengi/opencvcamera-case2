package com.matriksmobile.case2;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import org.opencv.android.JavaCameraView;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;


public class OpenCameraView extends JavaCameraView implements Camera.PictureCallback {

    private String mPictureFileName;
    private Mat imgThresholded;
    private Context context;


    public OpenCameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void takePicture(final String fileName, Mat mat) {
        this.mPictureFileName = fileName;
        this.imgThresholded = mat;
        mCamera.setPreviewCallback(null);
        mCamera.takePicture(null, null, this);
    }


    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        mCamera.startPreview();
        mCamera.setPreviewCallback(this);
        Highgui.imwrite(mPictureFileName, imgThresholded);
    }
}
