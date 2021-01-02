package com.longhb.do4life.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.longhb.do4life.R;
import com.longhb.do4life.databinding.ActivityDatLichBinding;

import java.util.ArrayList;
import java.util.List;

public class DatLichActivity extends AppCompatActivity {

    ActivityDatLichBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDatLichBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i + "");
        }

        binding.spinner.setItems(strings);
    }
}