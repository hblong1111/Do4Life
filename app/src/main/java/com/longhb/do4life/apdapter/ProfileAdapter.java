package com.longhb.do4life.apdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.R;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.RecyclerViewHolder> {
    List<ProfileRetrofit> listPro;
    Event event;

    public  ProfileAdapter(List<ProfileRetrofit> listPro, Event event) {
        this.listPro = listPro;
        this.event = event;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tvAge.setText(listPro.get(position).age + "");
        holder.tvName.setText(listPro.get(position).fullname);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.clickItem(position);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.deleteItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPro.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvAge;
        private ImageButton btnDelete;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public interface Event {
        void clickItem(int pos);

        void deleteItem(int pos);
    }
}
