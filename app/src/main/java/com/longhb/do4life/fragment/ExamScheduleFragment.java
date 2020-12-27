package com.longhb.do4life.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.R;
import com.longhb.do4life.apdapter.ExamScheduleAdapter;
import com.longhb.do4life.model.Exam;

import java.util.ArrayList;

public class ExamScheduleFragment extends Fragment{
    RecyclerView rcv_Exam;
    ExamScheduleAdapter examAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_exam_schedule, container, false);
        rcv_Exam=root.findViewById(R.id.rcv_ExamSchedule);
        ShowData();
        return root;
    }
    private void ShowData(){
        ArrayList<Exam> arrayExam=new ArrayList<>();
        arrayExam.add(new Exam("1","Bui Hung ","06/08/2020"));
        arrayExam.add(new Exam("2","Bui Hung 1","16/08/2020"));
        arrayExam.add(new Exam("3","Bui Hung 2","26/08/2020"));
        arrayExam.add(new Exam("4","Bui Hung 3","06/09/2020"));
        arrayExam.add(new Exam("5","Bui Hung 4","16/09/2020"));
        arrayExam.add(new Exam("6","Bui Hung 5","26/09/2020"));
        arrayExam.add(new Exam("7","Bui Hung 6","06/10/2020"));
        arrayExam.add(new Exam("8","Bui Hung 6","06/10/2020"));
        arrayExam.add(new Exam("9","Bui Hung 6","06/10/2020"));
        arrayExam.add(new Exam("10","Bui Hung 6","06/10/2020"));
        arrayExam.add(new Exam("11","Bui Hung 6","06/10/2020"));
        arrayExam.add(new Exam("12","Bui Hung 6","06/10/2020"));

        examAdapter=new ExamScheduleAdapter(arrayExam, getContext());
        LinearLayoutManager linearLayout=new LinearLayoutManager(getContext());
        rcv_Exam.setAdapter(examAdapter);
        rcv_Exam.setLayoutManager(linearLayout);
    }
}