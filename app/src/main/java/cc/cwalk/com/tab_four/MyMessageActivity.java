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
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.DetailActivity;
import cc.cwalk.com.tab_one.DetailImagesActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;

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
        List<DataBean> dataList = DataUtils.getInstance().getDataList();
        dataContent.addAll(dataList);
        mRcView.complete();
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, DataBean item) {
                //设置头像
                GlideUtils.lodeImage(item.getHead(),holder.getImageView(R.id.iv_head));
                holder.getTextView(R.id.tv_title).setText(item.getName() +"  评论了你的作品  " +item.getDetail().get(0).getVideos().get(0).getTitle());
                holder.getTextView(R.id.tv_time).setText(item.getAttentiontime());
                holder.getTextView(R.id.tv_des).setText(DataUtils.getInstance().getStringText());
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_my_message;
            }

            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext,MyMessageActivity.class));
    }

    @Override
    public void onItemClick(View itemView, int pos) {
//        DataBean itembean = (DataBean) mRcView.getDataContent().get(pos);
//        if (itembean.detailBeans.get(0).isVideo == 1)
//            DetailActivity.startActivity(xContext,itembean);
//        else
//            DetailImagesActivity.startActivity(MyMessageActivity.this,itembean);
    }
}
