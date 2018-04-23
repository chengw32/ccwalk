package cc.cwalk.com.tab_one;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.io.File;
import java.util.List;

import cc.cwalk.com.MyApplication;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.LogUtils;

/**
 * 热门视频
 */
public class NewestFragment extends BaseListFragment{


    @Override
    public void initView(View v) {
        super.initView(v);
        setBarGone();
        getData(1);
    }

    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, Object item) {
                holder.getView(R.id.rl_head).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserHomePagerActivity.startActivity(getActivity());
                    }
                });
                holder.getView(R.id.ll_detial).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DetailActivity.startActivity(getActivity());
                    }
                });
                NormalGSYVideoPlayer view = (NormalGSYVideoPlayer) holder.getView(R.id.video_view);

                setThumbImageView(view, position);
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_newest;
            }


            @Override
            public Context getContext() {
                return getActivity();
            }

        };
    }

    private void setThumbImageView(NormalGSYVideoPlayer videoPlayer, int position) {
        //增加封面
        String video_url = "http://chengw32.com:8080/sample.mp4";
        String image_url = "http://chengw32.com:8080/sample.png";
        switch (position % 3) {

            case 0:
                video_url = "http://chengw32.com:8080/sample.mp4";
                image_url = "http://chengw32.com:8080/sample.png";
                break;
            case 1:
                video_url = "http://chengw32.com:8080/sample1.mp4";
                image_url = "http://chengw32.com:8080/sample1.png";
                break;
            case 2:
                video_url = "http://chengw32.com:8080/sample2.flv";
                image_url = "http://chengw32.com:8080/sample2.png";
                break;
        }
        ImageView imageView = new ImageView(getActivity());
        GlideUtils.lodeImage(image_url, imageView);
        videoPlayer.setThumbImageView(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.getBackButton().setVisibility(View.INVISIBLE);
        videoPlayer.setUp(video_url, true, "");
        videoPlayer.setUp(video_url,true,new File(MyApplication.cachePath,"1.mp4"),"");
    }


    @Override
    public void onItemClick(View itemView, int pos) {
//        DetailActivity.startActivity(getActivity());
    }

    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        LogUtils.e("getData");
        mRcView.complete();
    }

}
