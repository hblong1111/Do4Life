package com.longhb.do4life.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.longhb.do4life.R;
import com.longhb.do4life.apdapter.ProfileAdapter;
import com.longhb.do4life.model.Profile;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    TextView tvAddProfile;
    RecyclerView rcvProfile;
    ProfileAdapter profileAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        tvAddProfile=root.findViewById(R.id.tvAddProfile);
        rcvProfile=root.findViewById(R.id.rcv_Profile);

        tvAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getData();
        return root;
    }
    private void getData(){
        ArrayList<Profile> prolist=new ArrayList<>();
        prolist.add(new Profile("NAme", R.drawable.phuongly, "03/02/1000"));
        prolist.add(new Profile("NAme", R.drawable.phuongly, "03/02/1000"));
        prolist.add(new Profile("NAme", R.drawable.phuongly, "03/02/1000"));
        prolist.add(new Profile("NAme", R.drawable.phuongly, "03/02/1000"));
        prolist.add(new Profile("NAme", R.drawable.phuongly, "03/02/1000"));
        prolist.add(new Profile("NAme", R.drawable.phuongly, "03/02/1000"));
        prolist.add(new Profile("NAme", R.drawable.phuongly, "03/02/1000"));
        prolist.add(new Profile("NAme", R.drawable.phuongly, "03/02/1000"));
        prolist.add(new Profile("NAme", R.drawable.phuongly, "03/02/1000"));
        prolist.add(new Profile("NAme", R.drawable.phuongly, "03/02/1000"));

        profileAdapter=new ProfileAdapter(prolist, getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcvProfile.setAdapter(profileAdapter);
        rcvProfile.setLayoutManager(layoutManager);
    }
}