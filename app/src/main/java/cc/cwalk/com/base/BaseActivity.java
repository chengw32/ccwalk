package cc.cwalk.com.base;


import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cc.cwalk.com.R;

/**
 * Author chen_gw
 * Date 2018/4/19 15:20
 * DES :
 */
public abstract class BaseActivity extends XActivity {

    protected View topbar;
    protected TextView tv_right;
    protected ImageView iv_right ;

    @Override
    protected void initTitle() {
        topbar = findViewById(R.id.top_bar);
        tv_right = findViewById(R.id.tv_right);
        setRightText("");
        if(null != tv_right)
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightClick();
            }
        });
        TextView titleView = findViewById(R.id.tv_title);
        iv_right =  findViewById(R.id.iv_right);
        if (null != iv_right)
            iv_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv_right.setVisibility(View.VISIBLE);
                    onRightClick();
                }
            });
        View back = findViewById(R.id.iv_back);
        if (null != back)
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        if (null != titleView)
            titleView.setText(setTitle());

    }

    public  void onRightClick(){};

    protected String setTitle() {
        return "";
    }

    public void setRightText(String text) {
        if (TextUtils.isEmpty(text))return;
        if (null != tv_right) tv_right.setText(text);
    }
    //右边的点击事件
    protected void setRightVisable(){
        if (null != iv_right)iv_right.setVisibility(View.VISIBLE);
    }
}
