package cc.cwalk.com.tab_four.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.utils.GlideUtils;

public class ActivityDetailActivity extends BaseActivity {

    @Bind(R.id.iv_image)
    ImageView ivImage;
    @Bind(R.id.tv_act_title)
    TextView tvTitle;
    @Bind(R.id.tv_manager)
    TextView tvManager;
    @Bind(R.id.tv_detial)
    TextView tvDetial;

    @Override
    protected String setTitle() {
        return "活动详情";
    }

    private GroupInfoBean bean ;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_activity_detail;
    }

    @Override
    protected void initView() {

         bean = (GroupInfoBean) getIntent().getSerializableExtra("bean");

         tvTitle.setText(bean.getActivity());
         tvDetial.setText(bean.getActivitydes());
        GlideUtils.lodeHeadImage(bean.getHead(),ivImage);
        tvManager.setText(bean.getName());

    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context xContext, GroupInfoBean groupInfoBean) {
        Intent intent = new Intent(xContext, ActivityDetailActivity.class);
        intent.putExtra("bean", groupInfoBean);
        xContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
