package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.tab_four.activity.GroupActivityActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.EventUtil;

public class MyGroupActivity extends BaseActivity {


    @Bind(R.id.ll_join)
    LinearLayout llJoin;
    @Bind(R.id.ll_expenditure)
    LinearLayout llexpenditure;
    @Bind(R.id.tv_join_num)
    TextView tvJoinNum;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_my_group;
    }

    @Override
    protected void initView() {

        if (DataUtils.getInstance().getCreater() != 1) {
            llJoin.setVisibility(View.GONE);
            llexpenditure.setVisibility(View.GONE);
        }

        tvJoinNum.setText("入团申请 ("+DataUtils.getInstance().getJoinList().size()+")");

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.ACT_REFRESH_group_member.equals(event.getAction()))
            tvJoinNum.setText("入团申请 ("+DataUtils.getInstance().getJoinList().size()+")");
    }

    @Override
    protected String setTitle() {
        return "我的社团";
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyGroupActivity.class));
    }


    @OnClick({R.id.ll_purchase, R.id.ll_expenditure, R.id.ll_activity, R.id.ll_inof_more, R.id.ll_members, R.id.ll_join, R.id.ll_notice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_members:
                GroupMemberActivity.startActivity(xContext);
                break;
            case R.id.ll_join:
                JoinGroupActivity.startActivity(xContext);
                break;
            case R.id.ll_notice:
                GroupNoticeActivity.startActivity(xContext);
                break;
            case R.id.ll_inof_more:
                GroupInfoActivity.startActivity(xContext);
                break;
            case R.id.ll_activity:
                GroupActivityActivity.startActivity(xContext);
                break;
            case R.id.ll_expenditure:
                ExpenditureActivity.startActivity(xContext);
                break;
            case R.id.ll_purchase:
                PurchaseActivity.startActivity(xContext);
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
