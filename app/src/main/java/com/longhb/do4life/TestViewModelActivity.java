package com.longhb.do4life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.res.MyAccount;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.utils.CheckLoginEvent;
import com.longhb.do4life.viewmodel.LoginViewModel;
import com.longhb.do4life.viewmodel.ProfileFragmentViewModel;

public class TestViewModelActivity extends AppCompatActivity   {

    ProfileFragmentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_viewmodel);

        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(ProfileFragmentViewModel.class);

//        viewModel.createProfile(new JsonProfile("5fef4687c02af400173bf000","HBLONG",2,"0123"));
        viewModel.getProfile(new JsonProfile("5fef4687c02af400173bf000"));
//        viewModel.updateProfile(new JsonProfile("5fef6da319dac900170f5d7c",0,"123","21"));
        viewModel.updateProfile(new JsonProfile("5fef6da319dac900170f5d7c",null));

    }

}