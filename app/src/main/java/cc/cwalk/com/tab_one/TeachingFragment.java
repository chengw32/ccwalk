package cc.cwalk.com.tab_one;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.TeachingBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.LogUtils;
import cc.cwalk.com.utils.StringCallback;
import cc.cwalk.com.webview.SingleActivity;
import cc.cwalk.com.webview.WebViewActivity;

/**
 * q
 * 教程
 */
public class TeachingFragment extends BaseListFragment {

    private GridLayoutManager layoutManager;

    @Override
    public void initView(View v) {
        super.initView(v);
        setBarGone();
        layoutManager = new GridLayoutManager(getActivity(), 3);
        mRcView.setLayoutManager(layoutManager);
        getData(1);
    }


    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<TeachingBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, TeachingBean item) {
                TextView textView = holder.getTextView(R.id.tv_name);
                int spanSize = layoutManager.getSpanSizeLookup().getSpanSize(position);
                if (spanSize == 1) {
                    textView.setBackgroundColor(getResources().getColor(R.color.transparent));
                    textView.setText(item.title);
                } else {
                    textView.setText(item.type);
                    textView.setBackgroundColor(getResources().getColor(R.color.gray));
                }
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_teaching;
            }


            @Override
            public Context getContext() {
                return getActivity();
            }

        };
    }

    String[] url = {"http://chengw32.com:8080/videos/sample.mp4",
            "http://chengw32.com:8080/videos/FE73411CFCC4935215173B4B071B75A8.mp4",
            "http://chengw32.com:8080/videos/01AC2E0111D3A6BA0D2B1F9904D6EA3E.mp4",
            "http://chengw32.com:8080/videos/8AF705836C6694848E227F3D7F5D4ABD.mp4",
            "http://chengw32.com:8080/videos/062E0B53EA8BBB94F6B0CEFD87D8286B.flv",
            "http://chengw32.com:8080/videos/F22630B947F92D63E79A84DA1DE398B9.mp4"};


    @Override
    public void onItemClick(View itemView, int pos) {

        TeachingBean o = (TeachingBean) mRcView.getDataContent().get(pos);
        if (TextUtils.isEmpty(o.url))return;
        SingleActivity.startActivity(xContext,  o.url);
    }

    int[] singlePosition = {0, 10, 16, 25};

    @Override
    public void getData(int pageNo) {


        mRcView.clearDataContent();
        mRcView.getDataContent().addAll(DataUtils.getInstance().getTeaching());
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                TeachingBean teachingBean = (TeachingBean) mRcView.getDataContent().get(position);
                if (teachingBean.istitle == 1)return 3;
                else return 1 ;
            }
        });
        mRcView.complete();
    }


}
