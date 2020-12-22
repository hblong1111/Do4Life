package com.longhb.do4life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.longhb.do4life.R;
import com.longhb.do4life.model.MyAccount;
import com.longhb.do4life.model.ViewModelFactory;
import com.longhb.do4life.utils.CheckLoginEvent;
import com.longhb.do4life.viewmodel.HomeViewModel;
import com.longhb.do4life.viewmodel.LoginViewModel;
import com.longhb.do4life.viewmodel.SignInViewModel;

public class TestViewModelActivity extends AppCompatActivity implements CheckLoginEvent {

    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_viewmodel);

        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(LoginViewModel.class);

        viewModel.checkLogin("Long", "123",this);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError() {
        Toast.makeText(this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
    }
}