package com.longhb.do4life.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.longhb.do4life.R;
import com.longhb.do4life.activity.HomeActivity;
import com.longhb.do4life.apdapter.ExamScheduleAdapter;
import com.longhb.do4life.databinding.FragmentExamScheduleBinding;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.json.JsonSchedule;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.model.retrofit.res.Schedule;
import com.longhb.do4life.model.retrofit.res.ScheduleHistory;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.utils.SharedUtils;
import com.longhb.do4life.viewmodel.ExamScheduleFragmentViewModel;
import com.longhb.do4life.viewmodel.ScheduleActivityViewModel;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExamScheduleFragment extends Fragment implements ExamScheduleAdapter.Event {

    FragmentExamScheduleBinding binding;

    ExamScheduleFragmentViewModel viewModel;

    ExamScheduleAdapter adapter;


    List<Schedule> list;
    List<ProfileRetrofit> listProfile;
    private ProgressDialog dialog;
    private Dialog alertDialog1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dialog = Common.buildDialogLoading(getContext(), null, "Đang tải...");

        binding = FragmentExamScheduleBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(getActivity(), new ViewModelFactory()).get(ExamScheduleFragmentViewModel.class);

        viewModel.getListSchedule().postValue(new ArrayList<>());
        binding.spinnerProfile.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (i, s, i1, t1) -> {
            dialog.show();
            binding.tvChonHoSo.setVisibility(View.GONE);
            viewModel.getScheduleByProfileId(new JsonSchedule(listProfile.get(i1).id), new ExamScheduleFragmentViewModel.EventGetScheduleByProfileId() {
                @Override
                public void onSuccess() {
                    dialog.dismiss();
                }

                @Override
                public void onError() {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Có lỗi xảy ra, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            });
        });

        listProfile = new ArrayList<>();
        viewModel.getProfile(new JsonProfile(SharedUtils.getInstance(getContext()).getString(Common.KEY_ID_ACC, null)), new ScheduleActivityViewModel.EventGetProfile() {
            @Override
            public void onSuccess(List<ProfileRetrofit> body) {
                listProfile.addAll(body);

                List<String> strings = new ArrayList<>();

                for (ProfileRetrofit retrofit :
                        listProfile) {
                    strings.add(retrofit.fullname);
                }

                binding.spinnerProfile.setItems(strings);

                dialog.dismiss();
            }

            @Override
            public void onError() {
                dialog.dismiss();
                Toast.makeText(getContext(), "Có lỗi xảy ra, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
            }
        });
        settingRCV();


        HomeActivity.drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                binding.spinnerProfile.dismiss();
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        return binding.getRoot();
    }

    private void settingRCV() {
        list = new ArrayList<>();
        adapter = new ExamScheduleAdapter(list, this);

        binding.rcv.setAdapter(adapter);
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getListSchedule().observe(getActivity(), schedules -> {
            if (schedules.size()==0&&binding.spinnerProfile.getSelectedIndex()>=0){
                binding.tvNoItem.setVisibility(View.VISIBLE);
            }else {
                binding.tvNoItem.setVisibility(View.GONE);
            }
            list.clear();
            list.addAll(schedules);
            adapter.notifyDataSetChanged();
        });


    }

    @Override
    public void onClickItem(Schedule item) {
showDialogColor(item);
    }


    private void showDialogColor(Schedule item) {
        int width = getResources().getDisplayMetrics().widthPixels;


        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_schedule, null, false);
        TextView tvBenhNhan;
        TextView tvAge;
        TextView tvPhone;
        TextView tvKhoaKham;
        TextView tvBacSi;
        TextView tvPhong;
        TextView tvTime;
        Button btnDong;

        tvBenhNhan = view.findViewById(R.id.tvBenhNhan);
        tvAge = view.findViewById(R.id.tvAge);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvKhoaKham = view.findViewById(R.id.tvKhoaKham);
        tvBacSi = view.findViewById(R.id.tvBacSi);
        tvPhong = view.findViewById(R.id.tvPhong);
        tvTime = view.findViewById(R.id.tvTime);
        btnDong = view.findViewById(R.id.btnDong);

        tvAge.setText(item.profileAge + " tuổi");
        tvBacSi.setText(item.doctor);
        tvBenhNhan.setText(item.profileName);
        tvKhoaKham.setText(item.department);
        tvPhone.setText(item.phoneNumber);
        tvPhong.setText(item.room);
        tvTime.setText(new SimpleDateFormat("hh:mm dd/MM/yyyy").format(item.time));

        btnDong.setOnClickListener(v -> alertDialog1.dismiss());

        alertDialog1 = new Dialog(getContext());
        alertDialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog1.setContentView(view);
        alertDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog1.getWindow().getAttributes());
        lp.width = (int) (0.9f * width);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        alertDialog1.show();
        alertDialog1.getWindow().setAttributes(lp);
    }
}