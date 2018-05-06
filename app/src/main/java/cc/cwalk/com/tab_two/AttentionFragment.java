package cc.cwalk.com.tab_two;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.DetailActivity;
import cc.cwalk.com.tab_one.DetailImagesActivity;
import cc.cwalk.com.tab_one.UserHomePagerActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;

/**
 * Time 2018/4/11 14:12
 * Des  关注
 */
public class AttentionFragment extends BaseListFragment {


    @Override
    public void initView(View v) {
        super.initView(v);
        setBarGone();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_attention_headview, null);
        inflate.findViewById(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAttentionActivity.startActivity(getActivity());
            }
        });
        LinearLayout content = (LinearLayout) inflate.findViewById(R.id.content);
        for (int j = 0; j < 8; j++) {
            View item = LayoutInflater.from(getActivity()).inflate(R.layout.attention_head_item, null);
            GlideUtils.lodeImage(DataUtils.getInstance().getDataList().get(j).userBean.head, (ImageView) item.findViewById(R.id.iv_head));
            TextView tv_name = item.findViewById(R.id.tv_name);
            tv_name.setText(DataUtils.getInstance().getDataList().get(j).userBean.name);
            final int finalJ = j;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserHomePagerActivity.startActivity(getActivity(), DataUtils.getInstance().getDataList().get(finalJ));
                }
            });
            content.addView(item);
        }
        mRcView.addHeadView(inflate);
        getData(1);
    }


    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, final DataBean item) {
                holder.getView(R.id.iv_head).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserHomePagerActivity.startActivity(getActivity(), item);
                    }
                });
                View iv_isvideo = holder.getView(R.id.iv_isvideo);
                if (item.detailBeans.get(0).isVideo == 1)iv_isvideo.setVisibility(View.VISIBLE);
                else iv_isvideo.setVisibility(View.GONE);
                //设置头像
                GlideUtils.lodeImage(item.userBean.head,holder.getImageView(R.id.iv_head));
                //设置图片
                GlideUtils.lodeImage(item.detailBeans.get(0).videoBeans.get(0).videoImages,holder.getImageView(R.id.iv_images));
                //设置名字
                holder.getTextView(R.id.tv_name).setText(item.userBean.name);
                holder.getTextView(R.id.tv_time).setText(item.userBean.attentiontime);
                holder.getTextView(R.id.tv_des).setText(item.detailBeans.get(0).videoBeans.get(0).mtitle);
                holder.getTextView(R.id.tv_num_evaluate).setText(""+item.detailBeans.get(0).numEvaluate);
                holder.getTextView(R.id.tv_num_zang).setText(""+item.detailBeans.get(0).numZang);
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
//        DataBean itembean = (DataBean) mRcView.getDataContent().get(pos-1);
//        if (itembean.detailBeans.get(0).isVideo == 1)
//            DetailActivity.startActivity(xContext,itembean);
//        else
//        DetailImagesActivity.startActivity(getActivity(),itembean);

    }

    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();
        List<DataBean> dataList = DataUtils.getInstance().getDataList();
        dataContent.addAll(dataList);
        mRcView.complete();
    }


}
