package cc.cwalk.com.tab_one;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;

public class UserHomePagerActivity extends BaseListActivity {


    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter() {

            @Override
            public Context getContext() {
                return UserHomePagerActivity.this;
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return  R.layout.user_home_page_item;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, Object item) {
                holder.getTextView(R.id.tv_name).setText(DataUtils.getUserInfo(position+10).name);
                holder.getTextView(R.id.tv_time).setText(DataUtils.getDetail(position).time);
                holder.getTextView(R.id.tv_des).setText(DataUtils.getString(position+17));
                holder.getTextView(R.id.tv_num_evaluate).setText(""+DataUtils.getDetail(position+7).numEvaluate);
                holder.getTextView(R.id.tv_num_zang).setText(""+DataUtils.getDetail(position+7).numZang);
                //设置图片
                GlideUtils.lodeImage(DataUtils.getVideoInfo(position+5).videoImages,holder.getImageView(R.id.iv_images));
            }

        };
    }


    @Override
    protected void initView() {
        super.initView();
        if (null != topbar)
        topbar.setVisibility(View.GONE);
        View headView = LayoutInflater.from(UserHomePagerActivity.this).inflate(R.layout.activity_user_home_pager_head_view, null);
        mRcView.addHeadView(headView);
        headView.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected String setTitle() {
        return "主页";
    }

    public static void startActivity(Context context) {

        context.startActivity(new Intent(context,UserHomePagerActivity.class));

    }


    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        mRcView.complete();
    }

    @Override
    protected void initData() {
getData(1);
    }
}
