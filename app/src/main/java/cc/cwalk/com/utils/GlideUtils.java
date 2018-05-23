package cc.cwalk.com.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import cc.cwalk.com.MyApplication;
import cc.cwalk.com.R;

/**
 * Created by Chen on 2018/4/15.
 */

public class GlideUtils {

    public static void  lodeImage(String url , ImageView mCoverImage){
        Glide.with(MyApplication.getContext())
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1000000)
                                .centerCrop()
                                .error(R.mipmap.default_header)
                                )
                .load(url)
                .into(mCoverImage);

    }

}
