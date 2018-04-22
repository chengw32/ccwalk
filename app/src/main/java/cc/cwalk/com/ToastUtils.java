package cc.cwalk.com;

import android.widget.Toast;

/**
 * Created by Chen on 2018/4/12.
 */

public class ToastUtils {

    private static  Toast mToast ;

    public static void s(Object msg){
        if (null == msg)return;
        if (null == mToast){
            mToast = Toast.makeText(MyApplication.getContext(),msg.toString(),Toast.LENGTH_SHORT);
        }else mToast.setText(msg.toString());
        mToast.show();
    }

}
