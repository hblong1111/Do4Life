package com.longhb.do4life.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longhb.do4life.R;
import com.longhb.do4life.apdapter.NotificationAdapter;
import com.longhb.do4life.model.Notification;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }
    RecyclerView rcv_notification;
    NotificationAdapter notifiAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.notification_fragment, container, false);
        rcv_notification=view.findViewById(R.id.rcv_notification);
        NotificationShow();
        return view;
    }
    private void NotificationShow(){
        ArrayList<Notification> notifiList=new ArrayList<>();
        notifiList.add(new Notification("Thời gian","Hôm nay bạn có lịch khám ","03/01/2021"));
        notifiList.add(new Notification("Thời gian 1","Hôm nay bạn có lịch khám ","03/01/2021"));
        notifiList.add(new Notification("Thời gian 2","Hôm nay bạn có lịch khám ","03/01/2021"));
        notifiList.add(new Notification("Thời gian 3","Hôm nay bạn có lịch khám ","03/01/2021"));
        notifiAdapter=new NotificationAdapter(notifiList,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcv_notification.setAdapter(notifiAdapter);
        rcv_notification.setLayoutManager(layoutManager);
    }
}