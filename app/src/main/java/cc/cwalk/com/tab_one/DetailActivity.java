package cc.cwalk.com.tab_one;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.io.Serializable;
import java.util.List;

import cc.cwalk.com.MyApplication;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.DetailBean;
import cc.cwalk.com.custom_view.AutoFlowLayout;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.LogUtils;
import cc.cwalk.com.utils.ToastUtils;
import de.hdodenhof.circleimageview.CircleImageView;


public class DetailActivity extends BaseListActivity {

    NormalGSYVideoPlayer videoPlayer;
    private int numZang = 30;
    private TextView tv_attention;
    private DataBean bean;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initData() {
        getData(1);
    }

    @Override
    protected void initView() {
        super.initView();
        bean = (DataBean) getIntent().getSerializableExtra("bean");
        videoPlayer = findViewById(R.id.video_view);
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView image = new ImageView(this);
        GlideUtils.lodeImage(bean.detailBeans.get(0).videoBeans.get(0).videoImages, image);
        videoPlayer.setThumbImageView(image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LogUtils.e("播放video url " + bean.detailBeans.get(0).videoBeans.get(0).videoUrl);
        videoPlayer.setUp(bean.detailBeans.get(0).videoBeans.get(0).videoUrl, true, "");


        View view = LayoutInflater.from(this).inflate(R.layout.activity_detail_head, null);
        final TextView tv_num_zang = view.findViewById(R.id.tv_num_zang);
        final TextView tv_zang = view.findViewById(R.id.tv_zang);
        tv_zang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_zang.isSelected()) {
                    tv_zang.setSelected(false);
                    tv_zang.setText("取消赞");
                    ToastUtils.s("取消赞");
                    numZang-- ;

                } else {
                    tv_zang.setText("点个赞");
                    tv_zang.setSelected(true);
                    ToastUtils.s("已赞");
                    numZang++ ;

                }
                tv_num_zang.setText("共有 " + numZang + " 个赞");
            }
        });
        tv_attention = view.findViewById(R.id.tv_attention);
        tv_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("+ 关注".equals(tv_attention.getText().toString())) {

                    ToastUtils.s("已关注");
                    tv_attention.setText("- 已关注");
                } else {
                    ToastUtils.s("取消关注");
                    tv_attention.setText("+ 关注");

                }
            }
        });
        //头像
        ImageView iv_head = view.findViewById(R.id.iv_head);
        GlideUtils.lodeImage(bean.userBean.head, iv_head);
        //名字
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(bean.userBean.name);
        TextView tv_des = view.findViewById(R.id.tv_des);
        tv_des.setText(bean.detailBeans.get(0).videoBeans.get(0).mtitle);
        tv_num_zang.setText("共有 " + numZang + " 个赞");
        AutoFlowLayout af_heads = view.findViewById(R.id.af_heads);
        for (int i = 0; i < numZang; i++) {

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((int) (20 * MyApplication.getScale()), (int) (20 * MyApplication.getScale()));
            CircleImageView imageView = new CircleImageView(xContext);
            imageView.setPadding(5, 5, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.mipmap.default_head);
            GlideUtils.lodeImage(DataUtils.getInstance().getSingleUserData().head, imageView);
            af_heads.addView(imageView);

        }
        mRcView.addHeadView(view);
//        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, DataBean item) {
                holder.getTextView(R.id.tv_name).setText(item.userBean.name);
                holder.getTextView(R.id.tv_time).setText(item.userBean.befanstime);
                holder.getTextView(R.id.tv_evaluate).setText(DataUtils.getInstance().getStringText());

            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_detail_item;
            }


            @Override
            public Context getContext() {
                return DetailActivity.this;
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

    @Override
    protected String setTitle() {
        return null;
    }

    public static void startActivity(Context context, DataBean bean) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);
    }
}
