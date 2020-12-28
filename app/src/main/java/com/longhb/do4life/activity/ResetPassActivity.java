package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.longhb.do4life.R;

public class ResetPassActivity extends AppCompatActivity {
    TextView tv_error;
    EditText edt_repasss,edtCMND;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        tv_error=findViewById(R.id.tv_error);
        edt_repasss=findViewById(R.id.edt_repasss);
        edtCMND=findViewById(R.id.edtCMND);
    }

    public void Confirm(View view) {
        Intent intent = new Intent(ResetPassActivity.this, RePassWordActivity.class);
        startActivity(intent);
    }

    public void Cancel(View view) {
            Intent intent = new Intent(ResetPassActivity.this, LoginActivity.class);
            startActivity(intent);
    }
}