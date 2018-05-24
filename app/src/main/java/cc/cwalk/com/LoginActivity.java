package cc.cwalk.com;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GsonUtil;
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

        List<UserBean> userList = GsonUtil.getUserList(SPUtils.getUser());
        for (int i = 0; i < userList.size(); i++) {
            UserBean userBean = userList.get(i);
            if (userBean.name.equals(phone)) {
                if ("123456".equals(psw)) {

                    ToastUtils.s("登录成功");
                    SPUtils.setIsLogin(true);
                    SPUtils.setId(userBean.id);
                    EventUtil.sendEvent(EventUtil.ACT_LOGIN, null);
                    EventUtil.sendEvent(EventUtil.ACT_REFRESH, null);
                    finish();
                } else ToastUtils.s("密码不正确");
                return;
            }
        }
        ToastUtils.s("账号不对");

    }
}