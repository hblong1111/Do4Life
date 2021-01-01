package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.longhb.do4life.R;

public class RePassWordActivity extends AppCompatActivity {
    TextView tv_result;
    EditText edt_repass,edt_confirm_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_pass_word);
        tv_result=findViewById(R.id.tv_error_pass);
        edt_repass=findViewById(R.id.edt_repasss);
        edt_confirm_pass=findViewById(R.id.edt_confirmPass);
    }

    public void Repass(View view) {
        Intent intent = new Intent(RePassWordActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}