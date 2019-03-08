package com.example.mohammed_elagha.camerawithinactivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    Camera camera;
    CameraShow cameraShow;
    FrameLayout frameLayout;
    Button captureButton;

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            cameraShow.resume();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frame_layout);

        camera = Camera.open();

        cameraShow = new CameraShow(this, camera);
        frameLayout.addView(cameraShow);

        captureButton = findViewById(R.id.capture_btn);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage(v);
            }
        });
    }

    public void captureImage(View v) {
        if (camera != null) {
            camera.takePicture(null, null, mPictureCallback);
        }
    }
}
