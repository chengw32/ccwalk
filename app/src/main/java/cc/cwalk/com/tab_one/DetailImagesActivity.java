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
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.custom_view.AutoFlowLayout;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.ToastUtils;
import cc.cwalk.com.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Creat By_Chen
 * Time 2018/4/21 21:25
 * Des 图片类的详情
 */
public class DetailImagesActivity extends BaseListActivity {

    @Bind(R.id.et_evaluate)
    EditText mEtEvaluate;
    private AllDataBean mBean;
    UserBean userById;
    private TextView tv_num_zang;
    private TextView tv_zang;
    private AutoFlowLayout af_heads;

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

        mBean = (AllDataBean) getIntent().getSerializableExtra("bean");
        userById = DataUtils.getInstance().getUserById(mBean.userid);
        float screenWidth = MyApplication.getScreenWidth(this);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_detail_images, null);
        tv_num_zang = view.findViewById(R.id.tv_num_zang);
        af_heads = view.findViewById(R.id.af_heads);
        tv_zang = view.findViewById(R.id.tv_zang);
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
        GlideUtils.lodeHeadImage(userById.head, iv_head);
        //名字
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(userById.name);
        TextView tv_des = view.findViewById(R.id.tv_des);
        tv_des.setText(mBean.video.content);
        AutoFlowLayout af_heads = view.findViewById(R.id.af_images);
        List<String> images = mBean.video.images;
        for (int i = 0; i < images.size(); i++) {

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((int) (screenWidth / 3 - 20 * MyApplication.getScale()), (int) (100 * MyApplication.getScale()));
            ImageView imageView = new ImageView(xContext);
            imageView.setPadding(5, 5, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.mipmap.samp2);
            GlideUtils.lodeImage(images.get(i), imageView);
            af_heads.addView(imageView);

        }
        mRcView.addHeadView(view);
        initZang();
        List dataContent = mRcView.getDataContent();
        dataContent.addAll(mBean.evaluate);
        mRcView.complete();

    }

    int numZang;

    private void initZang() {

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

        numZang = mBean.zang.size();
        tv_num_zang.setText("共有 " + numZang + " 个赞");
        for (int i = 0; i < mBean.zang.size(); i++) {

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((int) (20 * MyApplication.getScale()), (int) (20 * MyApplication.getScale()));
            CircleImageView imageView = new CircleImageView(xContext);
            imageView.setPadding(5, 5, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.mipmap.default_head);
            UserBean userById = DataUtils.getInstance().getUserById(mBean.zang.get(i).id);
            GlideUtils.lodeHeadImage(userById.head, imageView);
            af_heads.addView(imageView);
        }
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
                return DetailImagesActivity.this;
            }
        };
    }

    @Override
    public void getData(int pageNo) {
        mRcView.complete();
    }

    @Override
    protected String setTitle() {
        return "详情";
    }

    public static void startActivity(Context context, AllDataBean bean) {
        Intent intent = new Intent(context, DetailImagesActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);
    }


    @OnClick(R.id.tv_evaluate)
    public void onViewClicked() {

        if (!SPUtils.isLoginWithToast())return;

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
        List<AllDataBean.EvaluateBean> evaluate = mBean.evaluate;

        AllDataBean.EvaluateBean evaluateBean = new AllDataBean.EvaluateBean();
        evaluateBean.userid = SPUtils.getId() ;
        evaluateBean.des = trim ;
        evaluateBean.time = time ;
        evaluate.add(0,evaluateBean );

        List dataContent = mRcView.getDataContent();
        dataContent.clear();
        dataContent.addAll( mBean.evaluate);
        mRcView.complete();

        Utils.hideSoft(mEtEvaluate);

    }
}
