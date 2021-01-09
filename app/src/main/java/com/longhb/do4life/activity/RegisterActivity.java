package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.longhb.do4life.R;
import com.longhb.do4life.base.BaseActivity;
import com.longhb.do4life.databinding.ActivityRegisterBinding;
import com.longhb.do4life.model.retrofit.json.JsonAccount;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.utils.CheckCreateAccountEvent;
import com.longhb.do4life.viewmodel.RegisterViewModel;

import static com.longhb.do4life.utils.Common.showDialogAlert;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

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

        checkText(binding.edtCMND, binding.tipCMND, "Số CMND không hợp lệ");
        checkText(binding.edtPhone, binding.tipPhone, "Số điện thoại không hợp lệ");
        checkText(binding.edtPassword, binding.tipPass, "Password không hợp lệ");
        checkText(binding.edtCfPassword, binding.tipCfPass, "Password không hợp lệ");


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


        if (binding.edtCMND.getText().length() == 0
                || binding.edtPhone.getText().length() == 0
                || binding.edtCMND.getText().length() == 0
                || binding.edtPhone.getText().length() == 0) {
            Toast.makeText(this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if (binding.tipPass.isErrorEnabled() || binding.tipPhone.isErrorEnabled() || binding.tipCfPass.isErrorEnabled() || binding.tipCMND.isErrorEnabled()) {
            Toast.makeText(this, "Có lỗi xảy ra.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!binding.edtPassword.getText().toString().equals(binding.edtCfPassword.getText().toString())) {
            binding.tipCfPass.setError("Hai mật khẩu phải khớp nhau");
            return;
        }

        String username = binding.edtPhone.getText().toString();
        String pass = binding.edtPassword.getText().toString();
        String passCF = binding.edtCfPassword.getText().toString();

        String cmnd = binding.edtCMND.getText().toString();

        if (pass.equals(passCF)) {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Vui lòng đợi...");
            dialog.setTitle("Tạo tài khoản");
            dialog.show();
            viewModel.createAccount(new JsonAccount(username, pass, cmnd), new CheckCreateAccountEvent() {
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

    @Override
    protected boolean checkPass(int idEdt, String text) {

        switch (idEdt) {
            case R.id.edtCMND:
                if (text.length() == 9 || text.length() == 12) return true;
                break;
            case R.id.edtPhone:
                int i = 1;
                try {
                    i = Integer.parseInt(text.substring(0, 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if ((text.length() == 10 || text.length() == 11) && i == 0) return true;
                break;
            case R.id.edtPassword:
            case R.id.edtCfPassword:
                if (text.length() >= 8) return true;
                break;
        }
        return false;
    }
}