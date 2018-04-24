package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.DetailImagesActivity;

public class MyMessageActivity extends BaseListActivity {

    @Override
    protected void initData() {
        getData(1);
    }

    @Override
    protected String setTitle() {
        return "我的消息";
    }

    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
        dataContent.add(1);
        dataContent.add(1);
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
                return R.layout.activity_my_message;
            }

            @Override
            public Context getContext() {
                return xContext;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }
        };
    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext,MyMessageActivity.class));
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        DetailImagesActivity.startActivity(xContext);
    }
}
