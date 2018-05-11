package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.ToastUtils;

public class GroupMemberActivity extends BaseListActivity {


    @Override
    protected void initData() {
        getData(1);
    }

    @Override
    protected String setTitle() {
        return "成员管理";
    }

    @Override
    public void getData(int pageNo) {
        DataUtils.getInstance().getDataList(mRcView);
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, DataBean item) {

                holder.getTextView(R.id.tv_name).setText(item.getName());
                holder.getTextView(R.id.tv_des).setText("加入时间： "+item.getAttentiontime());
                GlideUtils.lodeHeadImage(item.getHead(),holder.getImageView(R.id.iv_head));
                final TextView tv_zang = holder.getTextView(R.id.tv_allow);
                tv_zang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       mRcView.getDataContent().remove(position);
                       ToastUtils.s("移除成功");
                       mRcView.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_group_member;
            }

            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,GroupMemberActivity.class));
    }
}