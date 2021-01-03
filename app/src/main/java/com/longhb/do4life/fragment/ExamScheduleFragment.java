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
import com.longhb.do4life.model.retrofit.json.JsonSchedule;
import com.longhb.do4life.model.retrofit.res.Schedule;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.viewmodel.ExamScheduleFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExamScheduleFragment extends Fragment{

    FragmentExamScheduleBinding binding;

    ExamScheduleFragmentViewModel viewModel;

    ExamScheduleAdapter adapter;
    List<Schedule> list;
    private ProgressDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dialog = Common.buildDialogLoading(getContext(), null, "Đang tải...");

        binding = FragmentExamScheduleBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(getActivity(), new ViewModelFactory()).get(ExamScheduleFragmentViewModel.class);

        list = new ArrayList<>();
        adapter = new ExamScheduleAdapter(list);

        binding.rcv.setAdapter(adapter);
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getListSchedule().observe(getActivity(),schedules -> {
            list.clear();
            list.addAll(schedules);
            adapter.notifyDataSetChanged();
        });

        viewModel.getScheduleByProfileId(new JsonSchedule("5fefee3311a13400176c7367"), new ExamScheduleFragmentViewModel.EventGetScheduleByProfileId() {
            @Override
            public void onSuccess() {
                dialog.dismiss();
            }

            @Override
            public void onError() {
                dialog.dismiss();
                Toast.makeText(getContext() , "Có lỗi xảy ra, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}