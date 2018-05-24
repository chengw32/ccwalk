package cc.cwalk.com.tab_one;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.LogUtils;
import cc.cwalk.com.utils.SPUtils;

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
        return new BaseRecyclerAdapter<AllDataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, final AllDataBean item) {
                holder.getView(R.id.rl_head).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserHomePagerActivity.startActivity(getActivity(), item.userid);
                    }
                });
                holder.getView(R.id.ll_detial).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DetailActivity.startActivity(getActivity(), item);
                    }
                });
                //设置头像
                UserBean userById = DataUtils.getInstance().getUserById(item.userid);
                GlideUtils.lodeImage(userById.head, holder.getImageView(R.id.iv_head));
                //设置名字
                holder.getTextView(R.id.tv_name).setText(userById.name);
                holder.getTextView(R.id.tv_des).setText(item.video.content);
                holder.getTextView(R.id.tv_num_evaluate).setText("" + item.evaluate.size());
                holder.getTextView(R.id.tv_num_zang).setText("" + item.zang.size());
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

    private void setThumbImageView(NormalGSYVideoPlayer videoPlayer, AllDataBean item) {
        //增加封面
        AllDataBean.VideoBean videoInfo = item.video;
        ImageView imageView = new ImageView(getActivity());
        GlideUtils.lodeImage(videoInfo.videoImages, imageView);
        videoPlayer.setThumbImageView(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.getBackButton().setVisibility(View.INVISIBLE);
        videoPlayer.setUp(videoInfo.videoUrl, true, "");
    }


    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.ACT_REFRESH.equals(event.getAction())) {
            getData(1);
        }
    }

    private int currentPosition;

    @Override
    public void onItemClick(View itemView, int pos) {
        currentPosition = pos - mRcView.getHeadViewCount();
        AllDataBean itembean = (AllDataBean) mRcView.getDataContent().get(currentPosition);
        switch (itembean.type) {
            case 1:
                DetailActivity.startActivity(getActivity(), itembean);
                break;
            case 2:
                DetailImagesActivity.startActivity(getActivity(), itembean);
                break;
            case 3:
                DetailTextActivity.startActivity(getActivity(), itembean);
                break;
        }
    }


    @Override
    public void getData(int pageNo) {
        mRcView.getDataContent().clear();
        DataUtils.getInstance().getNewestList(mRcView);
    }

}
