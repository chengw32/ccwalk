package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;

public class MemberDetailActivity extends BaseActivity {


    @Override
    protected int setContentLayout() {
        return R.layout.activity_member_detail;
    }

    @Override
    protected String setTitle() {
        return "详细资料";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext,MemberDetailActivity.class));
    }
}
