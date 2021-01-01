package com.longhb.do4life.apdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.databinding.AdapterPostBinding;
import com.longhb.do4life.model.retrofit.res.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    List<Post> list;
    Event callback;

    public PostAdapter(List<Post> list, Event callback) {
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(AdapterPostBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        holder.binding.getRoot().setOnClickListener(v -> callback.clickItem(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterPostBinding binding;
        public ViewHolder( @NonNull AdapterPostBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bind(Post post){
            binding.setPost(post);
            binding.executePendingBindings(); 
        }
    }

    public interface Event{
        void clickItem(int pos);
    }
}
