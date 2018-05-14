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
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;

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
        DataUtils.getInstance().getGroupList(mRcView);
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<GroupInfoBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, GroupInfoBean item) {

                GlideUtils.lodeHeadImage(item.getHead(),holder.getImageView(R.id.iv_head));
                holder.getTextView(R.id.tv_title).setText(item.getTitle());
                holder.getTextView(R.id.tv_number).setText("已包名人数："+DataUtils.getInstance().getRandow(11));
                holder.getTextView(R.id.tv_time).setText(item.getJointime());
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
        ActivityDetailActivity.startActivity(xContext);
    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext,GroupActivityActivity.class));
    }
}
