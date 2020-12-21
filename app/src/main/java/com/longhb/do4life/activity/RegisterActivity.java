package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.longhb.do4life.R;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    String birthday;
    TextView edtNewBirhday;
    EditText txtFullname,edtPhone,edtPassword,edtCfPassword,edtAddess,edtCMND;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtFullname=findViewById(R.id.txtFullname);
        edtPhone=findViewById(R.id.edtPhone);
        edtPassword=findViewById(R.id.edtPassword);
        edtCfPassword=findViewById(R.id.edtCfPassword);
        edtAddess=findViewById(R.id.edtAddess);
        edtCMND=findViewById(R.id.edtCMND);
        edtNewBirhday= findViewById(R.id.edtNewBirhday);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edtNewBirhday.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                birthday=dayOfMonth+"-"+monthOfYear+"-"+year;
            }
        }, year, month, day);
    }
    public void onBack(View view) {
        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void onSignUp(View view) {
        Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    public void onPickDate(View view) {
        datePickerDialog.show();
    }
}