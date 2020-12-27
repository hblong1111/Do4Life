package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityConfirmAccountBinding;
import com.longhb.do4life.model.JsonProfile;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.viewmodel.ConfirmAccountViewModel;
import com.longhb.do4life.viewmodel.UpdateAccountViewModel;

public class ConfirmAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CODE_IMAGE_CMNDS = 1;
    private static final int CODE_IMAGE_CMNDT = 2;
    ActivityConfirmAccountBinding binding;

    Bitmap bitmapT, bitmapS;
    ConfirmAccountViewModel viewModel;
    private AlertDialog alertDialog;

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
                updateAccount();
                break;
        }
    }

    private void updateAccount() {
        //check ảnh
        if (bitmapT == null || bitmapS == null) {
            alertDialog = Common.showDialogAlert(this,
                    "Ảnh CMND cần phải chụp cả mặt trước và sau.",
                    "Cancel",
                    (dialog, which) -> alertDialog.dismiss());
        } else {
            ProgressDialog dialog = Common.buildDialogLoading(this, "Xin Chờ", "Đang gửi yêu cầu...");
            dialog.show();
            String[] urlT = new String[1];
            String[] urlS = new String[1];
            viewModel.uploadImage(bitmapT, url -> {
                urlT[0] = url;
                viewModel.uploadImage(bitmapS, urlS0 -> {
                    urlS[0] = urlS0;
                    if (urlS[0] == null || urlS[0].equals("") || urlT[0] == null || urlT[0].equals("")) {
                        alertDialog = Common.showDialogAlert(ConfirmAccountActivity.this,
                                "Có lỗi xảy ra, vui lòng thủ lại.",
                                "cancel",
                                (dialoga, which) -> alertDialog.dismiss());
                    } else {
                        //fixme: lấy id và số cmnd của người dùng
                        JsonProfile jsonProfile = new JsonProfile("5fdb822b7361b200177b71b7",
                                "2eweqrffasasda",
                                urlT[0],
                                urlS[0],
                                true,
                                true);
                        viewModel.updateAccount(jsonProfile, new ConfirmAccountViewModel.UpdateAccountEvent() {
                            @Override
                            public void onUpdateSuccess() {
                                dialog.dismiss();
                                alertDialog = Common.showDialogAlert(ConfirmAccountActivity.this,
                                        "Đã gửi yêu cầu xác nhận!",
                                        "ok",
                                        (dialog, which) -> alertDialog.dismiss());
                            }

                            @Override
                            public void onUpdateError() {
                                alertDialog = Common.showDialogAlert(ConfirmAccountActivity.this,
                                        "Có lỗi xảy ra, vui lòng thử lại.",
                                        "ok",
                                        (dialog, which) -> alertDialog.dismiss());
                            }
                        });
                    }
                });
            });

        }
    }

    private void getImageCMND(int codeRequestImage) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, codeRequestImage);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            data.getExtras().get("data");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (requestCode == CODE_IMAGE_CMNDS) {
            bitmapS = (Bitmap) data.getExtras().get("data");
            if (bitmapS == null) return;
            binding.imageCMNDS.setImageBitmap(bitmapS);
        } else {
            bitmapT = (Bitmap) data.getExtras().get("data");
            if (bitmapT == null) return;
            binding.imageCMNDT.setImageBitmap(bitmapT);
        }
    }

}