package cc.cwalk.com.credits;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.utils.ToastUtils;

public class CreditsStoreActivity extends BaseActivity {


    @Override
    protected int setContentLayout() {
        return R.layout.activity_credits_store;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected String setTitle() {
        return "积分商城";
    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CreditsStoreActivity.class));
    }


    @OnClick({R.id.tv_buy4, R.id.tv_buy3, R.id.tv_buy2, R.id.tv_buy1, R.id.tv_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_buy4:
            case R.id.tv_buy3:
            case R.id.tv_buy2:
            case R.id.tv_buy1:
            case R.id.tv_buy:
                ToastUtils.s("积分不足无法兑换");
                break;
        }
    }
}
