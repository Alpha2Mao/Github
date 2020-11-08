package com.example.mvvmgithub.bindingadapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.mvvmgithub.R;
import com.squareup.picasso.Picasso;

public class ImageViewBindingAdapter {
    @BindingAdapter(value = {"image", "defaultImageResource"}, requireAll = false)
    public static void setImage(ImageView imageView, String imageUrl, int imageResource){
        if (!TextUtils.isEmpty(imageUrl)){
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView);
        } else {
            imageView.setImageResource(imageResource);
        }
    }
}
