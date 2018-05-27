package cc.cwalk.com;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.ToastUtils;
import cc.cwalk.com.utils.Utils;

public class RegistActivity extends BaseActivity {


    @Bind(R.id.etName)
    EditText mEtName;
    @Bind(R.id.etPwd)
    EditText mEtPwd;
    @Bind(R.id.etPwd2)
    EditText mEtPwd2;
    @Bind(R.id.btnRegist)
    Button mBtnRegist;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btnRegist)
    public void onViewClicked() {

        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            ToastUtils.s("用户名不能为空");
            return;
        }
        String pwd = mEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            ToastUtils.s("密码不能为空");
            return;
        }
        String pwd2 = mEtPwd2.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            ToastUtils.s("确认密码不能为空");
            return;
        }

        if (!pwd.equals(pwd2)){
            ToastUtils.s("两次密码不一致");
            return;
        }

        UserBean userBean = new UserBean();
        List userList = DataUtils.getInstance().getUserList();
        userBean.id = userList.size() + 1;
        userBean.jointime = Utils.getTime();
        userBean.name = name;
        userBean.sex = 1 ;
        userBean.pwd = pwd ;
        userList.add(userBean);
        SPUtils.setUser(GsonUtil.toJosn(userList));

        SPUtils.setIsLogin(true);
        SPUtils.setId(userBean.id);
        EventUtil.sendEvent(EventUtil.ACT_regist, null);
        EventUtil.sendEvent(EventUtil.ACT_LOGIN, null);
        EventUtil.sendEvent(EventUtil.ACT_REFRESH, null);
        ToastUtils.s("注册成功,已为您登录");
        finish();

    }
}
