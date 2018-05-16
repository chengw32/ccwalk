package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.ToastUtils;

public class MemberDetailActivity extends BaseActivity {

    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_ni_name)
    TextView tvNiName;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_grade)
    TextView tvGrade;
    @Bind(R.id.tv_college)
    TextView tvCollege;
    @Bind(R.id.tv_profession)
    TextView tvProfession;
    @Bind(R.id.tv_join_time)
    TextView tvJoinTime;
    @Bind(R.id.tv_des)
    TextView tvDes;
    @Bind(R.id.tv_del)
    TextView tvDel;
    private GroupInfoBean bean;

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
        bean = (GroupInfoBean) getIntent().getSerializableExtra("bean");

        if (SPUtils.getCreat() == 1){
            if (bean.getCreater() == 1){
                tvDel.setVisibility(View.GONE);
            }else
            tvDel.setVisibility(View.VISIBLE);
        }else tvDel.setVisibility(View.GONE);

        tvName.setText(bean.getName());
        tvNiName.setText(bean.getNiname());
        tvCollege.setText(bean.getCollege());
        tvSex.setText(bean.getSex() == 1 ? "男" : "女");
        tvGrade.setText(bean.getGrade());
        tvProfession.setText(bean.getProfession());
        tvDes.setText(bean.getPersondes());
        GlideUtils.lodeHeadImage(bean.getHead(), ivHead);


    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context xContext, GroupInfoBean groupInfoBean) {
        Intent intent = new Intent(xContext, MemberDetailActivity.class);
        intent.putExtra("bean", groupInfoBean);
        xContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_del)
    public void onViewClicked() {
        EventUtil.sendEvent(EventUtil.REMOVE_MEMBER,bean);
        finish();
    }
}
