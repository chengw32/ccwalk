package cc.cwalk.com.tab_four;

import android.content.Context;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;

public class GroupNoticeActivity extends BaseListActivity {


    @Override
    protected String setTitle() {
        return "公告";
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, Object item) {

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
    public void getData(int pageNo) {
        List dataContent =mRcView.getDataContent();
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        dataContent.add(1);
        mRcView.complete();
    }

    @Override
    protected void initData() {
        getData(1);
    }
}
