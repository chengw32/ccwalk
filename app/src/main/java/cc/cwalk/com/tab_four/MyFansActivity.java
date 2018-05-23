package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.AttentionBean;
import cc.cwalk.com.beans.FansBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.DialogUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.SPUtils;

public class MyFansActivity extends BaseListActivity {


    @Override
    protected String setTitle() {
        return "我的粉丝";
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        //TODO
//        List<DataBean> data = mRcView.getDataContent();
//        UserHomePagerActivity.startActivity(xContext,data.get(pos));
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<FansBean.FanslistBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, FansBean.FanslistBean item) {
                holder.getView(R.id.tv_remove).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveFans(position);

                    }
                });
                //设置头像
                UserBean userById = DataUtils.getInstance().getUserById(item.id);
                GlideUtils.lodeImage(userById.head, holder.getImageView(R.id.iv_head));
                holder.getTextView(R.id.tv_name).setText(userById.name);
                holder.getTextView(R.id.tv_time).setText(item.befanstime + " 成为你的粉丝");

            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_my_fans;
            }


            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    private void moveFans(final int position) {

        DialogUtils.showTopicDialogsCustom("确定移除？", xContext, new DialogUtils.DialogClickBack() {
            @Override
            public void define() {
                FansBean.FanslistBean o = (FansBean.FanslistBean) mRcView.getDataContent().get(position);
                //从粉丝列表移除

                //保存数据
                saveFans(position);

                mRcView.notifyDataSetChanged();
            }

            @Override
            public void cancel() {

            }
        });

    }

    private void saveFans(int position) {
        mRcView.getDataContent().remove(position);
        for (int i = 0; i < fansList.size(); i++) {
            if (SPUtils.getId() == fansList.get(i).id) {
                fansList.get(i).fanslist = mRcView.getDataContent();
                SPUtils.setFans(GsonUtil.toJosn(fansList));
            }
        }
    }

    @Override
    public void getData(int pageNo) {
        mRcView.complete();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyFansActivity.class));
    }

    List<FansBean> fansList;

    @Override
    protected void initData() {
        mRcView.clearDataContent();
        fansList = DataUtils.getInstance().getFansList();
        for (int i = 0; i < fansList.size(); i++) {
            if (SPUtils.getId() == fansList.get(i).id) {
                mRcView.getDataContent().addAll(fansList.get(i).fanslist);
                mRcView.complete();
                return;
            }
        }
    }
}
