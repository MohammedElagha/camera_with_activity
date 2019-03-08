package com.example.mohammed_elagha.camerawithinactivity;

/**
 * Created by Mohammed El-Agha on 08/03/19.
 */

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;


public class CameraShow extends SurfaceView implements SurfaceHolder.Callback {
    Camera camera;
    SurfaceHolder holder;

    public CameraShow(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Camera.Parameters params = camera.getParameters();

        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        } else {
            params.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }

        params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        camera.setParameters(params);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        resume();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//        camera.stopPreview();
//        camera.release();
    }

    public void resume() {
        Camera.Parameters params = camera.getParameters();

        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        } else {
            params.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }

        params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        camera.setParameters(params);
        try {
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            camera.setPreviewDisplay(holder);
            camera.startPreview();
            camera.autoFocus(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean onTouchEvent(MotionEvent event){
//        if(event.getAction() == MotionEvent.ACTION_DOWN){
//            camera.autoFocus(null);
//        }

        return true;
    }
}
