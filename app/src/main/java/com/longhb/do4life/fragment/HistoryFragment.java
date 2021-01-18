package com.longhb.do4life.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.longhb.do4life.R;
import com.longhb.do4life.apdapter.HistoryAdapter;
import com.longhb.do4life.databinding.HistoryFragmentBinding;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.json.JsonScheduleHistory;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.model.retrofit.res.ScheduleHistory;
import com.longhb.do4life.utils.Common;
import com.longhb.do4life.utils.SharedUtils;
import com.longhb.do4life.viewmodel.HistoryFragmentViewModel;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements HistoryFragmentViewModel.Event, HistoryAdapter.Event {
    HistoryFragmentBinding binding;
    HistoryFragmentViewModel viewModel;
    private ProgressDialog dialog;
    HistoryAdapter adapter;
    AlertDialog alertDialog;
    List<ScheduleHistory> list;
    List<String> stringListIDProfile;
    private Dialog alertDialog1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = HistoryFragmentBinding.inflate(inflater);

        viewModel = new ViewModelProvider((ViewModelStoreOwner) getContext(), new ViewModelFactory()).get(HistoryFragmentViewModel.class);

        viewModel.setCallback(this);

        dialog = Common.buildDialogLoading(getContext(), "", "Đang tải...");

        viewModel.getProfile(new JsonProfile(SharedUtils.getInstance(getContext()).getString(Common.KEY_ID_ACC, null)));


        createData();

        settingRCV();

        binding.spinnerProfile.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (i, s, i1, t1) -> {
            Log.d("hblong", "HistoryFragment | onCreateView: " + stringListIDProfile.get(i1));
            viewModel.getScheduleHistoryByProfileId(new JsonScheduleHistory(stringListIDProfile.get(i1)));
        });


        return binding.getRoot();
    }


    private void settingRCV() {
        binding.rcvHistory.setAdapter(adapter);
        binding.rcvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void createData() {
        stringListIDProfile = new ArrayList<>();
        list = new ArrayList<>();
        adapter = new HistoryAdapter(list, this);

        viewModel.getList().observe((LifecycleOwner) getContext(), scheduleHistories -> {
            list.clear();
            list.addAll(scheduleHistories);
            adapter.notifyDataSetChanged();

        });
    }


    @Override
    public void onSuccess() {
        dialog.dismiss();
    }

    @Override
    public void onError() {
        alertDialog = Common.showDialogAlert(getContext(), "Có lỗi xảy ra, kiểm tra kết nối.", "ok", (dialog, which) -> {
            alertDialog.dismiss();
        });
        dialog.dismiss();

    }

    @Override
    public void noResponse() {
        alertDialog = Common.showDialogAlert(getContext(), "Hồ sơ chưa có lịch khám nào.", "ok", (dialog, which) -> {
            alertDialog.dismiss();
        });
        dialog.dismiss();

    }

    @Override
    public void noResponseProfile() {
        alertDialog = Common.showDialogAlert(getContext(), "Bạn chưa có hồ sơ nào, vui lòng quay lại tạo hồ sơ.", "ok", (dialog, which) -> alertDialog.dismiss());
        dialog.dismiss();
    }

    @Override
    public void onSuccessProfile(List<ProfileRetrofit> listProfile) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < listProfile.size(); i++) {
            strings.add(listProfile.get(i).fullname + " - " + listProfile.get(i).age + " tuổi");
            stringListIDProfile.add(listProfile.get(i).id);
        }
        binding.spinnerProfile.setItems(strings);

        dialog.dismiss();
    }

    @Override
    public void clickItem(ScheduleHistory item) {
        showDialogColor(item);
    }

    private void showDialogColor(ScheduleHistory item) {
        int width = getResources().getDisplayMetrics().widthPixels;


        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_history_schedule, null, false);
        TextView tvBenhNhan;
        TextView tvAge;
        TextView tvPhone;
        TextView tvKhoaKham;
        TextView tvBacSi;
        TextView tvPhong;
        TextView tvTime;
        TextView tvKetQua;
        Button btnDong;

        tvBenhNhan = view.findViewById(R.id.tvBenhNhan);
        tvAge = view.findViewById(R.id.tvAge);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvKhoaKham = view.findViewById(R.id.tvKhoaKham);
        tvBacSi = view.findViewById(R.id.tvBacSi);
        tvPhong = view.findViewById(R.id.tvPhong);
        tvTime = view.findViewById(R.id.tvTime);
        tvKetQua = view.findViewById(R.id.tvKetQua);
        btnDong = view.findViewById(R.id.btnDong);

        tvAge.setText(item.age + " tuổi");
        tvBacSi.setText(item.doctor);
        tvBenhNhan.setText(item.fullname);
        tvKetQua.setText(item.result);
        tvKhoaKham.setText(item.department);
        tvPhone.setText(item.phoneNumber);
        tvPhong.setText(item.room);
        tvTime.setText(new SimpleDateFormat("hh:mm dd/MM/yyyy").format(item.time));

        btnDong.setOnClickListener(v -> alertDialog1.dismiss());

        alertDialog1 = new Dialog(getContext());
        alertDialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog1.setContentView(view);
        alertDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog1.getWindow().getAttributes());
        lp.width = (int) (0.9f * width);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        alertDialog1.show();
        alertDialog1.getWindow().setAttributes(lp);
    }
}