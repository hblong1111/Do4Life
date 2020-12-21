package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.longhb.do4life.R;

public class LoginActivity extends AppCompatActivity {
    EditText edt_user,edt_pass;
    TextView tv_register,tv_alert,tv_quen_mat_khau;
    CheckBox ckb_save_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_user=findViewById(R.id.edtPhone);
        edt_pass=findViewById(R.id.edtPass);
        tv_register=findViewById(R.id.tvSignIn);
        tv_alert=findViewById(R.id.txtAlert);
        ckb_save_pass=findViewById(R.id.ckb_save_pass);
        tv_quen_mat_khau=findViewById(R.id.tv_quen_mat_khau);

        tv_quen_mat_khau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                startActivity(intent);
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onClickLogin(View view) {
        if(edt_user.toString().trim()==""||edt_pass.toString().trim()==""){
            tv_alert.setText("Bạn chưa nhập tài khoản hoặc mật khẩu");
        }else {
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    }
}