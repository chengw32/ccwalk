package cc.cwalk.com.tab_two;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.AttentionBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.DetailActivity;
import cc.cwalk.com.tab_one.DetailImagesActivity;
import cc.cwalk.com.tab_one.DetailTextActivity;
import cc.cwalk.com.tab_one.UserHomePagerActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.StringCallback;

/**
 * Time 2018/4/11 14:12
 * Des  关注
 */
public class AttentionFragment extends BaseListFragment {

    private View inflate;

    @Override
    public void initView(View v) {
        super.initView(v);
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_attention_headview, null);
        mRcView.addHeadView(inflate);
        setBarGone();

        getData(1);
    }

    List<AttentionBean.AttentionlistBean> attentionList;

    private void initAttentionList() {

        if (null != attentionList)attentionList.clear();//切换账号的时候有缓存要清除掉

        List<AttentionBean> data = DataUtils.getInstance().getAttentionList();
        for (int i = 0; i < data.size(); i++) {
            if (SPUtils.getId() ==data.get(i).id) {
                attentionList = data.get(i).attentionlist;
            }
        }

        if(null == attentionList)return;//匹配过后没有关注的人

        inflate.findViewById(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAttentionActivity.startActivity(getActivity());
            }
        });
        LinearLayout content = (LinearLayout) inflate.findViewById(R.id.content);
        content.removeAllViews();
        for (int j = 0; j < (attentionList.size() > 8 ? 8 : attentionList.size()); j++) {
            View item = LayoutInflater.from(getActivity()).inflate(R.layout.attention_head_item, null);
            UserBean userById = DataUtils.getInstance().getUserById(attentionList.get(j).id);
            GlideUtils.lodeImage(userById.head, (ImageView) item.findViewById(R.id.iv_head));
            TextView tv_name = item.findViewById(R.id.tv_name);
            tv_name.setText(userById.name);
            final int finalJ = j;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
//                    UserHomePagerActivity.startActivity(getActivity(), data.get(finalJ));
                }
            });
            content.addView(item);
        }

    }

    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.REMOVE_ATTENTION.equals(event.getAction()) || EventUtil.ACT_REFRESH.equals(event.getAction())) {
            getData(1);
        }
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<AllDataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, final AllDataBean item) {
                holder.getView(R.id.iv_head).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO  跳转
//                        UserHomePagerActivity.startActivity(getActivity(), item);

                    }
                });
                View view = holder.getView(R.id.rl_image_video);
                View iv_isvideo = holder.getView(R.id.iv_isvideo);
                if (item.type == 3) {
                    //纯文本
                    view.setVisibility(View.GONE);
                } else {
                    //设置图片
                    view.setVisibility(View.VISIBLE);
                    if (item.type == 1) {
                        //视频
                        GlideUtils.lodeImage(item.video.videoImages, holder.getImageView(R.id.iv_images));
                        iv_isvideo.setVisibility(View.VISIBLE);
                    } else if (item.type == 2) {
                        //图片
                        GlideUtils.lodeImage(item.video.images.get(0), holder.getImageView(R.id.iv_images));
                        iv_isvideo.setVisibility(View.GONE);
                    }

                }


                //设置头像
                UserBean userById = DataUtils.getInstance().getUserById(item.userid);
                GlideUtils.lodeImage(userById.head, holder.getImageView(R.id.iv_head));

                //设置名字
                holder.getTextView(R.id.tv_name).setText(userById.name);
                holder.getTextView(R.id.tv_time).setText(item.video.time);
                holder.getTextView(R.id.tv_des).setText(item.video.content);
                holder.getTextView(R.id.tv_num_evaluate).setText("" + item.evaluate.size());
                holder.getTextView(R.id.tv_num_zang).setText("" + item.zang.size());
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.attention_item;
            }


            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    @Override
    protected String setTitle() {
        return "";
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
    public void getData(int pageNo) {
        if (!SPUtils.isLogin()) {
            mRcView.complete();
            return;
        }
        initAttentionList();
        mRcView.clearDataContent();
        List<AllDataBean> allList = DataUtils.getInstance().getAllList();
        List dataList = new ArrayList();
        if (null != attentionList)
        for (int i = 0; i < allList.size(); i++) {

            int userid = allList.get(i).userid;
            for (int j = 0; j < attentionList.size(); j++) {
                if (userid == attentionList.get(j).id) dataList.add(allList.get(i));
            }
        }
        mRcView.getDataContent().addAll(dataList);
        mRcView.complete();
    }




}
