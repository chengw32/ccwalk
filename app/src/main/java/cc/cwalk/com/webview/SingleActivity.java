package cc.cwalk.com.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.utils.GlideUtils;

public class SingleActivity extends BaseActivity {


    @Bind(R.id.video_view)
    NormalGSYVideoPlayer videoView;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_single;
    }

    @Override
    protected String setTitle() {
        return "教程";
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放所有
        videoView.setVideoAllCallBack(null);
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.onVideoResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.onVideoPause();
    }

    @Override
    protected void initView() {
//增加封面
        videoView.getBackButton().setVisibility(View.INVISIBLE);
        videoView.setUp(getIntent().getStringExtra(URL), true, "");
    }

    @Override
    protected void initData() {

    }

    private final static String URL = "url";

    public static void startActivity(Context xContext, String url) {
        Intent intent = new Intent(xContext, SingleActivity.class);
        intent.putExtra(URL, url);
        xContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
