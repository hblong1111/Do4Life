package com.longhb.do4life.utils;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import kotlin.jvm.JvmStatic;

public class MyBindingAdapter {
    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String url){
        Glide.with(view.getContext()).load(url).into(view);

    }
}
