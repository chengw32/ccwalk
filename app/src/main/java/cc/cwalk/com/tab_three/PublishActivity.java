package cc.cwalk.com.tab_three;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.cwalk.com.MyApplication;
import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.custom_view.AutoFlowLayout;
import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.GlideUtils;
import cc.cwalk.com.utils.GsonUtil;
import cc.cwalk.com.utils.LogUtils;
import cc.cwalk.com.utils.SPUtils;
import cc.cwalk.com.utils.ToastUtils;
import cc.cwalk.com.utils.Utils;

public class PublishActivity extends BaseActivity {

    private final static String DATA = "DATA";
    @Bind(R.id.et_content)
    EditText etContent;
    private int image_width;
    @Bind(R.id.af_images)
    AutoFlowLayout mAfImages;
    private List<LocalMedia> selectList;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initView() {
        image_width = (int) (MyApplication.getScreenWidth(PublishActivity.this) / 3 - 20 * MyApplication.getScale());
        selectList = (List<LocalMedia>) getIntent().getSerializableExtra(DATA);
        refreshImages();

    }

    @Override
    public void onRightClick() {


        String content = etContent.getText().toString().trim();

        List<AllDataBean> allList = DataUtils.getInstance().getAllList();

        AllDataBean bean = new AllDataBean();
        bean.userid = SPUtils.getId();
        bean.zang = new ArrayList<>();
        bean.evaluate = new ArrayList<>();


        //开始填装 媒体数据
        AllDataBean.VideoBean videoBean = new AllDataBean.VideoBean();
        videoBean.content = content;
        videoBean.time = Utils.getTime();

        int mimeType = 0 ;
        if (null != selectList)
        mimeType = selectList.get(0).getMimeType();
        switch (mimeType) {
            case 1:
                //图片
                bean.type = 2;
                List<String> images = new ArrayList<>();
                for (int i = 0; i < selectList.size(); i++) {
                    images.add(selectList.get(i).getPath());
                }
                videoBean.images = images;
                break;
            case 2:
                //视频
                bean.type = 1;
                videoBean.videoUrl = selectList.get(0).getPath() ;
                videoBean.videoImages = selectList.get(0).getPath() ;

                break;
                default:
                bean.type = 3;
        }

        bean.video = videoBean;
        allList.add(0, bean);

        SPUtils.setNewest(GsonUtil.toJosn(allList));

        ToastUtils.s("发布成功 积分 +1");
        finish();
    }

    //展示选择的图片
    private void refreshImages() {
        if (null == selectList) {
            mAfImages.setVisibility(View.GONE);
            return;
        }
        mAfImages.removeAllViews();
        for (int i = 0; i < selectList.size(); i++) {

            ImageView imageView = getImageView(selectList.get(i).getPath());
            mAfImages.addView(imageView);

        }
        if (selectList.size() < 9) {
            ImageView imageView = getImageView("");
            mAfImages.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PictureSelector.create(PublishActivity.this)
                            .openGallery(PictureMimeType.ofAll())
                            .maxSelectNum(9 - selectList.size())
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

    private ImageView getImageView(String url) {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(image_width, image_width);
        ImageView imageView = new ImageView(xContext);
        imageView.setPadding(5, 5, 2, 2);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.add_pic);
        imageView.setLayoutParams(lp);
        if (!TextUtils.isEmpty(url))
            GlideUtils.lodeImage(url, imageView);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
