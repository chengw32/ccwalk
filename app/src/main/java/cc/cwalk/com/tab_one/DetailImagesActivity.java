package cc.cwalk.com.tab_one;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.util.List;

import cc.cwalk.com.MyApplication;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.custom_view.AutoFlowLayout;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Creat By_Chen
 * Time 2018/4/21 21:25
 * Des 图片类的详情
 * */
public class DetailImagesActivity extends BaseListActivity {


    @Override
    protected void initData() {
        getData(1);
    }


    @Override
    protected void initView() {
        super.initView();

        float screenWidth = MyApplication.getScreenWidth(this);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_detail_images, null);
        AutoFlowLayout af_heads = view.findViewById(R.id.af_images);
        for (int i = 0; i < 7; i++) {

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((int) (screenWidth/3 -20*MyApplication.getScale() ), (int) (100 * MyApplication.getScale()));
            ImageView imageView = new ImageView(xContext);
            imageView.setPadding(5, 5, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.mipmap.samp2);
            af_heads.addView(imageView);

        }
        mRcView.addHeadView(view);
    }


    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, Object item) {

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
        dataContent.add("");
        dataContent.add("");
        dataContent.add("");
        dataContent.add("");
        dataContent.add("");
        dataContent.add("");
        dataContent.add("");
        mRcView.complete();
    }

    @Override
    protected String setTitle() {
        return "详情";
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, DetailImagesActivity.class));
    }
}
