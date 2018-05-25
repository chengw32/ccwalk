package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.DialogUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.ToastUtils;

public class JoinGroupActivity extends BaseListActivity {


    @Override
    protected void initData() {
        getData(1);
    }

    @Override
    protected void initView() {
        super.initView();
        mRcView.addLineDivider();
    }

    @Override
    public void getData(int pageNo) {

        mRcView.clearDataContent();
        List<UserBean> userList = DataUtils.getInstance().getJoinList();
        mRcView.getDataContent().addAll(userList);
        mRcView.complete();
    }


    @Override
    protected String setTitle() {
        return "申请列表";
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<UserBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, final UserBean item) {
                    holder.getTextView(R.id.tv_name).setText(item.name);
                    holder.getTextView(R.id.tv_des).setText(item.persondes);

                GlideUtils.lodeImage(item.head,holder.getImageView(R.id.iv_head));
                final TextView tv_zang = holder.getTextView(R.id.tv_allow);
                tv_zang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!tv_zang.isSelected()) {

                            DialogUtils.showTopicDialogsCustom("确定同意加入？", xContext, new DialogUtils.DialogClickBack() {
                                @Override
                                public void define() {
                                    tv_zang.setSelected(true);
                                    tv_zang.setText("通过申请");
                                    List<UserBean> groupMemberList = DataUtils.getInstance().getGroupMemberList();
                                    groupMemberList.add(item);
                                    SPUtils.setGroupmemberglist(GsonUtil.toJosn(groupMemberList));
                                    mRcView.getDataContent().remove(item);
                                    SPUtils.setJoinlist(GsonUtil.toJosn(mRcView.getDataContent()));
                                    ToastUtils.s("已通过申请");
                                    EventUtil.sendEvent(EventUtil.ACT_REFRESH_group_member,null);
                                }

                                @Override
                                public void cancel() {

                                }
                            });


                        }
                    }
                });
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_join_group;
            }

            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, JoinGroupActivity.class));
    }
}
