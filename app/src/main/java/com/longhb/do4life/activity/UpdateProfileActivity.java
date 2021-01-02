package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityUpdateProfileBinding;
import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.network.RetrofitModule;
import com.longhb.do4life.utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityUpdateProfileBinding binding;
    private ProfileRetrofit profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        profile = (ProfileRetrofit) getIntent().getSerializableExtra(Common.CODE_PUT_PROFILE);


        binding.edtPhone.setText(profile.phoneNumber);
        binding.edtNamSinh.setText(profile.age + "");
        binding.edtName.setText(profile.fullname);

        binding.btnHuy.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
        binding.btnXacNhan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
            case R.id.btnHuy:
                onBackPressed();
                break;
            case R.id.btnXacNhan:
                ProgressDialog progressDialog = Common.buildDialogLoading(this, null, "Đang tải...");
                profile.fullname = binding.edtName.getText().toString();
                profile.age = Integer.valueOf(binding.edtNamSinh.getText().toString());
                profile.phoneNumber = binding.edtPhone.getText().toString();
                RetrofitModule.getInstance().updateProfile(new JsonProfile(profile.id, profile.age, profile.fullname, profile.phoneNumber)).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (!response.body())return;
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(Common.CODE_PUT_PROFILE, profile);
                        setResult(Activity.RESULT_OK, returnIntent);
                        progressDialog.dismiss();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(UpdateProfileActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        }
    }
}