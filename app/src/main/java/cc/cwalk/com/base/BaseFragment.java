package cc.cwalk.com.base;

import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cc.cwalk.com.R;

/**
 * @创建者 CSDN_LQR
 * @描述 Fragment的基类
 */
public abstract class BaseFragment extends XFragment {

    protected ImageView iv_back ;
    protected abstract int setContentLayout();
    protected View topbar ;
    @CallSuper
    @Override
    protected void initView(View v) {
        topbar = v.findViewById(R.id.top_bar);
        TextView titleView =  v.findViewById(R.id.tv_title);
        iv_back =  v.findViewById(R.id.iv_back);
        if (null != titleView)
            titleView.setText(setTitle());
    }

    public void setBarGone(){
        if (null != topbar)topbar.setVisibility(View.GONE);
    }
    protected String setTitle(){
        return "标题";
    }

}
