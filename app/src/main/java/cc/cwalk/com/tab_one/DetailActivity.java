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
import cc.cwalk.com.beans.DataBean;
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
    private DataBean bean;
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
        bean = (DataBean) getIntent().getSerializableExtra("bean");
        videoPlayer = findViewById(R.id.video_view);
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView image = new ImageView(this);
        GlideUtils.lodeImage(bean.getVideos().get(0).getVideoImages(), image);
        videoPlayer.setThumbImageView(image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.setUp(DataUtils.baseUrl + bean.getVideos().get(0).getVideoUrl(), true, "");


        View view = LayoutInflater.from(this).inflate(R.layout.activity_detail_head, null);
        tv_num_zang = view.findViewById(R.id.tv_num_zang);
        final TextView tv_zang = view.findViewById(R.id.tv_zang);
        tv_zang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_zang.isSelected()) {
                    tv_zang.setSelected(false);
                    tv_zang.setText("取消赞");
                    ToastUtils.s("取消赞");
                    numZang--;

                } else {
                    tv_zang.setText("点个赞");
                    tv_zang.setSelected(true);
                    ToastUtils.s("已赞");
                    numZang++;

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
        GlideUtils.lodeHeadImage(bean.getHead(), iv_head);
        //名字
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(bean.getName());
        TextView tv_des = view.findViewById(R.id.tv_des);
        tv_des.setText(bean.getVideos().get(0).getTitle());
        tv_num_zang.setText("共有 " + numZang + " 个赞");
        af_heads = view.findViewById(R.id.af_heads);
        mRcView.addHeadView(view);
        getZangUser();
    }

    private void getZangUser() {
        DataUtils.getInstance().getJsonFromService(new StringCallback() {
            @Override
            public void success(String result) {
                //Gson解析数据
                List<DataBean> data = GsonUtil.getData(result);
                initHead(data);
            }
        });
    }

    private void initHead(final List<DataBean> data) {
        numZang = data.size();
        tv_num_zang.setText("共有 " + numZang + " 个赞");
        for (int i = 0; i < data.size(); i++) {

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((int) (20 * MyApplication.getScale()), (int) (20 * MyApplication.getScale()));
            CircleImageView imageView = new CircleImageView(xContext);
            imageView.setPadding(5, 5, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.mipmap.default_head);
            GlideUtils.lodeHeadImage(data.get(i).getHead(), imageView);
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
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, DataBean item) {
                holder.getTextView(R.id.tv_name).setText(item.getName());
                holder.getTextView(R.id.tv_time).setText(item.getStr().get(0).getTime());
                holder.getTextView(R.id.tv_evaluate).setText(item.getStr().get(0).getDes());

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
        DataUtils.getInstance().getDataList(mRcView);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_evaluate)
    public void onViewClicked() {
        String trim = mEtEvaluate.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtils.s("内容不能为空");
            return;
        }
        mEtEvaluate.setText("");
        ToastUtils.s("评论成功");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);

        DataBean bean = new DataBean();


        List<DataBean.StrBean> strBeans = new ArrayList<>();
        //创建评论的bean
        DataBean.StrBean strBean = new DataBean.StrBean();
        strBean.setDes(trim);
        strBean.setTime(time);
        strBeans.add(strBean);

        bean.setStr(strBeans);

        bean.setName(SPUtils.getUserName());

        List<DataBean> dataContent = mRcView.getDataContent();

        dataContent.add(0,bean);

        mRcView.complete();

        Utils.hideSoft(mEtEvaluate);

    }
}
