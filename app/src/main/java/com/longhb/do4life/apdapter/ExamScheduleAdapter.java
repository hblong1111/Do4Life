package com.longhb.do4life.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.R;
import com.longhb.do4life.model.Exam;

import java.util.ArrayList;

public class ExamScheduleAdapter extends RecyclerView.Adapter<ExamScheduleAdapter.RecyclerViewHolder> {
    private ArrayList<Exam> examList;
    Context context;

    public ExamScheduleAdapter(ArrayList<Exam> examList, Context context) {
        this.examList = examList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExamScheduleAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_schedule, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamScheduleAdapter.RecyclerViewHolder holder, int position) {
        holder.tv_stt.setText(examList.get(position).STT);
        holder.tv_Name_exam.setText(examList.get(position).Name);
        holder.tv_day.setText(examList.get(position).day);
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_stt,tv_Name_exam,tv_day;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_stt=itemView.findViewById(R.id.tv_stt_exam);
            tv_Name_exam=itemView.findViewById(R.id.tv_Name_exam);
            tv_day=itemView.findViewById(R.id.tv_day_exam);
        }
    }
}
