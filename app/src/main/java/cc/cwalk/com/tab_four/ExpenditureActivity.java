package cc.cwalk.com.tab_four;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.base.BaseListActivity;
import cc.cwalk.com.recycles.BaseRecyclerAdapter;
import cc.cwalk.com.recycles.RecyclerViewHolder;

/**
 * Creat By_Chen
 * Time 2018/5/12 21:49
 * Des 经费管理
 * */
public class ExpenditureActivity extends BaseActivity {



//
//    @Override
//    protected void initView() {
//        super.initView();
//        View view = LayoutInflater.from(xContext).inflate(R.layout.activity_expenditure_head, null);
//        TextView tv_time = view.findViewById(R.id.tv_time);
//        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("更新时间 yyyy-MM-dd   HH:mm:ss");
//        Date curDate =  new Date(System.currentTimeMillis());
//        String   str   =   formatter.format(curDate);
//        tv_time.setText(str);
//        mRcView.addHeadView(view);
//    }

    @Bind(R.id.tv_history)
    TextView tvHistory;
    @Bind(R.id.tv_cache)
    TextView tvCache;
    private ViewPager baseViewPager;
    private PageAdapter basePagerAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_expenditure;
    }

    @Override
    protected String setTitle() {
        return "社团费用";
    }

    @Override
    protected void initView() {
        baseViewPager = findViewById(R.id.viewpager);
        basePagerAdapter = new PageAdapter(getFragmentManager());
        baseViewPager.setAdapter(basePagerAdapter);
        baseViewPager.setOffscreenPageLimit(3);
        baseViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        tvHistory.setTextColor(getResources().getColor(R.color.blue));
                        tvCache.setTextColor(Color.WHITE);
                        break;
                    case 1:
                        tvCache.setTextColor(getResources().getColor(R.color.blue));
                        tvHistory.setTextColor(Color.WHITE);
                        break;
                    case 2:
                        tvHistory.setTextColor(Color.WHITE);
                        tvCache.setTextColor(Color.WHITE);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void initData() {

    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext, ExpenditureActivity.class));
    }


    @OnClick({R.id.tv_history, R.id.tv_cache})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_history:
                baseViewPager.setCurrentItem(0);
                break;
            case R.id.tv_cache:
                baseViewPager.setCurrentItem(1);
                break;
        }
    }

    public class PageAdapter extends FragmentStatePagerAdapter {
        private Fragment exIn;
        private Fragment exOut;

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragment(position);
        }

        private Fragment getFragment(int position) {
            switch (position) {
                case 0:
                    if (null == exIn)
                        exIn = ExpenditureFragment.newInstance("1","");
                    return exIn;
                default:
                    if (null == exOut)
                        exOut = ExpenditureFragment.newInstance("2","");
                    return exOut;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}