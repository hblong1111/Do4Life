package com.longhb.do4life.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.R;
import com.longhb.do4life.model.Notification;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.RecyclerViewHolder> {
    ArrayList<Notification> arrayNotifi;
    Context context;

    public NotificationAdapter(ArrayList<Notification> arrayNotifi, Context context) {
        this.arrayNotifi = arrayNotifi;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.RecyclerViewHolder holder, int position) {
        holder.tv_title.setText(arrayNotifi.get(position).Title);
        holder.tv_content.setText(arrayNotifi.get(position).Content);
        holder.tv_day.setText(arrayNotifi.get(position).Time);
    }

    @Override
    public int getItemCount() {
        return arrayNotifi.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title,tv_content,tv_day;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title_notifi);
            tv_content=itemView.findViewById(R.id.tv_content_notifi);
            tv_day=itemView.findViewById(R.id.tv_day_notifi);
        }
    }
}
