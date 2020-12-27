package com.longhb.do4life.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.longhb.do4life.R;
import com.longhb.do4life.apdapter.HistoryAdapter;
import com.longhb.do4life.model.HistoryExam;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    RecyclerView rcv_history;
    HistoryAdapter historyAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.history_fragment, container, false);
        rcv_history=view.findViewById(R.id.rcv_History);

        ShowDataHis();
        return  view;

    }
    private  void ShowDataHis(){
        ArrayList<HistoryExam> hisList=new ArrayList<>();
        hisList.add(new HistoryExam("1", "U bướu", "25/09/2020"));
        hisList.add(new HistoryExam("2", "U bướu 1", "25/10/2020"));
        hisList.add(new HistoryExam("3", "U bướu 2", "15/10/2020"));
        hisList.add(new HistoryExam("4", "U bướu 3", "26/10/2020"));
        hisList.add(new HistoryExam("5", "U bướu 4", "25/09/2020"));
        hisList.add(new HistoryExam("6", "U bướu 5", "25/09/2020"));
        hisList.add(new HistoryExam("7", "U bướu 6", "25/09/2020"));
        hisList.add(new HistoryExam("8", "U bướu 7", "25/09/2020"));
        hisList.add(new HistoryExam("9", "U bướu 8", "25/09/2020"));
        hisList.add(new HistoryExam("10", "U bướu f", "25/09/2020"));
        hisList.add(new HistoryExam("11", "U bướu a", "25/09/2020"));
        hisList.add(new HistoryExam("12", "U bướu f", "25/09/2020"));

        historyAdapter=new HistoryAdapter(hisList, getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcv_history.setAdapter(historyAdapter);
        rcv_history.setLayoutManager(layoutManager);
    }
}