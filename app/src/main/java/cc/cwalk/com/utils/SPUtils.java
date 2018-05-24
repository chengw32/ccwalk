package cc.cwalk.com.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cc.cwalk.com.MyApplication;


/**
 * Creat By_Chen
 * Time 2018/4/21 14:23
 * Des SharedPreferences 工具类
 * */
public class SPUtils {
	 /** 
     * 保存在手机里面的文件名 
     */  
    private static final String FILE_NAME = "sp_data";  
    private static final String IS_LOGIN = "islogin";//登录状态
    private static final String IS_INIT = "isinit";//数据初始化
    private static final String ID = "id";//id
    private static final String userlist = "userlist";//
    private static final String newestlist = "newestlist";//
    private static final String fanslist = "fanslist";//
    private static final String attentionlist = "attentionlist";//
    private static final String noticelist = "noticelist";//
    private static SharedPreferences	sp;

    /**
     * 获取SharedPreferences实例
     * @return
     */
	private static SharedPreferences getPreferences(){
		if (sp == null){
			sp = MyApplication.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		}
		return sp;
	}


    public static SharedPreferences.Editor getEditor(){
          
        SharedPreferences sp = getPreferences();
        SharedPreferences.Editor editor = sp.edit();  
          return editor;

    }  
      

    public static boolean isLogin(){
        SharedPreferences sp = getPreferences();
       return sp.getBoolean(IS_LOGIN,false);
    }
    public static void setIsLogin(boolean isLogin) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(IS_LOGIN,isLogin);
        editor.commit();
    }

    //----------------------------------是否初始化过数据---------------------------------
    public static boolean isInit(){
        SharedPreferences sp = getPreferences();
       return sp.getBoolean(IS_INIT,false);
    }
    public static void setIsInit(boolean isLogin) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(IS_INIT,isLogin);
        editor.commit();
    }

    //判断登录状态 有提示的方法
    public static boolean isLoginWithToast(){
        SharedPreferences sp = getPreferences();
        boolean aBoolean = sp.getBoolean(IS_LOGIN, false);
        if (!aBoolean) ToastUtils.s("请登录");
       return aBoolean ;
    }


    public static int getId() {
        SharedPreferences sp = getPreferences();
        return sp.getInt(ID,0);
    }
    public static void setId(int id) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(ID,id);
        editor.commit();
    }


    public static void setUser(String user) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(userlist,user);
        editor.commit();
    }
    public static String getUser() {
        SharedPreferences sp = getPreferences();
        return sp.getString(userlist,"");
    }

    public static void setNewest(String newest) {
        LogUtils.e(newest);
        SharedPreferences.Editor editor = getEditor();
        editor.putString(newestlist,newest);
        editor.commit();
    }

    public static String getNewest() {
        SharedPreferences sp = getPreferences();
        String string = sp.getString(newestlist, "");
        return string;
    }
    public static String getFansList() {
        SharedPreferences sp = getPreferences();
        return sp.getString(fanslist,"");
    }
    public static void setFans(String fans) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(fanslist,fans);
        editor.commit();
    }
    public static String getAttentionList() {
        SharedPreferences sp = getPreferences();
        return sp.getString(attentionlist,"");
    }
    public static void setAttentionList(String fans) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(attentionlist,fans);
        editor.commit();
    }
    public static String getNoticeList() {
        SharedPreferences sp = getPreferences();
        return sp.getString(noticelist,"");
    }
    public static void setNoticeList(String fans) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(noticelist,fans);
        editor.commit();
    }


}
