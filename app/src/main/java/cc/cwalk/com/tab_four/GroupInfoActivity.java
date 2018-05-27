package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.utils.DataUtils;

public class GroupInfoActivity extends BaseActivity {


    @Bind(R.id.tv_members)
    TextView mTvMembers;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_group_info;
    }

    @Override
    protected void initView() {

        mTvMembers.setText(""+DataUtils.getInstance().getGroupMemberList().size());
    }

    @Override
    protected String setTitle() {
        return "更多资料";
    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext, GroupInfoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
