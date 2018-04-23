package cc.cwalk.com.tab_one;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.OnRefreshLoadMoreListener;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.recycles.RefreshLoadMoreRecyclerView;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.LogUtils;

/**
 * 教程
 */
public class TeachingFragment extends Fragment implements BaseRecyclerAdapter.OnItemClickListener,OnRefreshLoadMoreListener {

    protected RefreshLoadMoreRecyclerView mRcView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, null);
        initView(view);
        return view;
    }


    public void initView(View v) {
        mRcView =  v.findViewById(R.id.rc_list);
        mRcView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        if (null != mRcView)
            mRcView.setAdapter(getAdapter());
        mRcView.setOnRefreshLoadMoreListener(this);
        mRcView.setItemClick(this);
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
                return R.layout.item_hot;
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

    @Override
    public void onLoadMore(int pageNo) {

    }

}
