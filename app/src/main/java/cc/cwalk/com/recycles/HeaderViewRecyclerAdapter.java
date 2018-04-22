package cc.cwalk.com.recycles;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RecycleView添加头部
 */
public class HeaderViewRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADERS_START = Integer.MIN_VALUE;

    private static final int FOOTERS_START = Integer.MIN_VALUE + 10;

    private static final int ITEMS_START = Integer.MIN_VALUE + 20;

    private static final int ADAPTER_MAX_TYPES = 100;

    private RecyclerView.Adapter mWrappedAdapter;

    private List<View> mHeaderViews, mFooterViews;

    private Map<Class, Integer> mItemTypesOffset;
    private RecyclerView mRecyclerView;

    public HeaderViewRecyclerAdapter(RecyclerView.Adapter adapter) {

        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
        mItemTypesOffset = new HashMap<>();
        setWrappedAdapter(adapter);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {

        if (mWrappedAdapter != null && mWrappedAdapter.getItemCount() > 0) {
            notifyItemRangeRemoved(getHeaderCount(), mWrappedAdapter.getItemCount());
        }
        setWrappedAdapter(adapter);
        notifyItemRangeInserted(getHeaderCount(), mWrappedAdapter.getItemCount());
    }

    @Override
    public int getItemViewType(int position) {

        int hCount = getHeaderCount();
        if (position < hCount) return HEADERS_START + position;
        else {
            int itemCount = mWrappedAdapter.getItemCount();
            if (position < hCount + itemCount) {
                return getAdapterTypeOffset() + mWrappedAdapter.getItemViewType(position - hCount);
            } else return FOOTERS_START + position - hCount - itemCount;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType < HEADERS_START + getHeaderCount())
            return new StaticViewHolder(mHeaderViews.get(viewType - HEADERS_START));
        else if (viewType < FOOTERS_START + getFooterCount())
            return new StaticViewHolder(mFooterViews.get(viewType - FOOTERS_START));
        else {
            return mWrappedAdapter.onCreateViewHolder(viewGroup, viewType - getAdapterTypeOffset());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        int hCount = getHeaderCount();
        if (position >= hCount && position < hCount + mWrappedAdapter.getItemCount())
            mWrappedAdapter.onBindViewHolder(viewHolder, position - hCount);
    }

    public void addHeaderView(View headView) {

        if (null == headView) return;
        ifGridLayoutManager();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headView.setLayoutParams(params);
        mHeaderViews.add(headView);
    }


    public void addFooterView(View v) {
        if (null != v) {
            if (mFooterViews.contains(v)) {
//                removeFooter(v);
                return;
            }
            ifGridLayoutManager();
            mFooterViews.add(v);
            notifyDataSetChanged();
        }
    }

    public boolean removeFooter(View v) {
        if (null != v) {
            if (mFooterViews.contains(v)) {
                mFooterViews.remove(v);
                notifyDataSetChanged();
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public int getItemCount() {

        return getHeaderCount() + getFooterCount() + getWrappedItemCount();
    }

    public int getWrappedItemCount() {

        return mWrappedAdapter.getItemCount();
    }

    public int getHeaderCount() {

        return mHeaderViews.size();
    }

    public int getFooterCount() {

        return mFooterViews.size();
    }


    private void setWrappedAdapter(RecyclerView.Adapter adapter) {

        if (mWrappedAdapter != null) mWrappedAdapter.unregisterAdapterDataObserver(mDataObserver);
        mWrappedAdapter = adapter;
        Class adapterClass = mWrappedAdapter.getClass();
        if (!mItemTypesOffset.containsKey(adapterClass)) putAdapterTypeOffset(adapterClass);
        mWrappedAdapter.registerAdapterDataObserver(mDataObserver);
    }



    private void putAdapterTypeOffset(Class adapterClass) {

        mItemTypesOffset.put(adapterClass, ITEMS_START + mItemTypesOffset.size() * ADAPTER_MAX_TYPES);
    }


    private int getAdapterTypeOffset() {

        return mItemTypesOffset.get(mWrappedAdapter.getClass());
    }


    private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {

            super.onChanged();
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {

            super.onItemRangeChanged(positionStart, itemCount);
            notifyItemRangeChanged(positionStart + getHeaderCount(), itemCount);
        }


        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {

            super.onItemRangeInserted(positionStart, itemCount);
            notifyItemRangeInserted(positionStart + getHeaderCount(), itemCount);
        }


        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {

            super.onItemRangeRemoved(positionStart, itemCount);
            notifyItemRangeRemoved(positionStart + getHeaderCount(), itemCount);
        }


        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {

            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            int hCount = getHeaderCount();
            notifyItemRangeChanged(fromPosition + hCount, toPosition + hCount + itemCount);
        }
    };



    private static class StaticViewHolder extends RecyclerView.ViewHolder {

        public StaticViewHolder(View itemView) {

            super(itemView);
        }
    }


    /**
     * Author 陈国武
     * Time 2018/3/2 15:37
     * Des  添加头部底部 适配 GridLayoutManager
     */


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (getItemViewType(position) < HEADERS_START + getHeaderCount() || getItemViewType(position) < FOOTERS_START + getFooterCount()) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView();
    }

    private boolean isFooterView(int position) {
        return haveFooterView();
    }

    private boolean haveHeaderView() {
        return mHeaderViews.size() > 0;
    }

    public boolean haveFooterView() {
        return mFooterViews.size() > 0;
    }



    //添加行点击事件
    public void setItemClick(BaseRecyclerAdapter.OnItemClickListener listener) {
        if (null != listener && mWrappedAdapter instanceof BaseRecyclerAdapter) {
            BaseRecyclerAdapter a = (BaseRecyclerAdapter) mWrappedAdapter;
            a.setOnItemClickListener(listener);
        }
    }


    //添加数据
    public void addData(List list){
        if ( mWrappedAdapter instanceof BaseRecyclerAdapter) {
            BaseRecyclerAdapter a = (BaseRecyclerAdapter) mWrappedAdapter;
            a.addData(list);
        }
    }


    public List getDataContent() {
        if ( mWrappedAdapter instanceof BaseRecyclerAdapter) {
            BaseRecyclerAdapter a = (BaseRecyclerAdapter) mWrappedAdapter;
            return a.getDataContent();
        }
        return null ;
    }

}
