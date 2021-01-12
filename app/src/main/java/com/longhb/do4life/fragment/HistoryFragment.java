package com.longhb.do4life.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.longhb.do4life.R;
import com.longhb.do4life.apdapter.HistoryAdapter;
import com.longhb.do4life.databinding.HistoryFragmentBinding;
import com.longhb.do4life.model.HistoryExam;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.model.retrofit.json.JsonScheduleHistory;
import com.longhb.do4life.viewmodel.HistoryFragmentViewModel;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    HistoryFragmentBinding binding;

    HistoryFragmentViewModel viewModel;

    String idProfile;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = HistoryFragmentBinding.inflate(inflater);

        viewModel=new ViewModelProvider((ViewModelStoreOwner) getContext(),new ViewModelFactory()).get(HistoryFragmentViewModel.class);

        viewModel.getScheduleHistoryByProfileId(new JsonScheduleHistory(""));


        return  binding.getRoot();
    }
}