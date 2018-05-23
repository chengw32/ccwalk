package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.DialogUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.ToastUtils;

public class JoinGroupActivity extends BaseListActivity {


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
    public void getData(int pageNo) {
        DataUtils.getInstance().getDataList(mRcView);
    }


    @Override
    protected String setTitle() {
        return "申请列表";
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<DataBean>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, DataBean item) {
                    holder.getTextView(R.id.tv_name).setText(item.getName());
                    holder.getTextView(R.id.tv_des).setText(item.getStr().get(0).getDes());
                GlideUtils.lodeImage(item.getHead(),holder.getImageView(R.id.iv_head));
                final TextView tv_zang = holder.getTextView(R.id.tv_allow);
                tv_zang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!tv_zang.isSelected()) {

                            DialogUtils.showTopicDialogsCustom("确定同意加入？", xContext, new DialogUtils.DialogClickBack() {
                                @Override
                                public void define() {
                                    tv_zang.setSelected(true);
                                    tv_zang.setText("通过申请");
                                    ToastUtils.s("已通过申请");
                                }

                                @Override
                                public void cancel() {

                                }
                            });


                        }
                    }
                });
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.activity_join_group;
            }

            @Override
            public Context getContext() {
                return xContext;
            }

        };
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, JoinGroupActivity.class));
    }
}
