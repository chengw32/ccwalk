package cc.cwalk.com.tab_one;

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
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.ToastUtils;
import cc.cwalk.com.utils.Utils;

/**
 * Creat By_Chen
 * Time 2018/4/21 21:25
 * Des 图片类的详情
 */
public class DetailImagesActivity extends BaseListActivity {

    @Bind(R.id.et_evaluate)
    EditText mEtEvaluate;
    private DataBean mBean;

    @Override
    protected void initData() {
        getData(1);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.image_detail;
    }

    @Override
    protected void initView() {
        super.initView();

        mBean = (DataBean) getIntent().getSerializableExtra("bean");
        float screenWidth = MyApplication.getScreenWidth(this);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_detail_images, null);
       final TextView tv_attention = view.findViewById(R.id.tv_attention);
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
        GlideUtils.lodeHeadImage(mBean.getHead(), iv_head);
        //名字
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(mBean.getName());
        TextView tv_des = view.findViewById(R.id.tv_des);
        tv_des.setText(mBean.getVideos().get(0).getTitle());
        AutoFlowLayout af_heads = view.findViewById(R.id.af_images);
        for (int i = 0; i < 7; i++) {

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((int) (screenWidth / 3 - 20 * MyApplication.getScale()), (int) (100 * MyApplication.getScale()));
            ImageView imageView = new ImageView(xContext);
            imageView.setPadding(5, 5, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.mipmap.samp2);
            GlideUtils.lodeImage(mBean.getVideos().get(i).getVideoImages(), imageView);
            af_heads.addView(imageView);

        }
        mRcView.addHeadView(view);
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
                return DetailImagesActivity.this;
            }
        };
    }

    @Override
    public void getData(int pageNo) {
        DataUtils.getInstance().getDataList(mRcView);
    }

    @Override
    protected String setTitle() {
        return "详情";
    }

    public static void startActivity(Context context, DataBean bean) {
        Intent intent = new Intent(context, DetailImagesActivity.class);
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
