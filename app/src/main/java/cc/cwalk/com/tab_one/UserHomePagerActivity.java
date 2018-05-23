package cc.cwalk.com.tab_one;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.StringCallback;

public class UserHomePagerActivity extends BaseListActivity {

    private View headView;

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<AllDataBean>() {

            @Override
            public Context getContext() {
                return UserHomePagerActivity.this;
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.user_home_page_item;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, AllDataBean item) {
                holder.getTextView(R.id.tv_des).setText(item.video.content);
                //设置图片
                switch (item.type) {
                    case 1:
                        holder.getImageView(R.id.iv_images).setVisibility(View.VISIBLE);
                        GlideUtils.lodeImage(item.video.videoImages, holder.getImageView(R.id.iv_images));
                        break;
                    case 2:
                        GlideUtils.lodeImage(item.video.images.get(0), holder.getImageView(R.id.iv_images));
                        holder.getImageView(R.id.iv_images).setVisibility(View.VISIBLE);

                        break;
                    case 3:
                        holder.getImageView(R.id.iv_images).setVisibility(View.GONE);
                        break;
                }
            }

        };
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        AllDataBean itembean = (AllDataBean) mRcView.getDataContent().get(pos-mRcView.getHeadViewCount());
        switch (itembean.type) {
            case 1:
                DetailActivity.startActivity(UserHomePagerActivity.this, itembean);
                break;
            case 2:
                DetailImagesActivity.startActivity(UserHomePagerActivity.this, itembean);
                break;
            case 3:
                DetailTextActivity.startActivity(UserHomePagerActivity.this,itembean);
                break;
        }
    }

    UserBean bean;

    @Override
    protected void initView() {
        super.initView();
        if (null != topbar)
            topbar.setVisibility(View.GONE);

        headView = LayoutInflater.from(UserHomePagerActivity.this)
                .inflate(R.layout.activity_user_home_pager_head_view, null);
        mRcView.addHeadView(headView);

        int id = getIntent().getIntExtra("bean", 0);
        List<AllDataBean> allList = DataUtils.getInstance().getAllList();
        List<AllDataBean> dataList = new ArrayList<>();
        for (int i = 0; i < allList.size(); i++) {
            if (allList.get(i).userid == id) dataList.add(allList.get(i));
        }
        mRcView.getDataContent().addAll(dataList);
        mRcView.complete();
        bean = DataUtils.getInstance().getUserById(id);

        setData();
    }

    public void setData() {
        GlideUtils.lodeImage(bean.head, (ImageView) headView.findViewById(R.id.head_image));
        //名字
        TextView tvName = headView.findViewById(R.id.tvName);
        tvName.setText(bean.name);
        //名字
        TextView tvAccount = headView.findViewById(R.id.tvAccount);
        tvAccount.setText(bean.name);
        //性别
        ImageView iv_sex = (ImageView) headView.findViewById(R.id.iv_sex);
        iv_sex.setImageResource(bean.sex == 1 ? R.mipmap.ic_gender_male : R.mipmap.ic_gender_female);

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

    public static void startActivity(Context context, int id) {

        Intent intent = new Intent(context, UserHomePagerActivity.class);
        intent.putExtra("bean", id);
        context.startActivity(intent);

    }


    @Override
    public void getData(int pageNo) {
        mRcView.complete();
    }

    @Override
    protected void initData() {
        getData(1);
    }
}
