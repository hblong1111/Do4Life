package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.viewmodel.ConfirmAccountViewModel;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class ConfirmAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CODE_IMAGE_CMNDS = 1;
    private static final int CODE_IMAGE_CMNDT = 2;
    ActivityConfirmAccountBinding binding;

    Bitmap bitmap;
    ConfirmAccountViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(ConfirmAccountViewModel.class);

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
                getUrl();
                break;
        }
    }

    private void getUrl() {
        viewModel.uploadImage(bitmap);
    }

    private void getImageCMND(int codeRequestImage) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, codeRequestImage);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap) data.getExtras().get("data");
        if (bitmap == null) return;
        if (requestCode == CODE_IMAGE_CMNDS) {
            binding.imageCMNDS.setImageBitmap(bitmap);
        } else {
            binding.imageCMNDT.setImageBitmap(bitmap);
        }
    }

}