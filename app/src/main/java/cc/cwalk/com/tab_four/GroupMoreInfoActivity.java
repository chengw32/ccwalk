package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;

public class GroupMoreInfoActivity extends BaseActivity {


    @Override
    protected int setContentLayout() {
        return R.layout.activity_group_more_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,GroupMoreInfoActivity.class));
    }
}
