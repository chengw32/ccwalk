package cc.cwalk.com.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.LogUtils;

/**
 * Created by Chen on 2018/4/19.
 */

public abstract class XFragment extends Fragment {
    protected Context xContext ;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setContentLayout(), null);
        ButterKnife.bind(this,view);//绑定framgent
        xContext = getActivity();
        EventUtil.register(this);
        LogUtils.e("onCreateView");
        initView(view);
        return view;
    }

    //EvenBus在注册的页面 必须重新这个方法 而且必须加上注解标记 @Subscribe
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventUtil.BaseEvent event) {/* Do something */};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.e("onActivityCreated");
    }

    @Override
    public void onDestroy() {
        ButterKnife.unbind(this);//解绑
        EventUtil.unregister(this);
        super.onDestroy();
    }

    protected abstract int setContentLayout();
    protected abstract void initView(View view);
}
