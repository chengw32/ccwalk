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
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.StringCallback;

public class UserHomePagerActivity extends BaseListActivity {

    private  View headView ;

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean.VideosBean>() {

            @Override
            public Context getContext() {
                return UserHomePagerActivity.this;
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.user_home_page_item;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, DataBean.VideosBean item) {
                holder.getTextView(R.id.tv_des).setText(item.getTitle());
                //设置图片
                GlideUtils.lodeImage(item.getVideoImages(), holder.getImageView(R.id.iv_images));
            }

        };
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        if (bean.getVideos().get(0).getIsVideo() == 1)
        DetailActivity.startActivity(xContext,bean);
        else DetailImagesActivity.startActivity(xContext,bean);
    }


    private DataBean bean;

    @Override
    protected void initView() {
        super.initView();
        if (null != topbar)
            topbar.setVisibility(View.GONE);

        headView = LayoutInflater.from(UserHomePagerActivity.this)
                .inflate(R.layout.activity_user_home_pager_head_view, null);
        mRcView.addHeadView(headView);

    }

    public void setData(){
        GlideUtils.lodeHeadImage(bean.getHead(), (ImageView) headView.findViewById(R.id.head_image));
        //名字
        TextView tvName = headView.findViewById(R.id.tvName);
        tvName.setText(bean.getName());
        //名字
        TextView tvAccount = headView.findViewById(R.id.tvAccount);
        tvAccount.setText(bean.getName());
        //性别
        ImageView iv_sex = (ImageView) headView.findViewById(R.id.iv_sex);
        iv_sex.setImageResource(bean.getSex() == 1 ? R.mipmap.ic_gender_male : R.mipmap.ic_gender_female);

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

    public static void startActivity(Context context, DataBean bean) {

        Intent intent = new Intent(context, UserHomePagerActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);

    }


    @Override
    public void getData(int pageNo) {
        bean = (DataBean) getIntent().getSerializableExtra("bean");
        if (null == bean) {

            DataUtils.getInstance().getJsonFromService(new StringCallback() {
                @Override
                public void success(String result) {
                    //Gson解析数据
                    List<DataBean> data = GsonUtil.getData(result);
                    bean = data.get(0);
                    List<DataBean.VideosBean> detail = data.get(0).getVideos();
                    List dataContent = mRcView.getDataContent();
                    dataContent.addAll(detail);
                    mRcView.complete();
                    setData();
                }
            });
        } else {

            List dataContent = mRcView.getDataContent();
            List<DataBean.VideosBean> detail = bean.getVideos();
            dataContent.addAll(detail);
            mRcView.complete();
                    setData();
        }
    }

    @Override
    protected void initData() {
        getData(1);
    }
}
