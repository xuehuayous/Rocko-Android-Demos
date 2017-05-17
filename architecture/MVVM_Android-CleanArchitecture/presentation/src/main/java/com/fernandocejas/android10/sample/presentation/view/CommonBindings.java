package com.fernandocejas.android10.sample.presentation.view;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class CommonBindings {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext()).load(url).error(error)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
    }

}