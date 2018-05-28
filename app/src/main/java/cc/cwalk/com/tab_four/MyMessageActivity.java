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
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.DetailActivity;
import cc.cwalk.com.tab_one.DetailImagesActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.SPUtils;

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

        mRcView.clearDataContent();
        List<AllDataBean> allList = DataUtils.getInstance().getAllList();
        List dataContent = mRcView.getDataContent();
        for (int i = 0; i < allList.size(); i++) {
            AllDataBean allDataBean = allList.get(i);
            if (allDataBean.userid == SPUtils.getId()) {
                List<AllDataBean.EvaluateBean> evaluate = allDataBean.evaluate;
                for (int j = 0; j < evaluate.size(); j++) {
                    if (evaluate.get(j).userid != SPUtils.getId())
                        dataContent.add(evaluate.get(j));
                }
            }
        }
        mRcView.complete();

    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<AllDataBean.EvaluateBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, AllDataBean.EvaluateBean item) {
                //设置头像
                UserBean userById = DataUtils.getInstance().getUserById(item.userid);
                GlideUtils.lodeImage(userById.head, holder.getImageView(R.id.iv_head));
                holder.getTextView(R.id.tv_title).setText(userById.name + "  评论了你  ");
//                holder.getTextView(R.id.tv_video_name).setText( item.getVideos().get(0).getTitle());
                holder.getTextView(R.id.tv_time).setText(item.time);
                holder.getTextView(R.id.tv_des).setText(item.des);
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
        xContext.startActivity(new Intent(xContext, MyMessageActivity.class));
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        //TODO
//        DataBean itembean = (DataBean) mRcView.getDataContent().get(pos);
//        if (itembean.getVideos().get(0).getIsVideo() == 1)
//            DetailActivity.startActivity(xContext,itembean);
//        else
//            DetailImagesActivity.startActivity(MyMessageActivity.this,itembean);
    }
}
