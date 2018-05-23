package cc.cwalk.com.tab_one;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.MyApplication;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.AttentionBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.custom_view.AutoFlowLayout;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.StringCallback;
import cc.cwalk.com.utils.ToastUtils;
import cc.cwalk.com.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;


public class DetailActivity extends BaseListActivity {

    NormalGSYVideoPlayer videoPlayer;
    @Bind(R.id.et_evaluate)
    EditText mEtEvaluate;
    private int numZang = 30;
    private TextView tv_attention;
    private AllDataBean bean;
    UserBean userById;
    private AutoFlowLayout af_heads;
    private TextView tv_num_zang;

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
        bean = (AllDataBean) getIntent().getSerializableExtra("bean");
        userById = DataUtils.getInstance().getUserById(bean.userid);
        videoPlayer = findViewById(R.id.video_view);
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView image = new ImageView(this);
        GlideUtils.lodeImage(bean.video.videoImages, image);
        videoPlayer.setThumbImageView(image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.setUp(bean.video.videoUrl, true, "");


        View view = LayoutInflater.from(this).inflate(R.layout.activity_detail_head, null);
        tv_num_zang = view.findViewById(R.id.tv_num_zang);
        final TextView tv_zang = view.findViewById(R.id.tv_zang);
        tv_zang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_zang.isSelected()) {
                    tv_zang.setSelected(false);
                    tv_zang.setText("点个赞");
                    ToastUtils.s("已赞");
                    numZang--;

                } else {
                    tv_zang.setText("取消赞");
                    tv_zang.setSelected(true);
                    ToastUtils.s("取消赞");
                    numZang++;

                }
                tv_num_zang.setText("共有 " + numZang + " 个赞");
            }
        });
        tv_attention = view.findViewById(R.id.tv_attention);
        List<AttentionBean> attentionList = DataUtils.getInstance().getAttentionList();
        for (int i = 0; i < attentionList.size(); i++) {
            AttentionBean attentionBean = attentionList.get(i);
            if (attentionBean.id == SPUtils.getId()) {
                //关注列表
                List<AttentionBean.AttentionlistBean> attentionlistBeanlist = attentionBean.attentionlist;
                for (int j = 0; j < attentionlistBeanlist.size(); j++) {

                    if (bean.userid == attentionlistBeanlist.get(j).id)tv_attention.setText("- 已关注");
                }
            }
        }
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

        GlideUtils.lodeImage(userById.head, iv_head);
        //名字
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(userById.name);
        TextView tv_des = view.findViewById(R.id.tv_des);
        tv_des.setText(bean.video.content);
        tv_num_zang.setText("共有 " + numZang + " 个赞");
        af_heads = view.findViewById(R.id.af_heads);
        mRcView.addHeadView(view);
        initZang();
        List dataContent = mRcView.getDataContent();
        dataContent.addAll(bean.evaluate);
        mRcView.complete();

    }


    private void initZang() {
        numZang = bean.zang.size();
        tv_num_zang.setText("共有 " + numZang + " 个赞");
        for (int i = 0; i < bean.zang.size(); i++) {

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((int) (20 * MyApplication.getScale()), (int) (20 * MyApplication.getScale()));
            CircleImageView imageView = new CircleImageView(xContext);
            imageView.setPadding(5, 5, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.mipmap.default_head);
            UserBean userById = DataUtils.getInstance().getUserById(bean.zang.get(i).id);
            GlideUtils.lodeImage(userById.head, imageView);
            af_heads.addView(imageView);
        }
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
        return new BaseRecyclerAdapter<AllDataBean.EvaluateBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, AllDataBean.EvaluateBean item) {
                holder.getTextView(R.id.tv_name).setText(DataUtils.getInstance().getUserById(item.userid).name);
                holder.getTextView(R.id.tv_time).setText(item.time);
                holder.getTextView(R.id.tv_evaluate).setText(item.des);

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
        mRcView.complete();
    }

    @Override
    protected String setTitle() {
        return null;
    }

    public static void startActivity(Context context, AllDataBean bean) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);
    }


    @OnClick(R.id.tv_evaluate)
    public void onViewClicked() {

        if (!SPUtils.isLoginWithToast()) return;

        String trim = mEtEvaluate.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtils.s("内容不能为空");
            return;
        }
        mEtEvaluate.setText("");
        ToastUtils.s("评论成功");

        List<AllDataBean.EvaluateBean> evaluate = bean.evaluate;

        AllDataBean.EvaluateBean evaluateBean = new AllDataBean.EvaluateBean();
        evaluateBean.userid = SPUtils.getId();
        evaluateBean.des = trim;
        evaluateBean.time = Utils.getTime();
        evaluate.add(0, evaluateBean);

        List dataContent = mRcView.getDataContent();
        dataContent.clear();
        dataContent.addAll(bean.evaluate);
        mRcView.complete();

        Utils.hideSoft(mEtEvaluate);

    }
}
