package cc.cwalk.com.tab_four;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.LoginActivity;
import cc.cwalk.com.R;
import cc.cwalk.com.ToastUtils;
import cc.cwalk.com.base.BaseFragment;
import cc.cwalk.com.credits.CreditsActivity;
import cc.cwalk.com.tab_one.UserHomePagerActivity;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.SPUtils;


/**
 * @描述 我
 */
public class MeFragment extends BaseFragment {


    @Bind(R.id.mine_sign_tv)
    TextView mMineSignTv;
    @Bind(R.id.ll_sign)
    LinearLayout mLlSign;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_des)
    TextView mTvDes;
    @Bind(R.id.iv_sex)
    ImageView mIvSex;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected String setTitle() {
        return "我的";
    }

    @Override
    protected void onRightClick() {
        MyMessageActivity.startActivity(xContext);
    }

    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.ACT_LOGIN.equals(event.getAction())) {
            refreshLayout();
        }
    }

    /**
     * Creat By_Chen
     * Time 2018/4/21 15:39
     * Des 刷新登录状态
     */
    private void refreshLayout() {
        mIvSex.setImageResource(SPUtils.getSex() == 1 ? R.mipmap.ic_gender_male : R.mipmap.ic_gender_female);
        if (SPUtils.isLogin()) {
            mLlSign.setVisibility(View.VISIBLE);
            mTvDes.setVisibility(View.VISIBLE);
            mIvSex.setVisibility(View.VISIBLE);
            mTvName.setText(SPUtils.getUserName());
            mTvDes.setText("社团成员");
        } else {
            mTvName.setText("请登录");
            mTvDes.setVisibility(View.GONE);
            mLlSign.setVisibility(View.GONE);
            mIvSex.setVisibility(View.GONE);
        }


    }

    @Override
    public void initView(View v) {
        super.initView(v);

        setBackButtonGone();//隐藏返回键
        setRightVisable();//显示右边的按钮
        //刷新界面数据
        refreshLayout();

        View mygroup_collection = initItem(v, R.id.mygroup, "我的社团");
        mygroup_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SPUtils.isLoginWithToast())return;
                startActivity(new Intent(xContext, GroupNoticeActivity.class));
            }
        });
        View group_collection = initItem(v, R.id.group_notice, "社团公告");
        group_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(xContext, GroupNoticeActivity.class));
            }
        });
        View join_collection = initItem(v, R.id.group_join, "入团申请");
        join_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(xContext, GroupNoticeActivity.class));
            }
        });
        View hot_collection = initItem(v, R.id.hot_video, "申请热榜");
        hot_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(xContext, GroupNoticeActivity.class));
            }
        });
        View fans_collection = initItem(v, R.id.myfans, "我的粉丝");
        fans_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SPUtils.isLoginWithToast())return;
                MyFansActivity.startActivity(xContext);
            }
        });
        View setting_collection = initItem(v, R.id.settings, "设置");
        setting_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.startActivity(xContext);
            }
        });

        View my_credits_collection = initItem(v, R.id.my_credits, "我的积分");
        my_credits_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SPUtils.isLoginWithToast())return;
                CreditsActivity.startActivity(xContext);
            }
        });
        View message_collection = initItem(v, R.id.my_download, "视频管理");
        message_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SPUtils.isLoginWithToast())return;
                MyMessageActivity.startActivity(xContext);
            }
        });
        View my_homepager = initItem(v, R.id.my_homepager, "我的主页");
        my_homepager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SPUtils.isLoginWithToast())return;
                UserHomePagerActivity.startActivity(xContext);
            }
        });

    }

    //设置item数据
    private View initItem(View v, int id, String str) {
        View my_collection = v.findViewById(id);
        TextView collection_name = my_collection.findViewById(R.id.tv_name);
        collection_name.setText(str);
        return my_collection;
    }


    @OnClick({R.id.ll_sign, R.id.llMyInfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sign:
                ToastUtils.s("已签到 积分 +1");
                mMineSignTv.setText("已签到");
                break;
            case R.id.llMyInfo:
                if (SPUtils.isLogin())
                    UserInfoActivity.startActivity(xContext);
                else
                    LoginActivity.startActivity(xContext);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
