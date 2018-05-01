package cc.cwalk.com.tab_two;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.DetailBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.UserHomePagerActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;

public class MyAttentionActivity extends BaseListActivity {


    @Override
    protected String setTitle() {
        return "我的关注";
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, DataBean item) {
                //设置头像
                GlideUtils.lodeImage(item.userBean.head, holder.getImageView(R.id.iv_head));
                holder.getTextView(R.id.tv_name).setText(item.userBean.name);
                holder.getTextView(R.id.tv_time).setText("关注于 "+item.userBean.attentiontime);
                holder.getView(R.id.tv_remove).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mRcView.getDataContent().remove(position);
                        mRcView.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.myattention_item_layout;
            }


            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        DataBean dataContent = (DataBean) mRcView.getDataContent().get(pos);
        UserHomePagerActivity.startActivity(xContext,dataContent);
    }

    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
        List<DataBean> dataList = DataUtils.getDataList();
        dataContent.addAll(dataList);
        mRcView.complete();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyAttentionActivity.class));
    }

    @Override
    protected void initData() {
        getData(1);
    }
}
