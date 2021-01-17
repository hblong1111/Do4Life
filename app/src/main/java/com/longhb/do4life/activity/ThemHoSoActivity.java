package com.longhb.do4life.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.longhb.do4life.R;
import com.longhb.do4life.base.BaseActivity;
import com.longhb.do4life.databinding.ActivityThemHoSoBinding;
import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.utils.SharedUtils;

import java.io.Serializable;

public class ThemHoSoActivity extends BaseActivity implements View.OnClickListener {
    ActivityThemHoSoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThemHoSoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnXacNhan.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);

        checkText(binding.edtPhone,binding.tipPhone,"Số điện thoại không đúng");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnXacNhan:
                String acc = SharedUtils.getInstance(this).getString(Common.KEY_ID_ACC, null);
                String name = binding.edtName.getText().toString();
                String numberPhone = binding.edtPhone.getText().toString();
                String ageString = binding.edtNamSinh.getText().toString();

                //kiểm tra điều kiện nhập

                if (numberPhone.length() == 0||name.length() == 0||ageString.length() == 0) {
                    Toast.makeText(this, "Nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (binding.tipName.isErrorEnabled()||binding.tipPhone.isErrorEnabled()||binding.tipNamSinh.isErrorEnabled()){
                    Toast.makeText(this, "Lỗi nhập sai dữ liệu.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(ageString);
                Intent returnIntent = new Intent();
                JsonProfile profile = new JsonProfile(acc, name, age, numberPhone);
                returnIntent.putExtra(Common.CODE_PUT_PROFILE, (Serializable) profile);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                break;
            case R.id.btnBack:
                onBackPressed();
                break;
        }
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