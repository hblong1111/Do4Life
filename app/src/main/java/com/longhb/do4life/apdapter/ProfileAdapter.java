package com.longhb.do4life.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.R;
import com.longhb.do4life.model.JsonProfile;
import com.longhb.do4life.model.Profile;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.RecyclerViewHolder> {
    private ArrayList<Profile> listPro;
    Context context;

    public ProfileAdapter(ArrayList<Profile> listPro, Context context) {
        this.listPro = listPro;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tv_Name.setText(listPro.get(position).nameUser);
        holder.tv_birth.setText(listPro.get(position).birth);
        Picasso.with(context).load(listPro.get(position).imageUser).into(holder.circleImageProfile);
    }

    @Override
    public int getItemCount() {
        return listPro.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Name,tv_birth;
        CircleImageView circleImageProfile;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageProfile=itemView.findViewById(R.id.circleImageProfile);
            tv_Name=itemView.findViewById(R.id.tv_Name);
            tv_birth=itemView.findViewById(R.id.tv_birth);
        }
    }
}
