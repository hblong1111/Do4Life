package com.longhb.do4life.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.R;
import com.longhb.do4life.model.HistoryExam;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.RecyclerViewHolder> {
    ArrayList<HistoryExam> historyList;
    Context context;

    public HistoryAdapter(ArrayList<HistoryExam> historyList, Context context) {
        this.historyList = historyList;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.RecyclerViewHolder holder, int position) {
        holder.tv_STT_his.setText(historyList.get(position).STT_history);
        holder.tv_Name_Exam.setText(historyList.get(position).name_Exam);
        holder.tv_Day_Exam.setText(historyList.get(position).day_Exam);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_STT_his, tv_Name_Exam, tv_Day_Exam;
        ImageButton btn_delete;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_STT_his=itemView.findViewById(R.id.tv_stt_history);
            tv_Name_Exam=itemView.findViewById(R.id.tv_faculty_history);
            tv_Day_Exam=itemView.findViewById(R.id.tv_day_history);
            btn_delete=itemView.findViewById(R.id.delete_his);
        }
    }
}
