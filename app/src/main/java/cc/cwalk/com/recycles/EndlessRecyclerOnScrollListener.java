package cc.cwalk.com.recycles;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * RecycleView上拉加载更多
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener
{

    public static String TAG = EndlessRecyclerOnScrollListener.class.getSimpleName();

    private int previousTotal = 1;

    private boolean loading = true;

    private int LEAST_COUNT = 5;//至少大于10个才执行判断是否加载更多

    int lastCompletelyVisiableItemPosition, visibleItemCount, totalItemCount;

    private int currentPage = 0;

    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if(layoutManager instanceof GridLayoutManager) {
            this.mGridLayoutManager = (GridLayoutManager) layoutManager;
            mLinearLayoutManager=null;
        }
        else if(layoutManager instanceof LinearLayoutManager){
            this.mLinearLayoutManager = (LinearLayoutManager) layoutManager;
            mGridLayoutManager=null;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy){

        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        if(mLinearLayoutManager!=null) {
            totalItemCount = mLinearLayoutManager.getItemCount();
            lastCompletelyVisiableItemPosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
        } else if(mGridLayoutManager!=null) {
            totalItemCount = mGridLayoutManager.getItemCount();
            lastCompletelyVisiableItemPosition =mGridLayoutManager.findLastCompletelyVisibleItemPosition();
        }

        if (loading)
        {
            if (totalItemCount > previousTotal&&totalItemCount>=LEAST_COUNT)//至少大于10个才执行加载更多
            {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading&& (visibleItemCount > 0)&& (lastCompletelyVisiableItemPosition >= totalItemCount - 1)){
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);

    /**
     * Des：重置
     * Author：林智聪
     * Time：2016/12/22 11:39
     */
    public void setReset(){
        currentPage=0;
        previousTotal=1;
        loading = true;
    }
}