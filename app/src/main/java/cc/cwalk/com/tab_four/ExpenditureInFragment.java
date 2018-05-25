package cc.cwalk.com.tab_four;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;

public class ExpenditureInFragment extends BaseListFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;



    public static ExpenditureInFragment newInstance(String param1, String param2) {
        ExpenditureInFragment fragment = new ExpenditureInFragment();
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
        return new BaseRecyclerAdapter<UserBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, UserBean item) {

                holder.getTextView(R.id.tv_mark).setText("会员 "+item.name+ " 交团费");
                holder.getTextView(R.id.tv_money_change).setText("+200");
                holder.getTextView(R.id.tv_time).setText("时间 "+item.jointime);
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
    }

    @Override
    public void getData(int pageNo) {
       mRcView.clearDataContent();
       mRcView.getDataContent().addAll(DataUtils.getInstance().getGroupMemberList());
       mRcView.complete();
    }

}