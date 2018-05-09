package cc.cwalk.com.tab_three;

import android.content.Context;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.util.List;

import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.dialog.PickerDialog;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.DetailActivity;
import cc.cwalk.com.tab_one.DetailImagesActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.StringCallback;

/**
 * Created by Chen on 2018/4/18.
 * 社区交流
 */

public class CommunityFragment extends BaseListFragment {
    @Override
    public void initView(View v) {
        super.initView(v);
        setBarGone();
        getData(1);
    }


    @Override
    protected int setContentLayout() {
        return R.layout.community_head;
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        DataBean itembean = (DataBean) mRcView.getDataContent().get(pos);
        if (itembean.getVideos().get(0).getIsVideo() == 1)
            DetailActivity.startActivity(xContext,itembean);
        else
            DetailImagesActivity.startActivity(getActivity(),itembean);
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, DataBean item) {
                View iv_isvideo = holder.getView(R.id.iv_isvideo);
                if (item.getVideos().get(0).getIsVideo() == 1)iv_isvideo.setVisibility(View.VISIBLE);
                else iv_isvideo.setVisibility(View.GONE);
                //设置头像
                GlideUtils.lodeHeadImage(item.getHead(),holder.getImageView(R.id.iv_head));
                //设置图片
                GlideUtils.lodeImage(item.getVideos().get(0).getVideoImages(),holder.getImageView(R.id.iv_images));
                //设置名字
                holder.getTextView(R.id.tv_num_evaluate).setText("评论 ("+ item.getVideos().get(0).getNumEvaluate()+")");
                holder.getTextView(R.id.tv_time).setText(item.getVideos().get(0).getTime());
                holder.getTextView(R.id.tv_num_zang).setText("赞 ("+item.getVideos().get(0).getNumZang()+")");
                holder.getTextView(R.id.tv_name).setText(item.getName());
                holder.getTextView(R.id.tv_des).setText(item.getVideos().get(0).getTitle());
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.community_item_layout;
            }


            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }


    @Override
    public void getData(int pageNo) {
      DataUtils.getInstance().getDataList(mRcView);
    }

    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.IMAGE_VIDEO.equals(event.getAction())){
            PublishActivity.startActivity(xContext,event.getData());
        }
    }

    @OnClick(R.id.tv_publish)
    public void onViewClicked() {
        PickerDialog pickerDialog = new PickerDialog(getActivity(), new PickerDialog.PickCallBack() {
            @Override
            public void camera() {

                PictureSelector.create(getActivity())
                        .openCamera(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }

            @Override
            public void photo() {

                PictureSelector.create(getActivity())
                        .openGallery(PictureMimeType.ofAll())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
        pickerDialog.show();
    }
}
