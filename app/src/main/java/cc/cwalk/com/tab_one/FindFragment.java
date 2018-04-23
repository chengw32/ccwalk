package cc.cwalk.com.tab_one;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import cc.cwalk.com.MainActivity;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseFragment;

/**
 * 发现页
 */
public class FindFragment extends BaseFragment {

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
        MainActivity act = (MainActivity) getActivity();
        basePagerAdapter = new PageAdapter(act.getSupportFragmentManager());
        baseViewPager.setAdapter(basePagerAdapter);
        baseViewPager.setOffscreenPageLimit(3);
        baseViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }


    public class PageAdapter extends FragmentStatePagerAdapter {
        private Fragment hotFragment;
        private Fragment teachingFragment;
        private Fragment nowFragment;

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return getFragment(position);
        }

        private Fragment getFragment(int position) {
            switch (position) {
                case 0:
                    if (null == nowFragment)
                        nowFragment = new TeachingFragment();
                    return nowFragment;
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
