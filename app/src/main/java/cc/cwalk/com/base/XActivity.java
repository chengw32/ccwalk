package cc.cwalk.com.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import cc.cwalk.com.MyApplication;
import cc.cwalk.com.utils.EventUtil;

public abstract class XActivity extends FragmentActivity {

    protected Context xContext ;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上转场动画需要先设置这个
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }

        setContentView(setContentLayout());
        this.xContext = this ;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
        ButterKnife.bind(this);
        EventUtil.register(this);
        initTitle();
        initView();
        MyApplication.getInstance().addActivity(this);
        initData();

    }
    //EvenBus在注册的页面 必须重新这个方法 而且必须加上注解标记 @Subscribe
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Object event) {/* Do something */};

    /**
     * 设置布局
     */
    protected abstract int setContentLayout();

    /**
     * 初始化标题栏
     */
    protected abstract void initTitle();


    /**
     * 初始化布局文件中的控件
     */
    protected abstract void initView();
    /**
     * 初始化数据
     */
    protected abstract void  initData();


    @Override
    protected void onDestroy() {
        MyApplication.getInstance().removeActivity(this);
        super.onDestroy();
        ButterKnife.unbind(this);//解绑
        EventUtil.unregister(this);
    }
}
