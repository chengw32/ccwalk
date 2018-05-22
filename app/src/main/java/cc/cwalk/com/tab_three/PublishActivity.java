package cc.cwalk.com.tab_three;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.cwalk.com.MyApplication;
import cc.cwalk.com.R;
import cc.cwalk.com.utils.ToastUtils;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.custom_view.AutoFlowLayout;
import cc.cwalk.com.utils.GlideUtils;

public class PublishActivity extends BaseActivity {

    private final static String DATA = "DATA";
    private int image_width;
    @Bind(R.id.af_images)
    AutoFlowLayout mAfImages;
    private  List<LocalMedia> selectList ;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initView() {
      image_width =   (int) (MyApplication.getScreenWidth(PublishActivity.this)/3 -20*MyApplication.getScale());
        selectList = (List<LocalMedia>) getIntent().getSerializableExtra(DATA);
        refreshImages();

    }

    @Override
    public void onRightClick() {
        ToastUtils.s("发布成功 积分 +1");
        finish();
    }

    //展示选择的图片
    private void refreshImages(){
        if (null == selectList)return;
        mAfImages.removeAllViews();
        for (int i = 0; i <selectList.size(); i++) {

            ImageView imageView = getImageView(selectList.get(i).getPath());
            mAfImages.addView(imageView);

        }
        if (selectList.size()< 9){
            ImageView imageView = getImageView("");
            mAfImages.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PictureSelector.create(PublishActivity.this)
                            .openGallery(PictureMimeType.ofAll())
                            .maxSelectNum(9-selectList.size())
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            });
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
                    selectList.addAll(selects);
                    refreshImages();
                    break;
            }
        }
    }

    private ImageView getImageView(String url){
       ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(image_width, image_width);
       ImageView imageView = new ImageView(xContext);
       imageView.setPadding(5, 5, 2, 2);
       imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
       imageView.setImageResource(R.mipmap.add_pic);
       imageView.setLayoutParams(lp);
       if (!TextUtils.isEmpty(url))
       GlideUtils.lodeLocalImage(url,imageView);
       return imageView;
   }

    @Override
    public void setRightText(String text) {
        super.setRightText("发布");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected String setTitle() {
        return "";
    }

    public static void startActivity(Context xContext, Object data) {
        Intent intent = new Intent(xContext, PublishActivity.class);
        intent.putExtra(DATA, (Serializable) data);
        xContext.startActivity(intent);
    }

}
