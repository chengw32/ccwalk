package cc.cwalk.com.tab_four;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.tab_one.HotFragment;
import cc.cwalk.com.tab_one.NewestFragment;
import cc.cwalk.com.tab_one.TeachingFragment;

public class VideoManagementActivity extends BaseActivity {
    @Bind(R.id.tv_history)
    TextView tvHistory;
    @Bind(R.id.tv_cache)
    TextView tvCache;
    @Bind(R.id.tv_collection)
    TextView tvCollection;
    private ViewPager baseViewPager;
    private PageAdapter basePagerAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_video_management;
    }

    @Override
    protected String setTitle() {
        return "视频管理";
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
                        tvCollection.setTextColor(Color.WHITE);
                        tvCache.setTextColor(Color.WHITE);
                        break;
                    case 1:
                        tvCache.setTextColor(getResources().getColor(R.color.blue));
                        tvCollection.setTextColor(Color.WHITE);
                        tvHistory.setTextColor(Color.WHITE);
                        break;
                    case 2:
                        tvCollection.setTextColor(getResources().getColor(R.color.blue));
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
        xContext.startActivity(new Intent(xContext, VideoManagementActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_history, R.id.tv_cache, R.id.tv_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_history:
                baseViewPager.setCurrentItem(0);
                break;
            case R.id.tv_cache:
                baseViewPager.setCurrentItem(1);
                break;
            case R.id.tv_collection:
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
                        newestFragment = new VideoFragment();
                    return newestFragment;
                case 1:
                    if (null == hotFragment)
                        hotFragment = new VideoFragment();
                    return hotFragment;
                default:
                    if (null == teachingFragment)
                        teachingFragment = new VideoFragment();
                    return teachingFragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
