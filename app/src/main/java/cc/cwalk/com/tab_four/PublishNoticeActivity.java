package cc.cwalk.com.tab_four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.utils.EventUtil;
import cc.cwalk.com.utils.ToastUtils;

public class PublishNoticeActivity extends BaseActivity {


    @Bind(R.id.et_content)
    EditText etContent;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_publish_notice;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected String setTitle() {
        return "编辑内容";
    }

    @Override
    public void onRightClick() {
        String trim = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(trim)){ToastUtils.s("内容不能为空");
        return;}
        EventUtil.sendEvent(EventUtil.NOTICE_PUBLISH,trim);
        ToastUtils.s("发布成功");
        finish();
    }

    @Override
    public void setRightText(String text) {
        super.setRightText("发布");
    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, PublishNoticeActivity.class));
    }

}
