package cc.cwalk.com.recycles;

/**
 * Des:
 * Author Administrator
 * Time: 2016/12/21
 */


public interface OnRefreshLoadMoreListener {
	void getData(int pageNo);

	void onLoadMore(int pageNo);
}
