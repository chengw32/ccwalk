package cc.cwalk.com.tab_four;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.DetailActivity;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;

public class ExpenditureFragment extends BaseListFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;



    public static ExpenditureFragment newInstance(String param1, String param2) {
        ExpenditureFragment fragment = new ExpenditureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }





    @Override
    public void initView(View v) {
        super.initView(v);
        setBarGone();
        mRcView.addLineDivider();
        getData(1);

    }

    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, DataBean item) {
                holder.getTextView(R.id.tv_mark).setText("1".equals(mParam1)?"订制团服":"会员 海草 交团费");
                holder.getTextView(R.id.tv_money_change).setText("1".equals(mParam1)?"-300":"+200");
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.fragment_expenditure;
            }


            @Override
            public Context getContext() {
                return getActivity();
            }

        };
    }


    @Override
    public void onItemClick(View itemView, int pos) {
        DetailActivity.startActivity(getActivity(), (DataBean) mRcView.getDataContent().get(pos));
    }

    @Override
    public void getData(int pageNo) {
        DataUtils.getInstance().getDataList(mRcView);
    }

}