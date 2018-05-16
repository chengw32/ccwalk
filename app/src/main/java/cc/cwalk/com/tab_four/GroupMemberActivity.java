package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.ToastUtils;

public class GroupMemberActivity extends BaseListActivity {

    @Override
    protected void initView() {
        super.initView();
        mRcView.addLineDivider();
    }

    @Override
    protected void initData() {
        getData(1);
    }

    @Override
    protected String setTitle() {
        return "成员管理";
    }

    @Override
    public void getData(int pageNo) {
        DataUtils.getInstance().getGroupList(mRcView);
    }

    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.REMOVE_MEMBER.equals(event.getAction())){
            GroupInfoBean bean = (GroupInfoBean) event.getData();
            mRcView.getDataContent().remove(bean);
            mRcView.notifyDataSetChanged();
        }
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<GroupInfoBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, GroupInfoBean item) {


                TextView textView = holder.getTextView(R.id.tv_type);
                if (position< 10){
                    if (item.getCreater() == 1) {
                        textView.setVisibility(View.VISIBLE);
                        textView.setText("会长、副会长");
                    } else if (item.getManager() == 1) {
                        if (position > 0) {
                            GroupInfoBean groupInfoBean = (GroupInfoBean) mRcView.getDataContent().get(position - 1);
                            int manager = groupInfoBean.getManager();
                            int manager1 = item.getManager();
                            if (manager == manager1){
                                textView.setVisibility(View.GONE);
                            }else textView.setVisibility(View.VISIBLE);
                        }

                        textView.setText("管理员");

                    }else {
                        GroupInfoBean groupInfoBean = (GroupInfoBean) mRcView.getDataContent().get(position - 1);
                        int manager = groupInfoBean.getManager();
                        int manager1 = item.getManager();
                        if (manager == manager1){
                            textView.setVisibility(View.GONE);
                        }else textView.setVisibility(View.VISIBLE);
                        textView.setText("成员");
                    }
                }else {
                    textView.setVisibility(View.GONE);
                }


                holder.getTextView(R.id.tv_name).setText(item.getNiname());
                holder.getTextView(R.id.tv_des).setText("入团时间： " + item.getJointime());
                GlideUtils.lodeHeadImage(item.getHead(), holder.getImageView(R.id.iv_head));
                final TextView tv_zang = holder.getTextView(R.id.tv_allow);
//                tv_zang.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mRcView.getDataContent().remove(position);
//                        ToastUtils.s("移除成功");
//                        mRcView.notifyDataSetChanged();
//                    }
//                });

            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_group_member;
            }

            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, GroupMemberActivity.class));
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        MemberDetailActivity.startActivity(xContext, (GroupInfoBean) mRcView.getDataContent().get(pos));
    }
}
