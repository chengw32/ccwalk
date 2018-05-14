package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;

/**
 * Creat By_Chen
 * Time 2018/5/13 10:02
 * Des 采购信息
 */
public class PurchaseActivity extends BaseListActivity {


    @Override
    protected void initData() {
        getData(1);
    }

    @Override
    protected void initView() {
        super.initView();
        mRcView.addLineDivider();
    }

    @Override
    protected String setTitle() {
        return "社团采购";
    }

    @Override
    public void getData(int pageNo) {
        DataUtils.getInstance().getGroupList(mRcView);
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<GroupInfoBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, GroupInfoBean item) {
                holder.getTextView(R.id.tv_title).setText(item.getTitle());
                holder.getTextView(R.id.tv_money).setText(item.getMoney());
                holder.getTextView(R.id.tv_manager).setText("负责人："+item.getNiname());
                holder.getTextView(R.id.tv_time).setText(item.getTime());
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_purchase;
            }

            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        PurchaseDetailActivity.startActivity(xContext,(GroupInfoBean)mRcView.getDataContent().get(pos));
    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext, PurchaseActivity.class));
    }

}
