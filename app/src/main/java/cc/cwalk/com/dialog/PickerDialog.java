package cc.cwalk.com.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import cc.cwalk.com.MyApplication;
import cc.cwalk.com.R;

/**
 *图片选择对话框
 */
public class PickerDialog extends Dialog implements View.OnClickListener {
    private Activity activity;
    private PickCallBack mPickCallBack ;



    public PickerDialog(Activity activity,PickCallBack callBack) {
        super(activity, R.style.list_dialog);
        this.activity = activity;
        this.mPickCallBack = callBack ;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_picker_dialog_layout);
        initView();
    }




    public void initView() {
        //点击空白消失

        Window mWindow = getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        // 透明度的范围为：0.0f-1.0f;0.0f表示完全透明,1.0f表示完全不透明(系统默认的就是这个)。
        //lp.alpha = 0.35f;

        lp.width = (int) (MyApplication.getScreenWidth(activity)); //设置宽度
        mWindow.setAttributes(lp);
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setWindowAnimations(R.style.slide_in_bottom_dialog_animation);  //添加动画


        findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        findViewById(R.id.tv_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickCallBack.photo();
                dismiss();
            }
        });
        findViewById(R.id.tv_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickCallBack.camera();
                dismiss();
            }
        });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
    public interface PickCallBack {
         void camera();

         void photo();

    }

}
