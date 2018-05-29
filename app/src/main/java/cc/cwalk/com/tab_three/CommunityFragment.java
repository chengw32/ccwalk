package cc.cwalk.com.tab_three;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.dialog.PickerDialog;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.DetailActivity;
import cc.cwalk.com.tab_one.DetailImagesActivity;
import cc.cwalk.com.tab_one.DetailTextActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.SPUtils;

/**
 * Created by Chen on 2018/4/18.
 * 社区交流
 */

public class CommunityFragment extends BaseListFragment {
    @Bind(R.id.tv_post_num)
    TextView mTvPostNum;

    @Override
    public void initView(View v) {
        super.initView(v);
        mRcView.addLineDivider();
        setBarGone();
        getData(1);
    }


    @Override
    protected int setContentLayout() {
        return R.layout.community_head;
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        AllDataBean itembean = (AllDataBean) mRcView.getDataContent().get(pos - mRcView.getHeadViewCount());
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
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<AllDataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, AllDataBean item) {
                View iv_isvideo = holder.getView(R.id.iv_isvideo);
//                if (item.getVideos().get(0).getIsVideo() == 1)iv_isvideo.setVisibility(View.VISIBLE);
//                else iv_isvideo.setVisibility(View.GONE);
                //设置头像
                UserBean userById = DataUtils.getInstance().getUserById(item.userid);
                GlideUtils.lodeImage(userById.head, holder.getImageView(R.id.iv_head));
                //设置图片
//                GlideUtils.lodeImage(item.getVideos().get(0).getVideoImages(),holder.getImageView(R.id.iv_images));
                //设置名字
                String numE;
                int numEvaluate = item.evaluate != null ? item.evaluate.size() : 0;
                if (numEvaluate > 99) numE = "99+";
                else numE = String.valueOf(numEvaluate);
                holder.getTextView(R.id.tv_num_evaluate).setText("评论 (" + numE + ")");
                holder.getTextView(R.id.tv_time).setText(item.video.time);
//                holder.getTextView(R.id.tv_num_zang).setText("赞 ("+item.getVideos().get(0).getNumZang()+")");
                holder.getTextView(R.id.tv_name).setText(userById.name);
                holder.getTextView(R.id.tv_des).setText(item.video.content);
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
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.ACT_REFRESH.equals(event.getAction())) {
            getData(1);
        }
    }

    @Override
    public void getData(int pageNo) {
        mRcView.getDataContent().clear();
        mRcView.getDataContent().addAll(DataUtils.getInstance().getCommunityList());
        mRcView.complete();
        mTvPostNum.setText("访问 209  话题 "+mRcView.getDataContent().size());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tv_publish)
    public void onViewClicked() {
        if (!SPUtils.isLoginWithToast())return;
        PickerDialog pickerDialog = new PickerDialog(getActivity(), new PickerDialog.PickCallBack() {
            @Override
            public void video() {

                PictureSelector.create(getActivity())
                        .openGallery(PictureMimeType.ofVideo())
                        .maxSelectNum(1)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }

            @Override
            public void photo() {
                PictureSelector.create(getActivity())
                        .openGallery(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);

            }

            @Override
            public void text() {
                PublishActivity.startActivity(xContext, null,true);
            }
        });
        pickerDialog.show();
    }
}
