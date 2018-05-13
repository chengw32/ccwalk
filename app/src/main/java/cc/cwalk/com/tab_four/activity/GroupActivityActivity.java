package cc.cwalk.com.tab_four.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;

public class GroupActivityActivity extends BaseListActivity {


    @Override
    protected void initData() {
getData(1);
    }

    @Override
    public void setRightText(String text) {
        super.setRightText("发布活动");
    }

    @Override
    public void onRightClick() {
PublishActivityActivity.startActivity(xContext);
    }

    @Override
    protected String setTitle() {
        return "活动";
    }

    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
        mRcView.addLineDivider();
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        mRcView.complete();
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, Object item) {

            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_group_activity;
            }

            @Override
            public Context getContext() {
                return xContext;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        };
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        ActivityDetailActivity.startActivity(xContext);
    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext,GroupActivityActivity.class));
    }
}
