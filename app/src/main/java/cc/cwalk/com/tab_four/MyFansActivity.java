package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.tab_one.UserHomePagerActivity;

public class MyFansActivity extends BaseListActivity {


    @Override
    protected String setTitle() {
        return "我的粉丝";
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        UserHomePagerActivity.startActivity(xContext);
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter() {
            @Override
            public void bindData(RecyclerViewHolder holder, final int position, Object item) {
                holder.getView(R.id.tv_remove).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mRcView.getDataContent().remove(position);
                        mRcView.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_my_fans;
            }


            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    @Override
    public void getData(int pageNo) {
        List dataList = mRcView.getDataContent();
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        mRcView.complete();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,MyFansActivity.class));
    }


    @Override
    protected void initData() {
        getData(1);
    }
}
