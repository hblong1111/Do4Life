package com.longhb.do4life.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.longhb.do4life.R;
import com.longhb.do4life.base.BaseActivity;
import com.longhb.do4life.databinding.ActivityUpdateProfileBinding;
import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.network.RetrofitModule;
import com.longhb.do4life.utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends BaseActivity implements View.OnClickListener {
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

        checkText(binding.edtPhone, binding.tipPhone, "Số điện thoại không đúng");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
            case R.id.btnHuy:
                onBackPressed();
                break;
            case R.id.btnXacNhan:
                String name = binding.edtName.getText().toString();
                String numberPhone = binding.edtPhone.getText().toString();
                String ageString = binding.edtNamSinh.getText().toString();

                //kiểm tra điều kiện nhập

                if (numberPhone.length() == 0 || name.length() == 0 || ageString.length() == 0) {
                    Toast.makeText(this, "Nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (binding.tipName.isErrorEnabled() || binding.tipPhone.isErrorEnabled() || binding.tipNamSinh.isErrorEnabled()) {
                    Toast.makeText(this, "Lỗi nhập sai dữ liệu.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(ageString);

                ProgressDialog progressDialog = Common.buildDialogLoading(this, null, "Đang tải...");
                profile.fullname = binding.edtName.getText().toString();
                profile.age = age;
                profile.phoneNumber = binding.edtPhone.getText().toString();


                RetrofitModule.getInstance().updateProfile(new JsonProfile(profile.id, profile.age, profile.fullname, profile.phoneNumber)).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (!response.body()) return;
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

    @Override
    protected boolean checkPass(int idEdt, String text) {
        switch (idEdt) {
            case R.id.edtPass:
                if (text.length() >= 8) return true;
                break;
            case R.id.edtPhone:
                int i = 1;
                try {
                    i = Integer.parseInt(text.substring(0, 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if ((text.length() == 10 || text.length() == 11) && i == 0) return true;
                break;
        }
        return false;
    }
}