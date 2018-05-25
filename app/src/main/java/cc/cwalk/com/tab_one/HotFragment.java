package cc.cwalk.com.tab_one;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.LogUtils;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.StringCallback;

/**
 * 热门视频
 */
public class HotFragment extends BaseListFragment {


    @Override
    public void initView(View v) {
        super.initView(v);
        setBarGone();
        mRcView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getData(1);
    }

    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<AllDataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, final AllDataBean item) {
                holder.getView(R.id.iv_head).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //TODO
                        UserHomePagerActivity.startActivity(getActivity(), item.userid);
                    }
                });
                holder.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        UserHomePagerActivity.startActivity(getActivity(), item.userid);
                    }
                });
                NormalGSYVideoPlayer view = (NormalGSYVideoPlayer) holder.getView(R.id.video_view);

                setThumbImageView(view, item);
                holder.getView(R.id.tv_detial).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO  跳转
                        DetailActivity.startActivityHot(getActivity(), item);
                    }
                });

                //设置头像
                //设置头像
                UserBean userById = DataUtils.getInstance().getUserById(item.userid);
                GlideUtils.lodeImage(userById.head, holder.getImageView(R.id.iv_head));
                //设置名字
                holder.getTextView(R.id.tv_name).setText(userById.name);
                holder.getTextView(R.id.tv_play_num).setText("播放次数  " + item.video.numPaly);

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

    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.ACT_REFRESH_hot.equals(event.getAction())) {

            AllDataBean bean = (AllDataBean) event.getData();
            List<AllDataBean> dataContent = mRcView.getDataContent();
            for (int i = 0; i < dataContent.size(); i++) {
                AllDataBean allDataBean1 = dataContent.get(i);
                if (allDataBean1.id == bean.id) {
                    dataContent.remove(allDataBean1);
                    dataContent.add(i, bean);
                }
            }
            SPUtils.setHotList(GsonUtil.toJosn(dataContent));
            getData(1);
        }
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
    public void onItemClick(View itemView, int pos) {
        DetailActivity.startActivity(getActivity(), (AllDataBean) mRcView.getDataContent().get(pos));
    }

    @Override
    public void getData(int pageNo) {
        mRcView.clearDataContent();
        mRcView.getDataContent().addAll(DataUtils.getInstance().getHotList());
        mRcView.complete();

    }

}
