package cc.cwalk.com;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cwalk.com.base.BaseActivity;
import cc.cwalk.com.dialog.PickerDialog;
import cc.cwalk.com.tab_four.MeFragment;
import cc.cwalk.com.tab_one.FindFragment;
import cc.cwalk.com.tab_three.CommunityFragment;
import cc.cwalk.com.tab_three.PublishActivity;
import cc.cwalk.com.tab_two.AttentionFragment;
import cc.cwalk.com.utils.EventUtil;

public class MainActivity extends BaseActivity {
    @Bind(R.id.iv_tab_1)
    ImageView mIvTab1;
    @Bind(R.id.tv_tab_1)
    TextView mTvTab1;
    @Bind(R.id.iv_tab_2)
    ImageView mIvTab2;
    @Bind(R.id.tv_tab_2)
    TextView mTvTab2;
    @Bind(R.id.iv_tab_3)
    ImageView mIvTab3;
    @Bind(R.id.tv_tab_3)
    TextView mTvTab3;
    @Bind(R.id.iv_tab_4)
    ImageView mIvTab4;
    @Bind(R.id.tv_tab_4)
    TextView mTvTab4;
    private Fragment findFragment;
    private Fragment attentionFragment;
    private Fragment commFragment;
    private Fragment meFragment;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.add(R.id.home_content, getInstanceByIndex(1)).commitAllowingStateLoss();

        //设置选择颜色
        mIvTab1.setSelected(true);
        mTvTab1.setSelected(true);

        GSYVideoManager instance = GSYVideoManager.instance();
        instance.newProxy(xContext, new File(MyApplication.cachePath));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    EventUtil.sendEvent(EventUtil.IMAGE_VIDEO, selectList);
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    /**
     * Creat By_Chen
     * Time 2018/4/20 22:29
     * Des 切换片段
     */
    public void changeFragment(Fragment currentFragment) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();


        if (null != findFragment && findFragment.isVisible())
            transaction.hide(findFragment);
        if (null != attentionFragment && attentionFragment.isVisible())
            transaction.hide(attentionFragment);
        if (null != commFragment && commFragment.isVisible())
            transaction.hide(commFragment);
        if (null != meFragment && meFragment.isVisible())
            transaction.hide(meFragment);

        if (!currentFragment.isAdded())
            transaction.add(R.id.home_content, currentFragment);
        transaction.show(currentFragment);

        //不保留状态提交事务
        transaction.commitAllowingStateLoss();

    }

    /**
     * Creat By_Chen
     * Time 2018/4/19 22:57
     * Des 根据位置获取片段
     */
    private Fragment getInstanceByIndex(int index) {
        switch (index) {
            case 1://发现
                if (findFragment == null)
                    findFragment = new FindFragment();
                return findFragment;
            case 2://关注
                if (attentionFragment == null)
                    attentionFragment = new AttentionFragment();
                return attentionFragment;
            case 3://交流
                if (commFragment == null)
                    commFragment = new CommunityFragment();
                return commFragment;
            case 4://我
                if (meFragment == null)
                    meFragment = new MeFragment();
                return meFragment;
        }
        return null;
    }


    @Override
    protected void initData() {
    }


    @OnClick({R.id.ll_publish,R.id.ll_tab_1, R.id.ll_tab_2, R.id.ll_tab_3, R.id.ll_tab_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab_1:

                if (mIvTab1.isSelected()) return;
                mIvTab1.setSelected(true);
                mTvTab1.setSelected(true);
                mIvTab2.setSelected(false);
                mTvTab2.setSelected(false);
                mIvTab3.setSelected(false);
                mTvTab3.setSelected(false);
                mIvTab4.setSelected(false);
                mTvTab4.setSelected(false);
                changeFragment(getInstanceByIndex(1));
                break;
            case R.id.ll_tab_2:
                if (mIvTab2.isSelected()) return;
                mIvTab1.setSelected(false);
                mTvTab1.setSelected(false);
                mIvTab2.setSelected(true);
                mTvTab2.setSelected(true);
                mIvTab3.setSelected(false);
                mTvTab3.setSelected(false);
                mIvTab4.setSelected(false);
                mTvTab4.setSelected(false);
                changeFragment(getInstanceByIndex(2));
                break;
            case R.id.ll_tab_3:
                if (mIvTab3.isSelected()) return;
                mIvTab1.setSelected(false);
                mTvTab1.setSelected(false);
                mIvTab2.setSelected(false);
                mTvTab2.setSelected(false);
                mIvTab3.setSelected(true);
                mTvTab3.setSelected(true);
                mIvTab4.setSelected(false);
                mTvTab4.setSelected(false);
                changeFragment(getInstanceByIndex(3));
                break;
            case R.id.ll_tab_4:
                if (mIvTab4.isSelected()) return;
                mIvTab1.setSelected(false);
                mTvTab1.setSelected(false);
                mIvTab2.setSelected(false);
                mTvTab2.setSelected(false);
                mIvTab3.setSelected(false);
                mTvTab3.setSelected(false);
                mIvTab4.setSelected(true);
                mTvTab4.setSelected(true);
                changeFragment(getInstanceByIndex(4));
                break;
            case R.id.ll_publish:
                PickerDialog pickerDialog = new PickerDialog(MainActivity.this, new PickerDialog.PickCallBack() {
                    @Override
                    public void video() {

                        PictureSelector.create(MainActivity.this)
                                .openGallery(PictureMimeType.ofVideo())
                                .maxSelectNum(1)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                    }

                    @Override
                    public void photo() {
                        PictureSelector.create(MainActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);

                    }

                    @Override
                    public void text() {
                        PublishActivity.startActivity(xContext,null);
                    }
                });
                pickerDialog.show();
                break;
        }
    }

    @Override
    public void onMessageEvent(EventUtil.BaseEvent event) {
        if (EventUtil.IMAGE_VIDEO.equals(event.getAction())){
            PublishActivity.startActivity(xContext,event.getData());
        }
    }

}
