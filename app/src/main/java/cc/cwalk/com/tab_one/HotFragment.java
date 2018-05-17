package cc.cwalk.com.tab_one;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.VideoBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.LogUtils;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.StringCallback;

/**
 * 热门视频
 */
public class HotFragment extends BaseListFragment{


    @Override
    public void initView(View v) {
        super.initView(v);
        setBarGone();
        mRcView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getData(1);
    }

    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, final DataBean item) {
                holder.getView(R.id.iv_head).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserHomePagerActivity.startActivity(getActivity(), item);
                    }
                });
                holder.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserHomePagerActivity.startActivity(getActivity(), item);
                    }
                });
                NormalGSYVideoPlayer view = (NormalGSYVideoPlayer) holder.getView(R.id.video_view);

                setThumbImageView(view, item);
                holder.getView(R.id.tv_detial).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DetailActivity.startActivity(getActivity(),item);
                    }
                });

                //设置头像
                GlideUtils.lodeHeadImage(item.getHead(),holder.getImageView(R.id.iv_head));
                //设置名字
                holder.getTextView(R.id.tv_name).setText(item.getName());
                holder.getTextView(R.id.tv_play_num).setText("播放次数  "+item.getVideos().get(0).getNumPaly());

            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_hot;
            }


            @Override
            public Context getContext() {
                return getActivity();
            }

        };
    }

    private void setThumbImageView(NormalGSYVideoPlayer videoPlayer,  DataBean item) {
        //增加封面
        DataBean.VideosBean videoInfo = item.getVideos().get(0);
        ImageView imageView = new ImageView(getActivity());
        GlideUtils.lodeImage(videoInfo.getVideoImages(), imageView);
        videoPlayer.setThumbImageView(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.getBackButton().setVisibility(View.INVISIBLE);
        videoPlayer.setUp(videoInfo.getVideoUrl(), true, "");
    }


    @Override
    public void onItemClick(View itemView, int pos) {
//        DetailActivity.startActivity(getActivity());
    }

    @Override
    public void getData(int pageNo) {
        DataUtils.getInstance().getJsonFromService(new StringCallback() {
            @Override
            public void success(String result) {
                List<DataBean> data = GsonUtil.getData(result);

                List dataContent = mRcView.getDataContent();
                dataContent.addAll(data);
                mRcView.complete();
            }
        });
    }

}
