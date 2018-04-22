package cc.cwalk.com.tab_two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.DetailActivity;
import cc.cwalk.com.tab_one.DetailImagesActivity;
import cc.cwalk.com.tab_one.UserHomePagerActivity;

/**
 * Time 2018/4/11 14:12
 * Des  关注
 */
public class AttentionFragment extends BaseListFragment {


    @Override
    public void initView(View v) {
        super.initView(v);
        setBarGone();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_attention_headview, null);
        inflate.findViewById(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAttentionActivity.startActivity(getActivity());
            }
        });
        LinearLayout content = (LinearLayout) inflate.findViewById(R.id.content);
        for (int j = 0; j < 8; j++) {
            View item = LayoutInflater.from(getActivity()).inflate(R.layout.attention_head_item, null);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserHomePagerActivity.startActivity(getActivity());
                }
            });
            content.addView(item);
        }
        mRcView.addHeadView(inflate);
        getData(1);
    }


    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, Object item) {
                holder.getView(R.id.iv_head).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserHomePagerActivity.startActivity(getActivity());
                    }
                });
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.attention_item;
            }


            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    @Override
    protected String setTitle() {
        return "";
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        DetailImagesActivity.startActivity(getActivity());
    }

    @Override
    public void getData(int pageNo) {
        List dataContent = mRcView.getDataContent();

        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        dataContent.add("1");
        mRcView.complete();
    }


}
