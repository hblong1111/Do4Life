package com.longhb.do4life.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityHomeBinding;
import com.longhb.do4life.fragment.AboutFragment;
import com.longhb.do4life.fragment.ExamScheduleFragment;
import com.longhb.do4life.fragment.HistoryFragment;
import com.longhb.do4life.fragment.HomeFragment;
import com.longhb.do4life.fragment.ProfileFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityHomeBinding binding;

    public static DrawerLayout drawer;

    Fragment fragment;
    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawer = binding.drawerLayout;

        fragment = new HomeFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frame, fragment).commit();

        binding.navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_logout) {
            showDialogLogOut();
            return false;
        } else {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.nav_profile:
                    fragment = new ProfileFragment();
                    break;
                case R.id.nav_scheduleExam:
                    fragment = new ExamScheduleFragment();
                    break;
                case R.id.nav_historyExam:
                    fragment = new HistoryFragment();
                    break;
                case R.id.nav_about:
                    fragment = new AboutFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
            binding.drawerLayout.closeDrawer(binding.navView);
        }
        return true;
    }

    private void showDialogLogOut() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;


        View view = LayoutInflater.from(this).inflate(R.layout.dialog_log_out, null, false);
        Button btnCancel;
        Button btnOk;
        btnCancel = view.findViewById(R.id.btnCancel);
        btnOk = view.findViewById(R.id.btnOk);

        btnCancel.setOnClickListener(v -> alertDialog.dismiss());
        btnOk.setOnClickListener(v -> {
            alertDialog.dismiss();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        alertDialog = new Dialog(this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(view);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width = (int) (0.9f * width);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        alertDialog.show();
        alertDialog.getWindow().setAttributes(lp);
    }
}