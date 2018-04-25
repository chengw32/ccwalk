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
import cc.cwalk.com.utils.DataUtils;
import de.hdodenhof.circleimageview.CircleImageView;


public class DetailActivity extends BaseListActivity {

    NormalGSYVideoPlayer videoPlayer;

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
        videoPlayer = findViewById(R.id.video_view);
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //增加封面
//        ImageView imageView = new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//        videoPlayer.setThumbImageView(imageView);
        videoPlayer.setUp(DataUtils.getVideoInfo(getIntent().getIntExtra("position",1)).videoUrl, true, "");


        View view = LayoutInflater.from(this).inflate(R.layout.activity_detail_head, null);
        AutoFlowLayout af_heads = view.findViewById(R.id.af_heads);
        for (int i = 0; i < 50; i++) {

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((int) (20 * MyApplication.getScale()), (int) (20 * MyApplication.getScale()));
            CircleImageView imageView = new CircleImageView(xContext);
            imageView.setPadding(5, 5, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.mipmap.default_head);
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
                return DetailActivity.this;
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
        return null;
    }

    public static void startActivity(Context context,int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }
}
