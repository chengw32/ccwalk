package cc.cwalk.com.tab_one;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;

public class UserHomePagerActivity extends BaseListActivity {

    private int mPostion ;

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
                holder.getTextView(R.id.tv_des).setText(DataUtils.getString(position));
                holder.getTextView(R.id.tv_num_evaluate).setText(""+DataUtils.getDetail(position).numEvaluate);
                holder.getTextView(R.id.tv_num_zang).setText(""+DataUtils.getDetail(position).numZang);
                //设置图片
                GlideUtils.lodeImage(DataUtils.getVideoInfo(position).videoImages,holder.getImageView(R.id.iv_images));
            }

        };
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        if (DataUtils.getDetail(pos).isVideo == 1)
        DetailActivity.startActivity(xContext,pos);
        else DetailImagesActivity.startActivity(xContext,pos);
    }

    @Override
    protected void initView() {
        super.initView();
        if (null != topbar)
        topbar.setVisibility(View.GONE);

        mPostion = getIntent().getIntExtra("position", 1);

        View headView = LayoutInflater.from(UserHomePagerActivity.this).inflate(R.layout.activity_user_home_pager_head_view, null);
        GlideUtils.lodeImage(DataUtils.getVideoInfo(mPostion).videoImages, (ImageView) headView.findViewById(R.id.head_image));
        //名字
        TextView tvName = headView.findViewById(R.id.tvName);
        tvName.setText(DataUtils.getUserInfo(mPostion).name);
        //名字
        TextView tvAccount = headView.findViewById(R.id.tvAccount);
        tvAccount.setText(DataUtils.getUserInfo(mPostion).adress);
        //性别
        ImageView iv_sex = (ImageView) headView.findViewById(R.id.iv_sex);
        iv_sex.setImageResource(DataUtils.getUserInfo(mPostion).sex == 1?R.mipmap.ic_gender_male:R.mipmap.ic_gender_female);

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

    public static void startActivity(Context context, int pos) {

        Intent intent = new Intent(context, UserHomePagerActivity.class);
        intent.putExtra("position",pos);
        context.startActivity(intent);

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
