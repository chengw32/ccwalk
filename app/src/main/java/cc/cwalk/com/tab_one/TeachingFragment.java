package cc.cwalk.com.tab_one;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseListFragment;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.LogUtils;
import cc.cwalk.com.utils.StringCallback;
import cc.cwalk.com.webview.WebViewActivity;

/**q
 * 教程
 */
public class TeachingFragment extends BaseListFragment {

private GridLayoutManager layoutManager;

    @Override
    public void initView(View v) {
    super.initView(v);
    setBarGone();
    layoutManager = new GridLayoutManager(getActivity(), 3);
    mRcView.setLayoutManager(layoutManager);
    getData(1);
}


    protected BaseRecyclerAdapter getAdapter() {
        return new BaseRecyclerAdapter<String>() {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, String item) {
                TextView textView = holder.getTextView(R.id.tv_name);
                int spanSize = layoutManager.getSpanSizeLookup().getSpanSize(position);
                if (spanSize == 1){
                    textView.setBackgroundColor(getResources().getColor(R.color.transparent));
                }else textView.setBackgroundColor(getResources().getColor(R.color.gray));
                if (position == 25)
                textView.setText("其他");
                else
                textView.setText(item);
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_teaching;
            }


            @Override
            public Context getContext() {
                return getActivity();
            }

        };
    }

    String [] url = {"http://chengw32.com:8080/videos/sample.mp4",
            "http://chengw32.com:8080/videos/FE73411CFCC4935215173B4B071B75A8.mp4",
            "http://chengw32.com:8080/videos/01AC2E0111D3A6BA0D2B1F9904D6EA3E.mp4",
            "http://chengw32.com:8080/videos/8AF705836C6694848E227F3D7F5D4ABD.mp4",
            "http://chengw32.com:8080/videos/062E0B53EA8BBB94F6B0CEFD87D8286B.flv",
            "http://chengw32.com:8080/videos/F22630B947F92D63E79A84DA1DE398B9.mp4"};


    @Override
    public void onItemClick(View itemView, int pos) {

        if (singlePosition[0] == pos || singlePosition[1] == pos ||singlePosition[2] == pos ||singlePosition[3] == pos )
            return;
        WebViewActivity.startActivity(xContext,"教程", url[DataUtils.getInstance().getRandow(url.length)]);
    }

    int [] singlePosition = {0,10,16,25};

    @Override
    public void getData(int pageNo) {


        List dataContent = mRcView.getDataContent();
        dataContent.add("CRIP WALK");
        dataContent.add("cwalk教程");
        dataContent.add("c-walk教学2");
        dataContent.add("MAS风格");
        dataContent.add("Cwalk教学——弹性、基本动作和衔接_高清");
        dataContent.add("Cwalk全教程_高清");
        dataContent.add("Cwalk提高_高清");
        dataContent.add("Elector教学");
        dataContent.add("jumpStyle教学");
        dataContent.add("CWALK新手教程_标清");
        dataContent.add("CROWN WALK");
        dataContent.add("Freestyle教学");
        dataContent.add("Freestep教学");
        dataContent.add("jumpStyle教学");
        dataContent.add("cwalk中级");
        dataContent.add("Kecet Cwalk 【Heel toe】教程_标清");
        dataContent.add("CLOWN WALK");
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0)return  3;
                if (position == 10)return  3;
                if (position == 16)return  3;
                if (position == 25)return  3;
                return 1;
            }
        });
        mRcView.complete();
    }


}
