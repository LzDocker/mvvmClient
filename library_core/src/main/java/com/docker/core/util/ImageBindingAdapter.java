package com.docker.core.util;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.docker.commonlibrary.R;

/**
 * Created by zhangxindang on 2018/12/18.
 */

public class ImageBindingAdapter {

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }

//    @BindingAdapter({"app:imageUrl", "app:placeHolder", "app:error"})
//    public static void loadImage(ImageView imageView, String url, Drawable holderDrawable, Drawable errorDrawable) {
//        Glide.with(imageView.getContext())
//                .load(url)
//                .placeholder(holderDrawable)
//                .error(errorDrawable)
//                .into(imageView);
//    }

    @BindingAdapter({"imageUrl"})
    public static void loadimage(ImageView imageView,String url){
        Log.e("sss", "loadimage: "+url+"   -走到这里了");
        Glide.with(imageView.getContext()).load(url)
                .into(imageView);
    }


}
