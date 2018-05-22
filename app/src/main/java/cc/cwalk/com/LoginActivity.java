package cc.cwalk.com;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.ToastUtils;

/**
 * @描述 登录界面
 */
public class LoginActivity extends BaseActivity {


    @Bind(R.id.etPhone)
    EditText mEtPhone;
    @Bind(R.id.etPwd)
    EditText mEtPwd;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {

    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        String phone = mEtPhone.getText().toString().trim();
        String psw = mEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.s("用户不能为空");
            return;
        }
        if (TextUtils.isEmpty(psw)) {
            ToastUtils.s("密码不能为空");
            return;
        }

        if ("cwalk".equals(phone) || "张伟塔".equals(phone)) {

            if ("123456".equals(psw)){

                ToastUtils.s("登录成功");
                SPUtils.setUserName(phone);
                SPUtils.setUserName(phone);
                SPUtils.setIsLogin(true);
                SPUtils.setId(1);
                if ("张伟塔".equals(phone))
                    SPUtils.setCreat(1);
                else SPUtils.setCreat(0);
                EventUtil.sendEvent(EventUtil.ACT_LOGIN, null);
                finish();
            }else ToastUtils.s("密码不正确");

        }else ToastUtils.s("账号不对");

    }
}