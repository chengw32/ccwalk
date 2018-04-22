package cc.cwalk.com.base;

import android.support.annotation.CallSuper;
import android.view.View;

import cc.cwalk.com.R;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.OnRefreshLoadMoreListener;
import cc.cwalk.com.recycles.RefreshLoadMoreRecyclerView;


/**
 * Author chen_gw
 * Date 2018/4/12 15:12
 * DES :
 */
public abstract class BaseListFragment extends BaseFragment implements OnRefreshLoadMoreListener,BaseRecyclerAdapter.OnItemClickListener {

    protected RefreshLoadMoreRecyclerView mRcView;

    @CallSuper
    @Override
    public void initView(View v) {
        super.initView(v);
        mRcView =  v.findViewById(R.id.rc_list);
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(View itemView, int pos) {

    }


    @Override
    public void onLoadMore(int pageNo) {
        getData(pageNo);
    }
}
