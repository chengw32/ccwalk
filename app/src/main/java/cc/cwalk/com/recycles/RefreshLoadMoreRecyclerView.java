package cc.cwalk.com.recycles;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.utils.LogUtils;


/**
 * Des:
 * Author Administrator
 * Time: 2016/12/21
 */

public class RefreshLoadMoreRecyclerView extends SwipeRefreshLayout {

    public RecyclerView mRecyclerView;
    private View footView;
    private HeaderViewRecyclerAdapter mHeaderViewRecyclerAdapter;
    private boolean mIsRefreshing = false;
    public int pageNo = 1;
    private OnRefreshLoadMoreListener onRefreshLoadMoreListener;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private TextView empty_TV;

    public RefreshLoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshLoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        //只能 addView 一次 ，所以用一个 FrameLayout 装起来

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        FrameLayout emptyContentLayout = new FrameLayout(getContext());
        emptyContentLayout.setLayoutParams(layoutParams);


        LayoutParams tv_lParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        empty_TV = new TextView(this.getContext());
        empty_TV.setText("暂无数据");
        empty_TV.setTextColor(Color.WHITE);
        empty_TV.setGravity(Gravity.CENTER);
        empty_TV.setLayoutParams(tv_lParams);
        emptyContentLayout.addView(empty_TV);

        mRecyclerView = new RecyclerView(this.getContext());
		emptyContentLayout.addView(mRecyclerView);

		this.addView(emptyContentLayout);
        initRefreshLayout();
        initRecycleView();
    }

    private void initRefreshLayout() {
        setColorSchemeResources(R.color.colorPrimary);
        setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //刷新监听
            @Override
            public void onRefresh() {
                RefreshLoadMoreRecyclerView.this.onRefresh();
            }
        });
    }


    public void initRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(mLinearLayoutManager) {

            @Override
            public void onLoadMore(int currentPage) {
                LogUtils.e("onLoadMore");
                if (isLoadMore)
                    RefreshLoadMoreRecyclerView.this.onLoadMore();
            }
        };
        mRecyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
        setRecycleScrollBug();
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
        if (endlessRecyclerOnScrollListener != null)
            endlessRecyclerOnScrollListener.setLayoutManager(mRecyclerView.getLayoutManager());
    }


    private void setRecycleScrollBug() {

        mRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (mIsRefreshing) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    /**
     * Des：显示刷新进度
     * Time：2016/12/21 16:32
     */
    public void showRefreshProgress() {

        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                RefreshLoadMoreRecyclerView.this.onRefresh();
            }
        }, 500);
    }

    /**
     * Des：刷新后要调用此方法，否则加载更多会出错
     * Time：2018/1/22 10:21
     */
    public void onRefreshReset() {

        endlessRecyclerOnScrollListener.setReset();
    }


    public void onRefresh() {
        if (!mIsRefreshing) {
            mIsRefreshing = true;
            setRefreshing(true);
            pageNo = 1;
            if (null != onRefreshLoadMoreListener)
                onRefreshLoadMoreListener.getData(pageNo);
            onRefreshReset();
        }
    }

    public void onLoadMore() {
        if (!mIsRefreshing) {
            mIsRefreshing = true;
            pageNo++;
            setLoadMoreView();
            if (null != onRefreshLoadMoreListener)
                onRefreshLoadMoreListener.onLoadMore(pageNo);
        }
    }

    /**
     * Des：刷新或加载更多完成要调用此方法
     * Time：2016/12/21 16:27
     */
    public void complete() {
        isDataEmpty();
        mHeaderViewRecyclerAdapter.notifyDataSetChanged();
        if (isRefreshing())
            setRefreshing(false);
        mIsRefreshing = false;
        setNoMoreView();
    }

    /**
     * Des：设置适配器
     * Time：2016/12/21 16:38
     */
    public void setAdapter(BaseRecyclerAdapter baseRecyclerAdapter) {
        mHeaderViewRecyclerAdapter = new HeaderViewRecyclerAdapter(baseRecyclerAdapter);
        createFootView();
        isDataEmpty();
        mRecyclerView.setAdapter(mHeaderViewRecyclerAdapter);
    }

    //空数据提示
    private void isDataEmpty() {
        if (null != mHeaderViewRecyclerAdapter) {
            int itemCount = mHeaderViewRecyclerAdapter.getItemCount();
            if (itemCount > 0) {
                empty_TV.setVisibility(View.GONE);

            } else empty_TV.setVisibility(View.VISIBLE);
        }
    }


    TextView footViewTv;
    View progressView;

    /**
     * Des：添加加载更多 footView
     * Time：2016/12/21 16:46
     */
    private void createFootView() {
        footView = LayoutInflater.from(this.getContext()).inflate(R.layout.load_more_foot_layout, mRecyclerView, false);
        footViewTv = (TextView) footView.findViewById(R.id.load_tv);
        progressView = footView.findViewById(R.id.load_pro);
        if (isLoadMore)
            mHeaderViewRecyclerAdapter.addFooterView(footView);
        setNoMoreView();
    }

    private boolean isLoadMore = true;

    /**
     * Des：是否开启加载更多
     * Time：2016/12/22 14:06
     */
    public void setEanbleLoadMore(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
        if (isLoadMore) {
            mHeaderViewRecyclerAdapter.addFooterView(footView);
        } else {
            mHeaderViewRecyclerAdapter.removeFooter(footView);
        }
    }


    /**
     * Des：没有更多了
     * Time：2016/12/22 11:49
     */
    private void setNoMoreView() {
        if (!isLoadMore || mHeaderViewRecyclerAdapter.getItemCount() <= 5) {
            footView.setVisibility(View.GONE);
            mHeaderViewRecyclerAdapter.removeFooter(footView);
        } else {
            mHeaderViewRecyclerAdapter.addFooterView(footView);
            footViewTv.setText("没有更多了");
            progressView.setVisibility(View.GONE);
            footView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Des：正在加载更多
     * Time：2016/12/22 11:51
     */
    private void setLoadMoreView() {
        mHeaderViewRecyclerAdapter.addFooterView(footView);
        footViewTv.setText("正在加载...");
        progressView.setVisibility(View.VISIBLE);
        footView.setVisibility(View.VISIBLE);
    }


    /**
     * Des：设置下拉刷新 加载更多 监听
     * Time：2016/12/21 16:45
     */
    public void setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        this.onRefreshLoadMoreListener = onRefreshLoadMoreListener;
    }
//============================ 相关 API =========================================

    //添加头部
    public void addHeadView(View v) {
        mHeaderViewRecyclerAdapter.addHeaderView(v);
    }

    //刷新适配器
    public void notifyDataSetChanged() {
        isDataEmpty();
        mHeaderViewRecyclerAdapter.notifyDataSetChanged();
    }

    //设置 item 点击事件
    public void setItemClick(BaseRecyclerAdapter.OnItemClickListener listener) {
        mHeaderViewRecyclerAdapter.setItemClick(listener);
    }

    //添加 分割线
    public void addLineDivider(){
        LineDividerItemDecoration divieder = new LineDividerItemDecoration();
        mRecyclerView.addItemDecoration(divieder);
    }

    //添加数据
    public void addDataToAdapter(List list){
        if (null == list)return;
        mHeaderViewRecyclerAdapter.addData(list);
    }
    //获取数据容器
    public List getDataContent(){
        return mHeaderViewRecyclerAdapter.getDataContent();
    }

}
