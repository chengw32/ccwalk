package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.tab_three.PublishActivity;

public class PurchaseDetailActivity extends BaseActivity {


    @Override
    protected int setContentLayout() {
        return R.layout.activity_purchase_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected String setTitle() {
        return "采购详情";
    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext,PurchaseDetailActivity.class));
    }
}
