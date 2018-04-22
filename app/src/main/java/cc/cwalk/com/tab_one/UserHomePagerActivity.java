package cc.cwalk.com.tab_one;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;

public class UserHomePagerActivity extends BaseListActivity {


    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter() {

            @Override
            public Context getContext() {
                return UserHomePagerActivity.this;
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return  R.layout.user_home_page_item;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, Object item) {

            }

        };
    }


    @Override
    protected void initView() {
        super.initView();
        if (null != topbar)
        topbar.setVisibility(View.GONE);
        View headView = LayoutInflater.from(UserHomePagerActivity.this).inflate(R.layout.activity_user_home_pager_head_view, null);
        mRcView.addHeadView(headView);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected String setTitle() {
        return "主页";
    }

    public static void startActivity(Context context) {

        context.startActivity(new Intent(context,UserHomePagerActivity.class));

    }


    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        mRcView.complete();
    }

    @Override
    protected void initData() {
getData(1);
    }
}