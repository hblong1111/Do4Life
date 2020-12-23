package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                startActivity(intent);
            }
        });
        binding.tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onClickLogin(View view) {
        if(binding.edtPhone.toString().trim()==""||binding.edtPass.toString().trim()==""){
            binding.txtAlert.setText("Bạn chưa nhập tài khoản hoặc mật khẩu");
        }else {
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    }
}