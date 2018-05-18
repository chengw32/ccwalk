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
    private static final String PHONE = "phone";//手机号
    private static final String NAME = "name";//昵称
    private static final String QQ = "QQ";
    private static final String SEX = "sex";//1 男 其他 女性
    private static final String CREAT = "1";//
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
    //判断登录状态 有提示的方法
    public static boolean isLoginWithToast(){
        SharedPreferences sp = getPreferences();
        boolean aBoolean = sp.getBoolean(IS_LOGIN, false);
        if (!aBoolean) ToastUtils.s("请登录");
       return aBoolean ;
    }

    public static void setUserName(String name) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(NAME,name);
        editor.commit();
    }
    public static void setQQName(String name) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(QQ,name);
        editor.commit();
    }
    public static String getUserName() {
        SharedPreferences sp = getPreferences();
        return sp.getString(NAME,"");
    }

    public static void setIsLogin(boolean isLogin) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(IS_LOGIN,isLogin);
        editor.commit();
    }

    public static void setPhone(String phone) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(PHONE,phone);
        editor.commit();
    }
    public static String getPhone() {
        SharedPreferences sp = getPreferences();
        return sp.getString(PHONE,"");
    }
    public static void setSex(int sex) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(SEX,sex);
        editor.commit();
    }

    public static int getSex() {
        SharedPreferences sp = getPreferences();
        return sp.getInt(SEX,0);
    }

    public static void setCreat(int creat) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(CREAT,creat);
        editor.commit();
    }
    public static int getCreat() {
        SharedPreferences sp = getPreferences();
        return sp.getInt(CREAT,0);
    }
}
