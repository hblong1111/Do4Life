package com.longhb.do4life.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.longhb.do4life.R;
import com.longhb.do4life.apdapter.PostAdapter;
import com.longhb.do4life.databinding.FragmentHomeBinding;
import com.longhb.do4life.model.Post;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.viewmodel.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    HomeFragmentViewModel viewModel;

    List<Post> postList;
    PostAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(HomeFragmentViewModel.class);


        postList = new ArrayList<>();

        ProgressDialog dialog = Common.buildDialogLoading(getContext(), "Đang Tải", "Vui lòng chờ...");

        dialog.show();

        adapter = new PostAdapter(postList, pos -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(postList.get(pos).url));
            startActivity(browserIntent);
        });

        binding.rcv.setAdapter(adapter);
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getAllPost(() -> {
            Toast.makeText(getContext(), "Có lỗi xảy ra, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        viewModel.listPost.observe((LifecycleOwner) getContext(), posts -> {
            postList.addAll(posts);
            adapter.notifyDataSetChanged();
                dialog.dismiss();
        });


        return binding.getRoot();
    }
}