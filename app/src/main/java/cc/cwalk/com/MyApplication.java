
package cc.cwalk.com;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.util.DisplayMetrics;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import cc.cwalk.com.utils.DataUtils;
import cc.cwalk.com.utils.LogUtils;

public class MyApplication extends Application {
    private static MyApplication instance;
    public static boolean DEBUG = true;
    private static float scale ;//手机不同分辨率百分比  用在代码设置大小的时候适配不同分辨率

    //文件缓存路径
    public final static String cachePath = Environment.getExternalStorageDirectory()+"/ccwalk";

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Des：下面的代码是解决 Android系统 N 以上 FileUriExposedException
         */
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        if (Build.VERSION.SDK_INT >= 24) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        scale = getResources().getDisplayMetrics().density;
        instance = this;
        DataUtils.init();
    }

    public static float getScale(){
        return scale ;
    }
    public MyApplication() {
        instance = this;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    /**
     * 获取Application
     */
    public static MyApplication getInstance() {
        return instance;
    }


    public static int getScreenWidth(Activity context){
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        return width;
    }

    //===============将所有打开的 activity 添加到集合 推出时关闭所有activity=========
    private List<Activity> mList = new LinkedList<Activity>();

    /**
     * 添加activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (!mList.contains(activity))
        mList.add(activity);
    }

    /**
     * Des：移除activity
     */
    public void removeActivity(Activity activity) {
        if (mList.contains(activity))
        mList.remove(activity);
    }


    /**
     * 退出所有activity
     */
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
