package cc.cwalk.com.tab_one;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.util.List;

import cc.cwalk.com.MyApplication;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.custom_view.AutoFlowLayout;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Creat By_Chen
 * Time 2018/4/21 21:25
 * Des 图片类的详情
 * */
public class DetailImagesActivity extends BaseListActivity {

    private DataBean mBean;

    @Override
    protected void initData() {
        getData(1);
    }


    @Override
    protected void initView() {
        super.initView();

        mBean = (DataBean) getIntent().getSerializableExtra("bean");
        float screenWidth = MyApplication.getScreenWidth(this);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_detail_images, null);
        //头像
        ImageView iv_head = view.findViewById(R.id.iv_head);
        GlideUtils.lodeImage(mBean.userBean.head, iv_head);
        //名字
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(mBean.userBean.name);
        TextView tv_des = view.findViewById(R.id.tv_des);
        tv_des.setText(mBean.detailBeans.get(0).videoBeans.get(0).mtitle);
        AutoFlowLayout af_heads = view.findViewById(R.id.af_images);
        for (int i = 0; i < 7; i++) {

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((int) (screenWidth/3 -20*MyApplication.getScale() ), (int) (100 * MyApplication.getScale()));
            ImageView imageView = new ImageView(xContext);
            imageView.setPadding(5, 5, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.mipmap.samp2);
            GlideUtils.lodeImage(mBean.detailBeans.get(0).videoBeans.get(i).videoImages, imageView);
            af_heads.addView(imageView);

        }
        mRcView.addHeadView(view);
    }


    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, DataBean item) {
                holder.getTextView(R.id.tv_name).setText(item.userBean.name);
                holder.getTextView(R.id.tv_time).setText(item.userBean.befanstime);
                holder.getTextView(R.id.tv_evaluate).setText(DataUtils.getStringText());
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_detail_item;
            }


            @Override
            public Context getContext() {
                return DetailImagesActivity.this;
            }
        };
    }

    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
        List<DataBean> dataList = DataUtils.getDataList();
        dataContent.addAll(dataList);
        mRcView.complete();
    }

    @Override
    protected String setTitle() {
        return "详情";
    }

    public static void startActivity(Context context, DataBean bean) {
        Intent intent = new Intent(context, DetailImagesActivity.class);
        intent.putExtra("bean",bean);
        context.startActivity(intent);
    }
}
