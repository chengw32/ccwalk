package cc.cwalk.com.tab_four.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;

public class ActivityDetailActivity extends BaseActivity {

    @Override
    protected String setTitle() {
        return "活动详情";
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_activity_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext,ActivityDetailActivity.class));
    }
}
