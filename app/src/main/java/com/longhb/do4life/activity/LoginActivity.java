package com.longhb.do4life.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.longhb.do4life.R;
import com.longhb.do4life.base.BaseActivity;
import com.longhb.do4life.databinding.ActivityLoginBinding;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.model.retrofit.json.JsonAccount;
import com.longhb.do4life.model.retrofit.res.MyAccount;
import com.longhb.do4life.utils.CheckLoginEvent;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.utils.SharedUtils;
import com.longhb.do4life.viewmodel.LoginViewModel;

import static com.longhb.do4life.utils.Common.KEY_PREFS_PASSWORD;
import static com.longhb.do4life.utils.Common.KEY_PREFS_USERNAME;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    ActivityLoginBinding binding;
    LoginViewModel viewModel;
    private AlertDialog alertDialog;
    private SharedUtils prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(LoginViewModel.class);

        configRememberPassword();

        binding.btnLogin.setOnClickListener(this);
        binding.tvQuenMatKhau.setOnClickListener(this);
        binding.tvSignIn.setOnClickListener(this);


        checkText(binding.edtPass,binding.tipPass,"Mật khẩu không hợp lệ");
        checkText(binding.edtPhone,binding.tipPhone,"Số điện thoại không đúng");

    }

    private void configRememberPassword() {
        prefs = SharedUtils.getInstance(this);
        String username = prefs.getString(KEY_PREFS_USERNAME, null);
        String password = prefs.getString(Common.KEY_PREFS_PASSWORD, null);
        if (username != null) {
            binding.edtPhone.setText(username);
            binding.edtPass.setText(password);
            binding.ckbSavePass.setChecked(true);
        }

        binding.ckbSavePass.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                prefs.setString(KEY_PREFS_USERNAME, binding.edtPhone.getText().toString());
                prefs.setString(KEY_PREFS_PASSWORD, binding.edtPass.getText().toString());
            } else {

                prefs.setString(KEY_PREFS_USERNAME, null);
                prefs.setString(KEY_PREFS_PASSWORD, null);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                checkLogin();
                break;
            case R.id.tvSignIn:
                signInAccount();
                break;
            case R.id.tvQuenMatKhau:
                //todo: chuyển màn hình qyên mật khẩu
                ResetPass();
                break;
        }
    }

    private void signInAccount() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void ResetPass() {
        Intent intent = new Intent(LoginActivity.this, ResetPassActivity.class);
        startActivity(intent);
    }

    private void checkLogin() {
        if (binding.edtPass.getText().length() == 0||binding.edtPhone.getText().length() == 0) {
            Toast.makeText(this, "Mời nhập tài khoản", Toast.LENGTH_SHORT).show();
            return;
        }
        if (binding.tipPass.isErrorEnabled()||binding.tipPhone.isErrorEnabled()){
            Toast.makeText(this, "Có lỗi xảy ra.", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vui lòng đợi...");
        progressDialog.setTitle("Đang đăng nhập");
        progressDialog.show();
        String username = binding.edtPhone.getText().toString();
        String pass = binding.edtPass.getText().toString();
        viewModel.checkLogin(new JsonAccount(username, pass), new CheckLoginEvent() {
            @Override
            public void onLoginSuccess(MyAccount myAccount) {
                progressDialog.dismiss();
                prefs.setString(Common.KEY_ID_ACC, myAccount.id);
                prefs.setString(Common.KEY_FONT_CMND_ACC, myAccount.fontCMND);
                prefs.setString(Common.KEY_BACK_CMND_ACC, myAccount.backCMND);
                prefs.setBoolean(Common.KEY_CHECKED_ACC, myAccount.checked);
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }

            @Override
            public void onLoginError() {
                progressDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Thông Báo");
                builder.setMessage("Tài khoản hoặc mật khẩu không chính xác.");
                builder.setNegativeButton("Cancle", (dialog, which) -> {
                    alertDialog.dismiss();
                });
                builder.create();
                alertDialog = builder.show();
            }
        });
    }

    @Override
    protected boolean checkPass(int idEdt, String text) {
        switch (idEdt) {
            case R.id.edtPass:
            if (text.length() >= 8) return true;
            break;
            case R.id.edtPhone:
                int i =1;
                try {
                     i = Integer.parseInt(text.substring(0, 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if ((text.length()==10||text.length()==11)&&i==0) return true;
                break;
        }
        return false;
    }
}