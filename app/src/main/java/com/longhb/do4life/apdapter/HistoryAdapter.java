package com.longhb.do4life.apdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.R;
import com.longhb.do4life.model.retrofit.res.ScheduleHistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.RecyclerViewHolder> {
    List<ScheduleHistory> list;

    SimpleDateFormat format;

    public HistoryAdapter(List<ScheduleHistory> historyList ) {
        this.list = historyList;
        format = new SimpleDateFormat("dd/MM/yyyy");
    }

    @NonNull
    @Override
    public HistoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.RecyclerViewHolder holder, int position) {
        holder.tv_STT_his.setText((position+1)+"");
        holder.tv_Name_Exam.setText(list.get(position).fullname);
        holder.tv_Day_Exam.setText(format.format(new Date(list.get(position).time)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_STT_his, tv_Name_Exam, tv_Day_Exam;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_STT_his=itemView.findViewById(R.id.tv_stt_history);
            tv_Name_Exam=itemView.findViewById(R.id.tv_faculty_history);
            tv_Day_Exam=itemView.findViewById(R.id.tv_day_history);
        }
    }
}
