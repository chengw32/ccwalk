package cc.cwalk.com.tab_two;

import android.content.Context;
import android.content.Intent;


import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;

public class MyAttentionActivity extends BaseListActivity {


    @Override
    protected String setTitle() {
        return "我的关注";
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, Object item) {

            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.myattention_item_layout;
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
        context.startActivity(new Intent(context, MyAttentionActivity.class));
    }

    @Override
    protected void initData() {
        getData(1);
    }
}
