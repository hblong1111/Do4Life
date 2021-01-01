package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityRegisterBinding;
import com.longhb.do4life.model.retrofit.json.JsonAccount;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.utils.CheckCreateAccountEvent;
import com.longhb.do4life.viewmodel.RegisterViewModel;

import static com.longhb.do4life.utils.Common.showDialogAlert;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityRegisterBinding binding;
    RegisterViewModel viewModel;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(RegisterViewModel.class);


        binding.btnSignIn.setOnClickListener(this);
        binding.tvBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                siginAccount();
                break;
            case R.id.tvBack:
                onBackPressed();
                break;
        }
    }

    private void siginAccount() {

        //TODO: check các dữ liệu người dùng nhập(không đúng yêu cầu return;)

        String username = binding.edtPhone.getText().toString();
        String pass = binding.edtPassword.getText().toString();
        String passCF = binding.edtCfPassword.getText().toString();

        String cmnd = binding.edtCMND.getText().toString();

        if (pass.equals(passCF)) {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Vui lòng đợi...");
            dialog.setTitle("Tạo tài khoản");
            dialog.show();
            viewModel.createAccount(new JsonAccount(username,pass,cmnd), new CheckCreateAccountEvent() {
                @Override
                public void onCreateSuccess() {
                    dialog.dismiss();
                    alertDialog = showDialogAlert(RegisterActivity.this, "Tạo tài khoản thành công.", "ok", (dialog, which) -> {
                        alertDialog.dismiss();
                        onBackPressed();
                    });
                }

                @Override
                public void onCreateError() {
                    dialog.dismiss();
                    alertDialog = showDialogAlert(RegisterActivity.this, "Tài khoản đã tồn tại.", "cancel", (dialog, which) -> {
                        alertDialog.dismiss();
                    });
                }
            });
        } else {
            alertDialog = showDialogAlert(RegisterActivity.this, "Hai mật khẩu chưa trùng khớp.", "cancel", (dialog, which) -> {
                alertDialog.dismiss();
            });
        }
    }

}