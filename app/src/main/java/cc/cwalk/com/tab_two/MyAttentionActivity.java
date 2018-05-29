package cc.cwalk.com.tab_two;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.AttentionBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.DialogUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.LogUtils;
import cc.cwalk.com.utils.SPUtils;

public class MyAttentionActivity extends BaseListActivity {


    @Override
    protected String setTitle() {
        return "我的关注";
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<AttentionBean.AttentionlistBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, AttentionBean.AttentionlistBean item) {
                //设置头像
                UserBean userById = DataUtils.getInstance().getUserById(item.id);
                GlideUtils.lodeImage(userById.head, holder.getImageView(R.id.iv_head));
                holder.getTextView(R.id.tv_name).setText(userById.name);
                holder.getTextView(R.id.tv_time).setText("关注于 "+item.befanstime);
                holder.getView(R.id.tv_remove).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveFans(position);
                    }
                });
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.myattention_item_layout;
            }


            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    private void saveFans(final int position) {

        DialogUtils.showTopicDialogsCustom("确定取消？", xContext, new DialogUtils.DialogClickBack() {
            @Override
            public void define() {
                mRcView.getDataContent().remove(position);
                for (int i = 0; i < data.size(); i++) {
                    if (SPUtils.getId() == data.get(i).id) {
                        data.get(i).attentionlist = mRcView.getDataContent();
                        SPUtils.setAttentionList(GsonUtil.toJosn(data));
                        EventUtil.sendEvent(EventUtil.REMOVE_ATTENTION,null);
                    }
                }
                mRcView.complete();
            }

            @Override
            public void cancel() {

            }
        });


    }

    @Override
    public void onItemClick(View itemView, int pos) {
        //TODO  
//        DataBean dataContent = (DataBean) mRcView.getDataContent().get(pos);
//        UserHomePagerActivity.startActivity(xContext,dataContent);
    }

    @Override
    public void getData(int pageNo) {
        mRcView.complete();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyAttentionActivity.class));
    }

    List<AttentionBean> data;

    @Override
    protected void initData() {

        mRcView.clearDataContent();
       data = DataUtils.getInstance().getAttentionList();
        for (int i = 0; i < data.size(); i++) {
            if (SPUtils.getId() ==data.get(i).id) {
                List<AttentionBean.AttentionlistBean>  attentionlist = data.get(i).attentionlist;
                LogUtils.e(GsonUtil.toJosn(attentionlist));
                mRcView.getDataContent().addAll(attentionlist);
                mRcView.complete();
                return;
            }
        }
    }
}
