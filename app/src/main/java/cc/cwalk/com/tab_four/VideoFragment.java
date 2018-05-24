package cc.cwalk.com.tab_four;

import android.content.Context;
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
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;

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
        return new BaseRecyclerAdapter<AllDataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, AllDataBean item) {
                GlideUtils.lodeImage(item.video.videoImages,holder.getImageView(R.id.iv_head));
                UserBean userById = DataUtils.getInstance().getUserById(item.userid);
                GlideUtils.lodeImage(userById.head,holder.getImageView(R.id.iv_user_head));
                holder.getTextView(R.id.tv_title).setText(item.video.content);
                holder.getTextView(R.id.tv_name).setText(userById.name);
                holder.getTextView(R.id.tv_time).setText(item.video.time);
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
        //TODO  "跳转"
//        DetailActivity.startActivity(getActivity(), (DataBean) mRcView.getDataContent().get(pos));
    }

    @Override
    public void getData(int pageNo) {
        mRcView.getDataContent().clear();
        DataUtils.getInstance().getNewestList(mRcView);
    }

}
