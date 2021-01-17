package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.longhb.do4life.R;
import com.longhb.do4life.utils.Common;

public class HistoryDetail extends AppCompatActivity implements View.OnClickListener {
    EditText edtNameHis, edtAgeHis, edtPhoneHis, edtDoctorHis, edtDepartmentHis, edtPriceHis, edtResultHis, edtTimeHis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        edtNameHis=findViewById(R.id.edtNameHis);
        edtAgeHis=findViewById(R.id.edtAgeHis);
        edtPhoneHis=findViewById(R.id.edtPhoneHis);
        edtDoctorHis=findViewById(R.id.edtDoctorHis);
        edtDepartmentHis=findViewById(R.id.edtDepartmentHis);
        edtPriceHis=findViewById(R.id.edtRoomHis);
        edtTimeHis=findViewById(R.id.edtTimeHis);
        edtResultHis=findViewById(R.id.edtResultHis);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}