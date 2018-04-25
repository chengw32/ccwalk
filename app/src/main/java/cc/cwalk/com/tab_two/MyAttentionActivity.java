package cc.cwalk.com.tab_two;

import android.content.Context;
import android.content.Intent;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;

public class MyAttentionActivity extends BaseListActivity {


    @Override
    protected String setTitle() {
        return "我的关注";
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, Object item) {
                //设置头像
                GlideUtils.lodeImage(DataUtils.getVideoInfo(position).videoImages, holder.getImageView(R.id.iv_head));
                holder.getTextView(R.id.tv_name).setText(DataUtils.getUserInfo(position).name);
                holder.getTextView(R.id.tv_time).setText("关注于 "+DataUtils.getDetail(position).time);
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
    public void getData(int pageNo) {
        List dataList = mRcView.getDataContent();
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
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
