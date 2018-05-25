package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.NoticeBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.ToastUtils;
import cc.cwalk.com.utils.Utils;

public class GroupNoticeActivity extends BaseListActivity {


    @Override
    protected String setTitle() {
        return "公告";
    }

    @Override
    public void setRightText(String text) {
        super.setRightText(DataUtils.getInstance().getCreater()==1?"发布公告":"");
    }

    @Override
    public void onRightClick() {
        if (DataUtils.getInstance().getCreater() != 1)return;
       PublishNoticeActivity.startActivity(xContext);
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<NoticeBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, NoticeBean item) {
                UserBean userById = DataUtils.getInstance().getUserById(item.userid);
                holder.getTextView(R.id.tv_name).setText("发布人： "+userById.name);
                holder.getTextView(R.id.tv_content).setText(item.content);
                holder.getTextView(R.id.tv_time).setText(item.time);
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_group_notice;
            }

            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.NOTICE_PUBLISH.equals(event.getAction())){

            String str = (String) event.getData();
            NoticeBean bean = new NoticeBean();
            bean.content = str ;
            bean.time = Utils.getTime();
            bean.userid = SPUtils.getId();


            List<NoticeBean> dataContent = mRcView.getDataContent();

            dataContent.add(0,bean);

            SPUtils.setNoticeList(GsonUtil.toJosn(dataContent));
            mRcView.complete();



        }
    }

    @Override
    public void getData(int pageNo) {
        mRcView.clearDataContent();
        mRcView.getDataContent().addAll( DataUtils.getInstance().getNoticeList());
        mRcView.complete();
    }

    @Override
    protected void initData() {
        getData(1);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,GroupNoticeActivity.class));
    }
}
