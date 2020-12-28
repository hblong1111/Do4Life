package com.longhb.do4life.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityLoginBinding;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.utils.CheckLoginEvent;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.viewmodel.LoginViewModel;

import static com.longhb.do4life.utils.Common.KEY_PREFS_PASSWORD;
import static com.longhb.do4life.utils.Common.KEY_PREFS_USERNAME;
import static com.longhb.do4life.utils.Common.MY_PREFS_NAME;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;
    LoginViewModel viewModel;
    private AlertDialog alertDialog;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
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

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void configRememberPassword() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String username = prefs.getString(KEY_PREFS_USERNAME, null);
        String password = prefs.getString(Common.KEY_PREFS_PASSWORD, null);
        if (username != null) {
            binding.edtPhone.setText(username);
            binding.edtPass.setText(password);
            binding.ckbSavePass.setChecked(true);
        }

        binding.ckbSavePass.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            if (isChecked) {
                editor.putString(KEY_PREFS_USERNAME, binding.edtPhone.getText().toString());
                editor.putString(KEY_PREFS_PASSWORD, binding.edtPass.getText().toString());
            } else {

                editor.putString(KEY_PREFS_USERNAME, null);
                editor.putString(KEY_PREFS_PASSWORD, null);
            }
            editor.apply();
        });
    }

    public void onClickLogin(View view) {
        if (binding.edtPhone.toString().trim() == "" || binding.edtPass.toString().trim() == "") {
            binding.txtAlert.setText("Bạn chưa nhập tài khoản hoặc mật khẩu");
        } else {
        }
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
    private void ResetPass(){
        Intent intent = new Intent(LoginActivity.this, ResetPassActivity.class);
        startActivity(intent);
    }

    private void checkLogin() {

        //TODO: check các dữ liệu người dùng nhập(không đúng yêu cầu return;)

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vui lòng đợi...");
        progressDialog.setTitle("Đang đăng nhập");
        progressDialog.show();
        String username = binding.edtPhone.getText().toString();
        String pass = binding.edtPass.getText().toString();
        viewModel.checkLogin(username, pass, new CheckLoginEvent() {
            @Override
            public void onLoginSuccess(String idAcc) {
                progressDialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra(Common.CODE_PUT_ID_ACCOUNT, idAcc);
                startActivity(intent);
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
}