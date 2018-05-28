package cc.cwalk.com.credits;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.CreditsBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.webview.WebViewActivity;

public class CreditsActivity extends BaseListActivity {

    private TextView credits ;

    @Override
    protected void initData() {
        getData(1);
    }

    @Override
    protected void initView() {
        super.initView();
        View view = LayoutInflater.from(xContext).inflate(R.layout.activity_credits_head, null);
        TextView tv_time = view.findViewById(R.id.tv_time);
        credits = view.findViewById(R.id.tv_all_credits);
        SimpleDateFormat formatter = new SimpleDateFormat("更新时间 yyyy-MM-dd   HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tv_time.setText(str);
        mRcView.addHeadView(view);
    }

    @Override
    protected String setTitle() {
        return "我的积分";
    }

    @Override
    public void setRightText(String text) {
        super.setRightText("积分商城");
    }

    @Override
    public void onRightClick() {
        CreditsStoreActivity.startActivity(xContext);
//        WebViewActivity.startActivity(xContext,"积分商城","https://www.taobao.com/");
    }

    @Override
    public void getData(int pageNo) {

        mRcView.clearDataContent();
        List<CreditsBean> creditsList = DataUtils.getInstance().getCreditsList();
        for (int i = 0; i < creditsList.size(); i++) {
            CreditsBean creditsBean = creditsList.get(i);
            if (SPUtils.getId() == creditsBean.id)
                mRcView.getDataContent().addAll(creditsBean.creditslist);
        }
        credits.setText(""+mRcView.getDataContent().size());
        mRcView.complete();
    }

    String[] mark = {"签到", "发布视频", "评论帖子"};

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<CreditsBean.CreditslistBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, CreditsBean.CreditslistBean item) {
                holder.getTextView(R.id.tv_time).setText(item.time);
                holder.getTextView(R.id.tv_mark).setText(item.des);
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_credits;
            }

            @Override
            public Context getContext() {
                return xContext;
            }
        };
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CreditsActivity.class));
    }
}
