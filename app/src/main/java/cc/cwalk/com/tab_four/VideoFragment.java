package cc.cwalk.com.tab_four;

import android.content.Context;
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
import cc.cwalk.com.tab_one.DetailActivity;
import cc.cwalk.com.tab_one.UserHomePagerActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.LogUtils;

/**
 * 热门视频
 */
public class VideoFragment extends BaseListFragment{


    @Override
    public void initView(View v) {
        super.initView(v);
        setBarGone();
        mRcView.addLineDivider();
        getData(1);

    }

    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, DataBean item) {
                GlideUtils.lodeImage(item.getVideos().get(0).getVideoImages(),holder.getImageView(R.id.iv_head));
                GlideUtils.lodeHeadImage(item.getHead(),holder.getImageView(R.id.iv_user_head));
                holder.getTextView(R.id.tv_title).setText(item.getVideos().get(0).getTitle());
                holder.getTextView(R.id.tv_name).setText(item.getName());
                holder.getTextView(R.id.tv_time).setText(item.getAttentiontime());
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_video;
            }


            @Override
            public Context getContext() {
                return getActivity();
            }

        };
    }


    @Override
    public void onItemClick(View itemView, int pos) {
        DetailActivity.startActivity(getActivity(), (DataBean) mRcView.getDataContent().get(pos));
    }

    @Override
    public void getData(int pageNo) {
       DataUtils.getInstance().getDataList(mRcView);
    }

}
