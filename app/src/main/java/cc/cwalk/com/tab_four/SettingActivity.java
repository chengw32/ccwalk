package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.LoginActivity;
import cc.cwalk.com.R;
import cc.cwalk.com.ToastUtils;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.custom_view.CustomDialog;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.SPUtils;

/**
 * @描述 设置界面
 */
public class SettingActivity extends BaseActivity {

    Intent intent;
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.top_bar)
    RelativeLayout mTopBar;
    @Bind(R.id.clean_cache)
    TextView mCleanCache;
    @Bind(R.id.tv_about)
    TextView mTvAbout;
    @Bind(R.id.exit)
    TextView mExit;


    private View mExitDialogView;
    private CustomDialog mDialog;


    @Override
    protected int setContentLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        if (SPUtils.isLogin()){mExit.setText("退出");}else {mExit.setText("登录");}
    }


    @Override
    protected void initData() {

    }

    @Override
    protected String setTitle() {
        return "设置";
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }


    @OnClick({R.id.clean_cache, R.id.tv_about, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clean_cache:
                Toast.makeText(xContext,"正在清除缓存...",Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                    mExitDialogView = View.inflate(this, R.layout.dialog_exit, null);
                    mDialog = new CustomDialog(this, mExitDialogView, R.style.dialog);

                    TextView tvExitAccount = mExitDialogView.findViewById(R.id.tvExitAccount);
                tvExitAccount.setText(SPUtils.isLogin()?"退出登录":"登录账号");

                    tvExitAccount.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //退出app
                            mDialog.dismiss();
                            if (SPUtils.isLogin()){

                            ToastUtils.s("已退出当前账号");
                            SPUtils.setIsLogin(false);
                            EventUtil.sendEvent(EventUtil.ACT_LOGIN,null);
                            }else {
                                LoginActivity.startActivity(xContext);
                                finish();
                            }
                        }
                    });


                    mDialog.show();
                break;
        }
    }
}
