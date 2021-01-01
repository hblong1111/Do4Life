package com.longhb.do4life.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.longhb.do4life.R;
import com.longhb.do4life.activity.ConfirmAccountActivity;
import com.longhb.do4life.apdapter.PostAdapter;
import com.longhb.do4life.databinding.FragmentHomeBinding;
import com.longhb.do4life.model.Post;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.utils.SharedUtils;
import com.longhb.do4life.viewmodel.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {
    FragmentHomeBinding binding;
    HomeFragmentViewModel viewModel;

    List<Post> postList;
    PostAdapter adapter;

    SharedUtils sharedUtils;
    private AlertDialog alertDialog;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(HomeFragmentViewModel.class);

        sharedUtils = SharedUtils.getInstance(getContext());


        settingRCVPost();

        binding.btnDatLich.setOnClickListener(this);


        return binding.getRoot();
    }

    private void settingRCVPost() {
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDatLich:
                datLich();
                break;
        }
    }

    private void datLich() {
        String fCMND = sharedUtils.getString(Common.KEY_FONT_CMND_ACC, null);
        String bCMND = sharedUtils.getString(Common.KEY_BACK_CMND_ACC, null);
        boolean isChecked = sharedUtils.getBoolean(Common.KEY_CHECKED_ACC, false);
        if (fCMND==null||bCMND==null) {
            showDialogConfirm();
        }else if (!isChecked){
            showDialogAlert();
        }else {
            //todo: đặt lịch khám ở đây
        }

    }
 
    private void showDialogConfirm() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm_acc, null, false);
        Button btnXacNhan;
        btnXacNhan = view.findViewById(R.id.btnXacNhan);

        Dialog alertDialog = new Dialog(getContext());
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(view);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        btnXacNhan.setOnClickListener(v -> {
            alertDialog.dismiss();
            startActivity(new Intent(getContext(), ConfirmAccountActivity.class));
        });

    }

    private void showDialogAlert() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_alert, null, false);
        Button btnXacNhan;
        btnXacNhan = view.findViewById(R.id.btnXacNhan);

        Dialog alertDialog = new Dialog(getContext());
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(view);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        btnXacNhan.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

    }
}