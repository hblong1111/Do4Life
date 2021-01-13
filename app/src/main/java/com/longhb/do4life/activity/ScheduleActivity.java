package com.longhb.do4life.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityDatLichBinding;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.model.retrofit.json.JsonCreateSchedule;
import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.json.JsonShift;
import com.longhb.do4life.model.retrofit.res.Department;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.model.retrofit.res.Shift;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.utils.SharedUtils;
import com.longhb.do4life.viewmodel.ScheduleActivityViewModel;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityDatLichBinding binding;
    ScheduleActivityViewModel viewModel;

    List<Department> departmentList;

    List<Shift> shiftList, shiftListDate, shiftListTime;
    List<ProfileRetrofit> listProfile;
    private ProgressDialog progressDialog;

    SimpleDateFormat formatDate, formatTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDatLichBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(ScheduleActivityViewModel.class);

        departmentList = new ArrayList<>();
        shiftList = new ArrayList<>();
        listProfile = new ArrayList<>();
        shiftListDate = new ArrayList<>();
        shiftListTime = new ArrayList<>();

        progressDialog = Common.buildDialogLoading(ScheduleActivity.this, null, "Đang tải...");

        formatTime = new SimpleDateFormat("hh:mm");
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        settingSpinnerProfile();


        settingSpinnerShiftDate();

        binding.btnXacNhan.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
        binding.btnHuy.setOnClickListener(this);


    }

    private void settingSpinnerProfile() {
        viewModel.getProfile(new JsonProfile(SharedUtils.getInstance(this).getString(Common.KEY_ID_ACC, null)),
                new ScheduleActivityViewModel.EventGetProfile() {
                    @Override
                    public void onSuccess(List<ProfileRetrofit> body) {
                        listProfile.addAll(body);
                        List<String> strings = new ArrayList<>();

                        for (ProfileRetrofit retrofit : listProfile) {
                            strings.add(retrofit.fullname + " - " + retrofit.age + " tuổi");
                        }

                        binding.spinnerProfile.setItems(strings);

                        settingSpinnerDepartment();

                    }

                    @Override
                    public void onError() {
                        progressDialog.dismiss();
                        Toast.makeText(ScheduleActivity.this, "Có lỗi xảy ra, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                });
    }

    private void settingSpinnerShiftDate() {
        viewModel.getLitShift().observe(this, shifts -> {
            Log.d("hblong", "ScheduleActivity | settingSpinnerShift: " + shifts.toString());

            binding.spinnerShift.clearSelectedItem();
            shiftListTime.clear();
            shiftList.clear();
            shiftListDate.clear();
            shiftList.addAll(shifts);
            List<String> strings = new ArrayList<>();

            s:
            for (Shift shift : shiftList) {
                if (!shift.status) continue;
                for (Shift shift1 : shiftListDate) {
                    Log.d("hblong", "ScheduleActivity | settingSpinnerShiftDate: " +shift1.getTime(formatDate)+"|"+ shift.getTime(formatDate));
                    if (shift1.getTime(formatDate).equals(shift.getTime(formatDate))) {
                        continue s;
                    }
                }
                shiftListDate.add(shift);
                strings.add(shift.getTime(formatDate));
            }

            if (shiftListDate.size() == 0) {
                Toast.makeText(this, "Không còn ngày khám nào.", Toast.LENGTH_SHORT).show();
            }
            binding.spinnerDate.setItems(strings);
        });


        binding.spinnerDate.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (i, s, i1, t1) -> {
            binding.spinnerShift.clearSelectedItem();
            shiftListTime.clear();
            List<String> strings=new ArrayList<>();
            for (Shift shift:shiftList  ) {
                if (shift.getTime(formatDate).equals(shiftListDate.get(binding.spinnerDate.getSelectedIndex()).getTime(formatDate))) {
                    shiftListTime.add(shift);
                    strings.add(shift.getTime(formatTime));
                }
            }

            if (strings.size() == 0) {
                Toast.makeText(this, "Không còn ca khám nào trống, vui lòng quay lại sau.", Toast.LENGTH_SHORT).show();
            }
            binding.spinnerShift.setItems(strings);
        });

    }

    private void settingSpinnerDepartment() {
        binding.spinnerDepartment.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<String>() {
            @Override
            public void onItemSelected(int oldIndex, @Nullable String oldItem, int newIndex, String newItem) {
                if (!progressDialog.isShowing()) progressDialog.show();
                binding.spinnerShift.clearSelectedItem();
                //todo: call lại dữ liệu ca khám
                viewModel.getAllShiftByDepartmentId(new ScheduleActivityViewModel.EventGetAllShift() {
                    @Override
                    public void onSuccess() {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError() {
                        progressDialog.dismiss();
                        Toast.makeText(ScheduleActivity.this, "Có lỗi xảy ra, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }, new JsonShift(departmentList.get(newIndex).id));
            }
        });

        viewModel.getAllDepartment(new ScheduleActivityViewModel.EventGetAllDepartment() {
            @Override
            public void onSuccess(List<Department> body) {
                departmentList.addAll(body);
                List<String> strings = new ArrayList<>();
                for (int i = 0; i < departmentList.size(); i++) {
                    strings.add(departmentList.get(i).name);
                }
                binding.spinnerDepartment.setItems(strings);
                progressDialog.dismiss();
            }

            @Override
            public void onError() {
                progressDialog.dismiss();
                Toast.makeText(ScheduleActivity.this, "Có lỗi xảy ra, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnXacNhan:
                addSchedule();
                break;
            case R.id.btnBack:
            case R.id.btnHuy:
                onBackPressed();
                break;
        }
    }

    private void addSchedule() {
        if (binding.spinnerProfile.getSelectedIndex() >= 0 && binding.spinnerDepartment.getSelectedIndex() >= 0 && binding.spinnerShift.getSelectedIndex() >= 0) {
            progressDialog.show();
            Shift shift = shiftListDate.get(binding.spinnerShift.getSelectedIndex());
            ProfileRetrofit profileRetrofit = listProfile.get(binding.spinnerProfile.getSelectedIndex());
            viewModel.createSchedule(new JsonCreateSchedule(shift.id, profileRetrofit.id), new ScheduleActivityViewModel.EventCreateSchedule() {
                @Override
                public void onSuccess() {
                    progressDialog.dismiss();
                    Toast.makeText(ScheduleActivity.this, "Đặt lịch khám thành công.", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

                @Override
                public void onError() {
                    progressDialog.dismiss();
                    Toast.makeText(ScheduleActivity.this, "Có lỗi xảy ra, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Mời nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
        }
    }
}