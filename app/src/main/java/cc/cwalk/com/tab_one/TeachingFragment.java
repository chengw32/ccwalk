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


    @Override
    public void onItemClick(View itemView, int pos) {

        TeachingBean o = (TeachingBean) mRcView.getDataContent().get(pos);
        if (TextUtils.isEmpty(o.url))return;
        SingleActivity.startActivity(xContext,  o.url);
    }


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
