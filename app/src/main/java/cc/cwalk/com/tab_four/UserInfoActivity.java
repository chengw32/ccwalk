package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.utils.ToastUtils;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.SPUtils;

public class UserInfoActivity extends BaseActivity {

    @Bind(R.id.personal_data_name)
    EditText mPersonalDataName;
    @Bind(R.id.bt_comit)
    TextView mBtComit;
    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.rb_man)
    RadioButton mRbMan;
    @Bind(R.id.rb_wman)
    RadioButton mRbWman;
    @Bind(R.id.et_qq)
    EditText mEtQq;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    protected String setTitle() {
        return "个人信息";
    }

    @Override
    protected void initView() {

        mPersonalDataName.setText(SPUtils.getUserName());
        mEtPhone.setText(SPUtils.getPhone());
        if (SPUtils.getSex() == 1){
            mRbMan.setChecked(true);
        }else mRbWman.setChecked(true);


    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, UserInfoActivity.class));
    }


    @OnClick(R.id.bt_comit)
    public void onViewClicked() {
        //手机号码
        String phone = mEtPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(phone))SPUtils.setPhone(phone);
        //名字
        String name = mPersonalDataName.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            ToastUtils.s("昵称不能为空");
            return;
        }
        if (!TextUtils.isEmpty(name))SPUtils.setUserName(name);
        //名字
        String  qq = mEtQq.getText().toString().trim();
        if (!TextUtils.isEmpty(qq))SPUtils.setQQName(qq);

        //性别
        if (mRbMan.isChecked()){
            SPUtils.setSex(1);
        }else SPUtils.setSex(2);

        EventUtil.sendEvent(EventUtil.ACT_LOGIN,null);
        finish();
    }
}
