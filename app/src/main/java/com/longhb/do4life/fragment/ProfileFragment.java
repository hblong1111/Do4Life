package com.longhb.do4life.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.R;
import com.longhb.do4life.activity.ProfileDetail;
import com.longhb.do4life.activity.ThemHoSoActivity;
import com.longhb.do4life.apdapter.ProfileAdapter;
import com.longhb.do4life.databinding.FragmentProfileBinding;
import com.longhb.do4life.model.Profile;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.utils.SharedUtils;
import com.longhb.do4life.viewmodel.ProfileFragmentViewModel;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment implements View.OnClickListener, ProfileAdapter.Event {
    private static final int CODE_ADD_PROFILE = 0;
    FragmentProfileBinding binding;

    ProfileFragmentViewModel viewModel;
    private AlertDialog alertDialog;

    List<ProfileRetrofit> list;
    ProfileAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(ProfileFragmentViewModel.class);


        settingRCV();

        binding.tvAddProfile.setOnClickListener(this);

        viewModel.getProfile(new JsonProfile(SharedUtils.getInstance(getContext()).getString(Common.KEY_ID_ACC, null)));
        Log.d("hblong", "ProfileFragment | onCreateView: " + SharedUtils.getInstance(getContext()).getString(Common.KEY_ID_ACC, null));

        return binding.getRoot();
    }

    private void settingRCV() {
        list = new ArrayList<>();
        adapter = new ProfileAdapter(list, this);

        binding.rcv.setAdapter(adapter);
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getListProfile().observe(getActivity(), profileRetrofits -> {
            list.clear();
            list.addAll(profileRetrofits);
            adapter.notifyDataSetChanged();
            Log.d("hblong", "ProfileFragment | settingRCV: " + profileRetrofits.size());
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAddProfile:
                addProfile();
                break;
        }
    }

    private void addProfile() {
        Intent i = new Intent(getContext(), ThemHoSoActivity.class);
        startActivityForResult(i, CODE_ADD_PROFILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_ADD_PROFILE) {
            if (resultCode == Activity.RESULT_OK) {
                ProgressDialog progressDialog = Common.buildDialogLoading(getContext(), null, "Đang tải...");
                JsonProfile profile = (JsonProfile) data.getSerializableExtra(Common.CODE_PUT_PROFILE);
                viewModel.createProfile(profile, new ProfileFragmentViewModel.EventCreate() {
                    @Override
                    public void onSuccess(boolean res) {
                        alertDialog = Common.showDialogAlert(getContext(), "Tạo hồ sơ thành công.", "ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                                progressDialog.dismiss();

                                viewModel.getProfile(new JsonProfile(SharedUtils.getInstance(getContext()).getString(Common.KEY_ID_ACC, null)));
                            }
                        });
                    }

                    @Override
                    public void onError() {
                        alertDialog = Common.showDialogAlert(getContext(), "Có lỗi xảy ra, vui lòng thử lại.", "ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                                progressDialog.dismiss();
                            }
                        });
                    }
                });
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getProfile(new JsonProfile(SharedUtils.getInstance(getContext()).getString(Common.KEY_ID_ACC, null)));

    }

    @Override
    public void clickItem(int pos) {
        Intent intent = new Intent(getContext(), ProfileDetail.class);
        intent.putExtra(Common.CODE_PUT_PROFILE, list.get(pos));
        startActivity(intent);
    }
}