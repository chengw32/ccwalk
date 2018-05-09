package cc.cwalk.com.tab_one;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.StringCallback;

/**
 * 热门视频
 */
public class NewestFragment extends BaseListFragment {


    @Override
    public void initView(View v) {
        super.initView(v);
        setBarGone();
        getData(1);

    }

    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, final DataBean item) {
                holder.getView(R.id.rl_head).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserHomePagerActivity.startActivity(getActivity(), item);
                    }
                });
                holder.getView(R.id.ll_detial).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DetailActivity.startActivity(getActivity(), item);
                    }
                });
                //设置头像
                GlideUtils.lodeHeadImage(item.getHead(), holder.getImageView(R.id.iv_head));
                //设置名字
                holder.getTextView(R.id.tv_name).setText(item.getName());
                holder.getTextView(R.id.tv_des).setText(item.getVideos().get(0).getTitle());
                holder.getTextView(R.id.tv_num_evaluate).setText("" + item.getVideos().get(0).getNumEvaluate());
                holder.getTextView(R.id.tv_num_zang).setText("" + item.getVideos().get(0).getNumZang());
                NormalGSYVideoPlayer view = (NormalGSYVideoPlayer) holder.getView(R.id.video_view);

                setThumbImageView(view, item);
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

    private void setThumbImageView(NormalGSYVideoPlayer videoPlayer, DataBean item) {
        //增加封面
        DataBean.VideosBean videoInfo = item.getVideos().get(0);
        ImageView imageView = new ImageView(getActivity());
        GlideUtils.lodeImage(videoInfo.getVideoImages(), imageView);
        videoPlayer.setThumbImageView(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.getBackButton().setVisibility(View.INVISIBLE);
        videoPlayer.setUp(DataUtils.baseUrl+videoInfo.getVideoUrl(), true, "");
    }


    @Override
    public void onItemClick(View itemView, int pos) {
        DetailActivity.startActivity(getActivity(), (DataBean) mRcView.getDataContent().get(pos));
    }

    Dialog mDialog ;

    @Override
    public void getData(int pageNo) {
                DataUtils.getInstance().getDataList(mRcView);
    }

}
