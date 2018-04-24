package cc.cwalk.com.tab_one;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.LogUtils;
import cc.cwalk.com.webview.WebViewActivity;

/**q
 * 教程
 */
public class TeachingFragment extends BaseListFragment {



@Override
    public void initView(View v) {
    super.initView(v);
    setBarGone();
    mRcView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    getData(1);
}


    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<String>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, String item) {
                holder.getTextView(R.id.tv_name).setText(item);
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_teaching;
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
//        http://v.youku.com/v_show/id_XMjI1OTg4NDky.html?spm=a2h0k.8191407.0.0&from=s1.8-1-1.2

        WebViewActivity.startActivity(xContext, (String) mRcView.getDataContent().get(pos));
    }

    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
        dataContent.add("曳舞教学");
        dataContent.add("AUS风格");
        dataContent.add("MAS风格");
        dataContent.add("休闲视频");
        dataContent.add("国外Cwalk");
        dataContent.add("教学Cwalk");
        dataContent.add("Elector教学");
        dataContent.add("jumpStyle教学");
        dataContent.add("Elector手舞");
        dataContent.add("Freestyle教学");
        dataContent.add("Freestep教学");
        dataContent.add("jumpStyle教学");
        dataContent.add("韩国曳舞教学");
        dataContent.add("巴西曳舞教学");
        mRcView.complete();
    }


}
