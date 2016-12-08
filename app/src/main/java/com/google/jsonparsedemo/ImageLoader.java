package com.google.jsonparsedemo;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class ImageLoader {
    public static void with(String imageUrl, ImageView imageView,Context context){
        Picasso.with(context).load(imageUrl).into(imageView);
    }
}
