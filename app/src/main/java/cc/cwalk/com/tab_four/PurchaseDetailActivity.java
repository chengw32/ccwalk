package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.beans.PurchaseBean;

public class PurchaseDetailActivity extends BaseActivity {

    @Bind(R.id.tv_group_title)
    TextView tvTitle;
    @Bind(R.id.tv_des)
    TextView tvDes;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_manager)
    TextView tvManager;
    private PurchaseBean bean;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_purchase_detail;
    }

    @Override
    protected void initView() {

        bean = (PurchaseBean) getIntent().getSerializableExtra("bean");

        tvTitle.setText(bean.title);
        tvDes.setText(bean.content);
        tvMoney.setText(""+bean.money);
        tvManager.setText(bean.name);

    }

    @Override
    protected String setTitle() {
        return "采购详情";
    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context xContext, PurchaseBean groupInfoBean) {
        Intent intent = new Intent(xContext, PurchaseDetailActivity.class);
        intent.putExtra("bean", groupInfoBean);
        xContext.startActivity(intent);
    }

}
