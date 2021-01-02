package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityProfileDetailBinding;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.utils.Common;

public class ProfileDetail extends AppCompatActivity implements View.OnClickListener {
    private static final int CODE_UPDATE = 11;
    ActivityProfileDetailBinding binding;
    ProfileRetrofit profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        profile = (ProfileRetrofit) getIntent().getSerializableExtra(Common.CODE_PUT_PROFILE);

        binding.edtPhone.setText(profile.phoneNumber);
        binding.edtNamSinh.setText(profile.age + "");
        binding.edtName.setText(profile.fullname);

        binding.btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate:

                Intent i = new Intent(this, UpdateProfileActivity.class);
                i.putExtra(Common.CODE_PUT_PROFILE, profile);
                startActivityForResult(i, CODE_UPDATE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_UPDATE) {
            if (resultCode == Activity.RESULT_OK) {
                profile = (ProfileRetrofit) data.getSerializableExtra(Common.CODE_PUT_PROFILE);

                binding.edtPhone.setText(profile.phoneNumber);
                binding.edtNamSinh.setText(profile.age + "");
                binding.edtName.setText(profile.fullname);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//
}