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
import cc.cwalk.com.beans.ActivityBean;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.SPUtils;

public class GroupActivityActivity extends BaseListActivity {


    @Override
    protected void initData() {
getData(1);
    }

    @Override
    public void setRightText(String text) {

        super.setRightText(DataUtils.getInstance().getCreater() == 1 ?"发布活动":"");
    }

    @Override
    public void onRightClick() {

        if (DataUtils.getInstance().getCreater() != 1)return;
        PublishActivityActivity.startActivity(xContext);
    }

    @Override
    protected String setTitle() {
        return "活动";
    }

    @Override
    public void getData(int pageNo) {
        mRcView.clearDataContent();
        List activitysList = DataUtils.getInstance().getActivitysList();
        mRcView.getDataContent().addAll(activitysList);
        mRcView.complete();
    }

    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.ACT_REFRESH_group_activity.equals(event.getAction()))
            getData(1);
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<ActivityBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, ActivityBean item) {

                GlideUtils.lodeImage(item.banner,holder.getImageView(R.id.iv_head));
                holder.getTextView(R.id.tv_title).setText(item.title);
                holder.getTextView(R.id.tv_number).setText("已报名人数："+item.member.size());
                holder.getTextView(R.id.tv_time).setText(item.publishtime);
                if (position<2)
                holder.getTextView(R.id.tv_is_runing).setText("进行中");
                else
                holder.getTextView(R.id.tv_is_runing).setText("已结束");

            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_group_activity;
            }

            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        ActivityDetailActivity.startActivity(xContext,(ActivityBean)mRcView.getDataContent().get(pos));
    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext,GroupActivityActivity.class));
    }
}
