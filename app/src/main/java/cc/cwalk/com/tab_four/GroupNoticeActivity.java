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
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.EventUtil;
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
        super.setRightText(SPUtils.getCreat()==1?"发布公告":"");
    }

    @Override
    public void onRightClick() {
        if (SPUtils.getCreat() != 1)return;
       PublishNoticeActivity.startActivity(xContext);
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, DataBean item) {
                holder.getTextView(R.id.tv_name).setText("发布人： "+item.getName());
                holder.getTextView(R.id.tv_content).setText(item.getStr().get(0).getDes());
                holder.getTextView(R.id.tv_time).setText(item.getStr().get(0).getTime());
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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String time = formatter.format(curDate);

            DataBean bean = new DataBean();


            List<DataBean.StrBean> strBeans = new ArrayList<>();
            //创建评论的bean
            DataBean.StrBean strBean = new DataBean.StrBean();
            strBean.setDes(str);
            strBean.setTime(time);
            strBeans.add(strBean);

            bean.setStr(strBeans);

            bean.setName(SPUtils.getUserName());

            List<DataBean> dataContent = mRcView.getDataContent();

            dataContent.add(0,bean);

            mRcView.complete();



        }
    }

    @Override
    public void getData(int pageNo) {
        DataUtils.getInstance().getDataList(mRcView);
    }

    @Override
    protected void initData() {
        getData(1);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,GroupNoticeActivity.class));
    }
}
