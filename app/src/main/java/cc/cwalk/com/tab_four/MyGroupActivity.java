package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;

public class MyGroupActivity extends BaseActivity {


    @Override
    protected int setContentLayout() {
        return R.layout.activity_my_group;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected String setTitle() {
        return "我的社团";
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyGroupActivity.class));
    }


    @OnClick({R.id.ll_all_post,R.id.ll_inof_more,R.id.ll_members, R.id.ll_join, R.id.ll_notice})
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
                GroupMoreInfoActivity.startActivity(xContext);
                break;
            case R.id.ll_all_post:
                GroupMemberPostActivity.startActivity(xContext);
                break;
        }
    }
}
