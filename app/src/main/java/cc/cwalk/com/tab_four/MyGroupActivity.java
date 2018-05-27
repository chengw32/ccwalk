package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.tab_four.activity.GroupActivityActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.DialogUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.SPUtils;

public class MyGroupActivity extends BaseActivity {


    @Bind(R.id.ll_join)
    LinearLayout llJoin;
    @Bind(R.id.ll_expenditure)
    LinearLayout llexpenditure;
    @Bind(R.id.tv_join_num)
    TextView tvJoinNum;
    @Bind(R.id.tv_join)
    TextView mTvJoin;

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

        refreshUI();

    }

    @Override
    protected void initData() {

    }


    private void refreshUI(){
            tvJoinNum.setText("入团申请 (" + DataUtils.getInstance().getJoinList().size() + ")");

            if (isMember())mTvJoin.setVisibility(View.GONE);
            else if (isJoin()){
                mTvJoin.setVisibility(View.VISIBLE);
                mTvJoin.setText("申请中...");
            }

    }

    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.ACT_REFRESH_group_member.equals(event.getAction()))
            refreshUI();
    }

    @Override
    protected String setTitle() {
        return "我的社团";
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyGroupActivity.class));
    }


    @OnClick({R.id.tv_join,R.id.ll_purchase, R.id.ll_expenditure, R.id.ll_activity, R.id.ll_inof_more, R.id.ll_members, R.id.ll_join, R.id.ll_notice})
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
            case R.id.tv_join:
                if (isJoin())return;
                DialogUtils.showTopicDialogsCustom("确定加入？", xContext, new DialogUtils.DialogClickBack() {
                    @Override
                    public void define() {
                        //把注册用户添加到协会申请列表
                        List<UserBean> userList = DataUtils.getInstance().getUserList();
                        for (int i = 0; i < userList.size(); i++) {
                            if (userList.get(i).id == SPUtils.getId()){
                                List jooinList = DataUtils.getInstance().getJoinList();
                                jooinList.add(userList.get(i));
                                SPUtils.setJoinlist(GsonUtil.toJosn(jooinList));
                                refreshUI();
                            }
                        }
                    }

                    @Override
                    public void cancel() {

                    }
                });
                break;
        }
    }

    /**
     * Creat By_Chen
     * Time 2018/5/26 22:18
     * Des 是否是成员
     * */
    private boolean isMember(){
        List<UserBean> groupMemberList = DataUtils.getInstance().getGroupMemberList();
        for (int i = 0; i < groupMemberList.size() ; i++) {
            if (SPUtils.getId() == groupMemberList.get(i).id)return true ;
        }

        return false;
    }

    /**
     * Creat By_Chen
     * Time 2018/5/26 22:18
     * Des 是否在申请加入
     * */
    private boolean isJoin(){
        List<UserBean> groupMemberList = DataUtils.getInstance().getJoinList();
        for (int i = 0; i < groupMemberList.size() ; i++) {
            if (SPUtils.getId() == groupMemberList.get(i).id)return true ;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
