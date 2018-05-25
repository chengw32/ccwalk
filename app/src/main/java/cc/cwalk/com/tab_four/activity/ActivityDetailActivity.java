package cc.cwalk.com.tab_four.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.beans.ActivityBean;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.DialogUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.ToastUtils;
import cc.cwalk.com.utils.Utils;

public class ActivityDetailActivity extends BaseActivity {

    @Bind(R.id.iv_image)
    ImageView ivImage;
    @Bind(R.id.tv_act_title)
    TextView tvTitle;
    @Bind(R.id.tv_manager)
    TextView tvManager;
    @Bind(R.id.tv_detial)
    TextView tvDetial;
    @Bind(R.id.tv_adress)
    TextView tvAdress;
    @Bind(R.id.tv_endtime)
    TextView tvEndtime;
    @Bind(R.id.tv_members)
    TextView tvMembers;
    @Bind(R.id.tv_join)
    TextView tvJoin;

    @Override
    protected String setTitle() {
        return "活动详情";
    }

    private ActivityBean bean;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_activity_detail;
    }

    @Override
    protected void initView() {

        bean = (ActivityBean) getIntent().getSerializableExtra("bean");
        String name = DataUtils.getInstance().getUserById(SPUtils.getId()).name;
        if (bean.name.equals(name))tvJoin.setText("已报名");
        for (int i = 0; i < bean.member.size(); i++) {
            if (name.equals(bean.member.get(i))){tvJoin.setText("已报名");break;}
        }

            tvTitle.setText(bean.title);
        tvDetial.setText(bean.content);
        GlideUtils.lodeImage(bean.banner, ivImage);
        tvManager.setText(bean.name);
        tvAdress.setText(bean.adress);
        tvEndtime.setText(bean.endtime);
        tvMembers.setText(bean.member.toString());

    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context xContext, ActivityBean groupInfoBean) {
        Intent intent = new Intent(xContext, ActivityDetailActivity.class);
        intent.putExtra("bean", groupInfoBean);
        xContext.startActivity(intent);
    }


    @OnClick({R.id.tv_call, R.id.tv_join})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tv_call:

                DialogUtils.showTopicDialogsCustom("拨打电话 15710607777", xContext, new DialogUtils.DialogClickBack() {
                    @Override
                    public void define() {
                        Utils.callTel(xContext, "15710607777");
                    }

                    @Override
                    public void cancel() {

                    }
                });
                break;
            case R.id.tv_join:

                if ("已报名".equals(tvJoin.getText().toString()))return;

                DialogUtils.showTopicDialogsCustom("确定报名该活动吗", xContext, new DialogUtils.DialogClickBack() {
                    @Override
                    public void define() {

                        bean.member.add(0,DataUtils.getInstance().getUserById(SPUtils.getId()).name);
                        initView();

                        List<ActivityBean> activitysList = DataUtils.getInstance().getActivitysList();
                        for (int i = 0; i < activitysList.size(); i++) {
                            if (activitysList.get(i).id == bean.id){
                                activitysList.remove(i);
                                activitysList.add(i,bean);
                                SPUtils.setActivityList(GsonUtil.toJosn(activitysList));
                                EventUtil.sendEvent(EventUtil.ACT_REFRESH_group_activity,null);
                                break;
                            }

                        }

                        ToastUtils.s("报名成功");
                    }

                    @Override
                    public void cancel() {

                    }
                });
                break;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
