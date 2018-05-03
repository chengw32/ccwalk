package cc.cwalk.com.tab_one;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseFragment;
import cc.cwalk.com.beans.DetailBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.LogUtils;

/**
 * 现在模块的主容器
 */
public class FindFragment extends BaseFragment {

    @Bind(R.id.tv_newest)
    TextView mTvNewest;
    @Bind(R.id.tv_hot)
    TextView mTvHot;
    @Bind(R.id.tv_teaching)
    TextView mTvTeaching;
    private ViewPager baseViewPager;
    private PageAdapter basePagerAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView(View v) {
        super.initView(v);
        baseViewPager = v.findViewById(R.id.viewpager);
        basePagerAdapter = new PageAdapter(getFragmentManager());
        baseViewPager.setAdapter(basePagerAdapter);
        baseViewPager.setOffscreenPageLimit(3);
        baseViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTvNewest.setTextColor(getResources().getColor(R.color.blue));
                        mTvHot.setTextColor(Color.WHITE);
                        mTvTeaching.setTextColor(Color.WHITE);
                        break;
                    case 1:
                        mTvNewest.setTextColor(Color.WHITE);
                        mTvHot.setTextColor(getResources().getColor(R.color.blue));
                        mTvTeaching.setTextColor(Color.WHITE);
                        break;
                    case 2:
                        mTvNewest.setTextColor(Color.WHITE);
                        mTvHot.setTextColor(Color.WHITE);
                        mTvTeaching.setTextColor(getResources().getColor(R.color.blue));
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }



    @OnClick({R.id.tv_newest, R.id.tv_hot, R.id.tv_teaching})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_newest:
                baseViewPager.setCurrentItem(0);
                break;
            case R.id.tv_hot:
                baseViewPager.setCurrentItem(1);
                break;
            case R.id.tv_teaching:
                baseViewPager.setCurrentItem(2);
                break;
        }
    }


    public class PageAdapter extends FragmentStatePagerAdapter {
        private Fragment hotFragment;
        private Fragment newestFragment;
        private Fragment teachingFragment;

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
                    if (null == newestFragment)
                        newestFragment = new NewestFragment();
                    return newestFragment;
                case 1:
                    if (null == hotFragment)
                        hotFragment = new HotFragment();
                    return hotFragment;
                default:
                    if (null == teachingFragment)
                        teachingFragment = new TeachingFragment();
                    return teachingFragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
