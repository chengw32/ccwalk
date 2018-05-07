package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.UserHomePagerActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;

public class MyFansActivity extends BaseListActivity {


    @Override
    protected String setTitle() {
        return "我的粉丝";
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        UserHomePagerActivity.startActivity(xContext,DataUtils.getInstance().getDataList().get(0));
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, DataBean item) {
                holder.getView(R.id.tv_remove).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mRcView.getDataContent().remove(position);
                        mRcView.notifyDataSetChanged();
                    }
                });
                //设置头像
                GlideUtils.lodeImage(item.getHead(), holder.getImageView(R.id.iv_head));
                holder.getTextView(R.id.tv_name).setText(item.getName());
                holder.getTextView(R.id.tv_time).setText(item.getBefanstime()+" 成为你的粉丝");

            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_my_fans;
            }


            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
        List<DataBean> dataList = DataUtils.getInstance().getDataList();
        dataContent.addAll(dataList);
        mRcView.complete();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,MyFansActivity.class));
    }


    @Override
    protected void initData() {
        getData(1);
    }
}
