package cc.cwalk.com.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.test.ServiceTestCase;
import android.view.View;

import cc.cwalk.com.R;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.OnRefreshLoadMoreListener;
import cc.cwalk.com.recycles.RefreshLoadMoreRecyclerView;


/**
 * Created by Chen on 2018/4/11.
 */

public abstract class BaseListActivity extends BaseActivity implements OnRefreshLoadMoreListener, BaseRecyclerAdapter.OnItemClickListener {

    protected RefreshLoadMoreRecyclerView mRcView;

    @CallSuper
    @Override
    protected void initView() {
        mRcView = findViewById(R.id.rc_list);
        if (null != mRcView)
            mRcView.setAdapter(getAdapter());
        mRcView.setOnRefreshLoadMoreListener(this);
        mRcView.setItemClick(this);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.base_recycle_layout;
    }

    protected abstract BaseRecyclerAdapter getAdapter();

    @Override
    public void onItemClick(View itemView, int pos) {

    }

    @Override
    public void onLoadMore(int pageNo) {
        getData(pageNo);
    }
}
