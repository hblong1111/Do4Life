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
import com.longhb.do4life.model.retrofit.res.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ExamScheduleAdapter extends RecyclerView.Adapter<ExamScheduleAdapter.RecyclerViewHolder> {
    List<Schedule> schedules;

    public ExamScheduleAdapter(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ExamScheduleAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_schedule, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamScheduleAdapter.RecyclerViewHolder holder, int position) {
        holder.tv_stt.setText((position+1)+"");
        holder.tv_Name_exam.setText(schedules.get(position).profileName);
        holder.tv_day.setText(schedules.get(position).time);
    }

    @Override
    public int getItemCount() {
        return schedules.size();
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
