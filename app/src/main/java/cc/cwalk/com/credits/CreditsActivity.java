package cc.cwalk.com.credits;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
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
        TextView tv_time = view.findViewById(R.id.tv_time);
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("更新时间 yyyy-MM-dd   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        String   str   =   formatter.format(curDate);
        tv_time.setText(str);
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
        CreditsStoreActivity.startActivity(xContext);
//        WebViewActivity.startActivity(xContext,"积分商城","https://www.taobao.com/");
    }

    @Override
    public void getData(int pageNo) {
        DataUtils.getInstance().getDataList(mRcView);
    }

    String [] mark = {"签到","发布视频","评论帖子"};

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, DataBean item) {
                holder.getTextView(R.id.tv_time).setText(item.getAttentiontime());
                holder.getTextView(R.id.tv_mark).setText(mark[position%mark.length]);
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
