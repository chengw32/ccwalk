package cc.cwalk.com.recycles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
	protected List<T> mData;
	protected  Context mContext;
	protected LayoutInflater mInflater;
	private OnItemClickListener mClickListener;
	private OnItemLongClickListener mLongClickListener;


	public BaseRecyclerAdapter() {
		mInflater = LayoutInflater.from(mContext= getContext());
		mData =new ArrayList<T>() ;
	}

	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			final  RecyclerViewHolder holder = new RecyclerViewHolder(mContext,
				mInflater.inflate(getItemLayoutId(viewType), parent, false));

		if (mClickListener != null) {
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
				}
			});
		}
		if (mLongClickListener != null) {
			holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					mLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
					return true;
				}
			});
		}
		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder holder, int position) {
		bindData(holder, position, mData.get(position));
	}

	@Override
	public int getItemCount() {
		return getCount();
	}

	private int getCount(){
		return mData.size();
	}

	public void addData(List list){
		mData.addAll(list);
	}

	public List getDataContent() {
		return mData;
	}


	public void setData(List<T> list){
		mData = (list != null) ? list : new ArrayList<T>();
		this.notifyDataSetChanged();
	}

	public void add(int pos, T item) {
		mData.add(pos, item);
		notifyItemInserted(pos);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mClickListener = listener;
	}

	public void setOnItemLongClickListener(OnItemLongClickListener listener) {
		mLongClickListener = listener;
	}


	abstract public void bindData(RecyclerViewHolder holder, int position, T item);
	abstract public int getItemLayoutId(int viewType);
	abstract public Context getContext();



	public interface OnItemClickListener {
		public void onItemClick(View itemView, int pos);
	}

	public interface OnItemLongClickListener {
		public void onItemLongClick(View itemView, int pos);
	}


	public void celean(){
		mClickListener=null;
		mLongClickListener=null;
		if(mData!=null)
			mData.clear();
		mData=null;
		mContext=null;

	}

}
