package cc.cwalk.com.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


import butterknife.ButterKnife;
import cc.cwalk.com.MyApplication;
import cc.cwalk.com.utils.EventUtil;

public abstract class XActivity extends Activity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上转场动画需要先设置这个
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }

        setContentView();
        ButterKnife.bind(this);
        EventUtil.register(this);
        initTitle();
        initView();
        MyApplication.getInstance().addActivity(this);

    }


    /**
     * 设置布局
     */
    protected abstract void setContentView();

    /**
     * 初始化标题栏
     */
    protected abstract void initTitle();


    /**
     * 初始化布局文件中的控件
     */
    protected abstract void initView();


    @Override
    protected void onDestroy() {
        MyApplication.getInstance().removeActivity(this);
        super.onDestroy();
        ButterKnife.unbind(this);//解绑
        EventUtil.unregister(this);
    }
}
