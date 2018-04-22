/*
 * Copyright 2011 meiyitian
 * Blog  :http://www.cnblogs.com/meiyitian
 * Email :haoqqemail@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *MyApplication.java
 *2011-10-25 ����02:34:14
 */
package cc.cwalk.com;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.util.DisplayMetrics;

import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {
    private static MyApplication instance;
    public static boolean DEBUG = true;
    private static float scale ;//手机不同分辨率百分比  用在代码设置大小的时候适配不同分辨率

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
