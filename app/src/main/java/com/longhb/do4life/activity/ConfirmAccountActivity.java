package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityConfirmAccountBinding;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class ConfirmAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CODE_IMAGE_CMNDS = 1;
    private static final int CODE_IMAGE_CMNDT = 2;
    ActivityConfirmAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageCMNDS.setOnClickListener(this);
        binding.imageCMNDT.setOnClickListener(this);
        binding.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageCMNDS:
                getImageCMND(CODE_IMAGE_CMNDS);
                break;
            case R.id.imageCMNDT:
                getImageCMND(CODE_IMAGE_CMNDT);
                break;
            case R.id.btnSave:
                break;
        }
    }

    private void getImageCMND(int codeRequestImage) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, codeRequestImage);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        Uri uri = getImageUri(getApplicationContext(), photo);
        if (uri == null) return;
        if (requestCode == CODE_IMAGE_CMNDS) {
            binding.imageCMNDS.setImageURI(uri);
        } else {
            binding.imageCMNDT.setImageURI(uri);
        }
        Log.d("longhbb", "ConfirmAccountActivity | onActivityResult: " +
                getImageUri(getApplicationContext(), photo));
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, UUID.randomUUID().toString() + ".png", "drawing");
        return Uri.parse(path);
    }
}