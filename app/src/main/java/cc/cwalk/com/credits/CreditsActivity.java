package cc.cwalk.com.credits;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.exoplayer2.text.webvtt.WebvttCssStyle;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.webview.WebViewActivity;

public class CreditsActivity extends BaseListActivity {


    @Override
    protected void initData() {
        getData(1);
    }

    @Override
    protected void initView() {
        super.initView();
        View view = LayoutInflater.from(xContext).inflate(R.layout.activity_credits_head, null);
        mRcView.addHeadView(view);
    }

    @Override
    protected String setTitle() {
        return "我的积分";
    }

    @Override
    public void setRightText(String text) {
        super.setRightText("积分商城");
    }

    @Override
    public void onRightClick() {
        WebViewActivity.startActivity(xContext,"积分商城");
    }

    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
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
                return R.layout.activity_credits;
            }

            @Override
            public Context getContext() {
                return xContext;
            }
        };
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CreditsActivity.class));
    }
}
