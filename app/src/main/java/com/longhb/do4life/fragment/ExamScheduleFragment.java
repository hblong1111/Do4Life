package com.longhb.do4life.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.R;
import com.longhb.do4life.apdapter.ExamScheduleAdapter;
import com.longhb.do4life.databinding.FragmentExamScheduleBinding;
import com.longhb.do4life.model.Exam;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.json.JsonSchedule;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.model.retrofit.res.Schedule;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.utils.SharedUtils;
import com.longhb.do4life.viewmodel.ExamScheduleFragmentViewModel;
import com.longhb.do4life.viewmodel.ScheduleActivityViewModel;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExamScheduleFragment extends Fragment implements ExamScheduleAdapter.Event {

    FragmentExamScheduleBinding binding;

    ExamScheduleFragmentViewModel viewModel;

    ExamScheduleAdapter adapter;


    List<Schedule> list;
    List<ProfileRetrofit> listProfile;
    private ProgressDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dialog = Common.buildDialogLoading(getContext(), null, "Đang tải...");

        binding = FragmentExamScheduleBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(getActivity(), new ViewModelFactory()).get(ExamScheduleFragmentViewModel.class);

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

        return binding.getRoot();
    }

    private void settingRCV() {
        list = new ArrayList<>();
        adapter = new ExamScheduleAdapter(list, this);

        binding.rcv.setAdapter(adapter);
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getListSchedule().observe(getActivity(), schedules -> {
            if (schedules.size()==0){
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
    public void onClickItem(int pos) {

    }
}