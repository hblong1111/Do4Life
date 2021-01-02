package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityThemHoSoBinding;
import com.longhb.do4life.model.Profile;
import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.utils.SharedUtils;

import java.io.Serializable;

public class ThemHoSoActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityThemHoSoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThemHoSoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnXacNhan.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnXacNhan:
                String acc = SharedUtils.getInstance(this).getString(Common.KEY_ID_ACC, null);
                String name = binding.edtName.getText().toString();
                String numberPhone = binding.edtPhone.getText().toString();
                int age = Integer.parseInt(binding.edtNamSinh.getText().toString());
                Intent returnIntent = new Intent();
                JsonProfile profile = new JsonProfile(acc,  age,name, numberPhone);
                returnIntent.putExtra(Common.CODE_PUT_PROFILE, (Serializable) profile);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                break;
        }
    }
}