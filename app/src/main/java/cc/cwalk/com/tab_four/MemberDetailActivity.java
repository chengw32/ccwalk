package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.DialogUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.ToastUtils;
import cc.cwalk.com.utils.Utils;

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
    private UserBean bean;

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
        bean = (UserBean) getIntent().getSerializableExtra("bean");

        if (DataUtils.getInstance().getCreater() == 1){
            if (bean.creater == 1){
                tvDel.setVisibility(View.GONE);
            }else
            tvDel.setVisibility(View.VISIBLE);
        }else tvDel.setVisibility(View.GONE);

        tvName.setText(bean.name);
        tvCollege.setText(bean.college);
        tvSex.setText(bean.sex == 1 ? "男" : "女");
        tvGrade.setText(bean.grade);
        tvProfession.setText(bean.profession);
        tvDes.setText(bean.persondes);
        GlideUtils.lodeImage(bean.head, ivHead);


    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context xContext, UserBean groupInfoBean) {
        Intent intent = new Intent(xContext, MemberDetailActivity.class);
        intent.putExtra("bean", groupInfoBean);
        xContext.startActivity(intent);
    }


    @OnClick(R.id.tv_del)
    public void onViewClicked() {

        DialogUtils.showTopicDialogsCustom("确定移除该成员？", xContext, new DialogUtils.DialogClickBack() {
            @Override
            public void define() {
                EventUtil.sendEvent(EventUtil.REMOVE_MEMBER,bean);
                ToastUtils.s("移除成功");
                finish();
            }

            @Override
            public void cancel() {

            }
        });
    }
}
