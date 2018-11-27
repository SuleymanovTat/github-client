package ru.suleymanovtat.githubclient.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CustomBindingAdapter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String urlImage) {
        Glide.with(imageView.getContext()).load(urlImage).into(imageView);
    }
}
