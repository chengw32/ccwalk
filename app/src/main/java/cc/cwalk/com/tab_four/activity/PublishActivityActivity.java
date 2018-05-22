package cc.cwalk.com.tab_four.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.ToastUtils;

public class PublishActivityActivity extends BaseActivity {


    @Bind(R.id.iv_image)
    ImageView mIvImage;
    @Bind(R.id.et_title)
    EditText mEtTitle;
    @Bind(R.id.et_time)
    EditText etTime;
    @Bind(R.id.et_adress)
    EditText etAdress;
    @Bind(R.id.et_money)
    EditText etMoney;
    @Bind(R.id.et_des)
    EditText etDes;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_publish_activity;
    }

    @Override
    public void setRightText(String text) {
        super.setRightText("发布");
    }

    @Override
    protected String setTitle() {
        return "发布活动";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @Override
    public void onRightClick() {

        String title = mEtTitle.getText().toString().trim();
        if (TextUtils.isEmpty(title)){
            ToastUtils.s("标题不能为空");
            return;}
        String time = etTime.getText().toString().trim();
        if (TextUtils.isEmpty(time)){
            ToastUtils.s("时间不能为空");
            return;}
        String adress = etAdress.getText().toString().trim();
        if (TextUtils.isEmpty(adress)){
            ToastUtils.s("地点不能为空");
            return;}

        ToastUtils.s("发布成功");
        finish();
    }

    public static void startActivity(Context xContext) {
        xContext.startActivity(new Intent(xContext, PublishActivityActivity.class));
    }


    @OnClick({R.id.add_pic, R.id.et_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_pic:
                PictureSelector.create(PublishActivityActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .enableCrop(true)
                        .withAspectRatio(2, 1)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.et_title:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selects = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    GlideUtils.lodeLocalImage(selects.get(0).getCutPath(), mIvImage);
                    break;
            }
        }
    }


}
