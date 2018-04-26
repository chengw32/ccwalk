package cc.cwalk.com.tab_one;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.VideoBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.LogUtils;
import cc.cwalk.com.utils.DataUtils;

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
            public void bindData(RecyclerViewHolder holder, final int position, Object item) {
                holder.getView(R.id.rl_head).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserHomePagerActivity.startActivity(getActivity(), position);
                    }
                });
                holder.getView(R.id.ll_detial).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DetailActivity.startActivity(getActivity(),position);
                    }
                });
                //设置头像
                GlideUtils.lodeImage(DataUtils.getVideoInfo(position+7).videoImages,holder.getImageView(R.id.iv_head));
                //设置名字
                holder.getTextView(R.id.tv_name).setText(DataUtils.getUserInfo(position).name);
                holder.getTextView(R.id.tv_des).setText(DataUtils.getString(position));
                holder.getTextView(R.id.tv_num_evaluate).setText(""+DataUtils.getDetail(position).numEvaluate);
                holder.getTextView(R.id.tv_num_zang).setText(""+DataUtils.getDetail(position).numZang);
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
        VideoBean videoInfo = DataUtils.getVideoInfo(position);
        ImageView imageView = new ImageView(getActivity());
        GlideUtils.lodeImage(videoInfo.videoImages, imageView);
        videoPlayer.setThumbImageView(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.getBackButton().setVisibility(View.INVISIBLE);
        LogUtils.e(videoInfo.videoUrl);
        videoPlayer.setUp(videoInfo.videoUrl, true, "");
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
