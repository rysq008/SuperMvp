package com.ly.supermvp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ly.supermvp.R;

/**
 * <Pre>
 *     glide图片加载工具类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/28 14:07
 */
public class GlideUtil {

    public static void loadImage(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(R.mipmap.holding_icon)
                .error(R.mipmap.error_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }
}
