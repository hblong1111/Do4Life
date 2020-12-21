package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.longhb.do4life.R;

public class ConfirmAccountActivity extends AppCompatActivity {
    ImageView imageCMNDT, imageCMNDS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_account);
        imageCMNDT=findViewById(R.id.imageCMNDT);
        imageCMNDS=findViewById(R.id.imageCMNDS);

        imageCMNDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });

        imageCMNDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });
    }
    static final int REQUEST_IMAGE_CAPTURE=1;
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            // Start camera and wait for the results.
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }catch (ActivityNotFoundException e){

        }

    }


    public void onSignUp(View view) {
    }
}