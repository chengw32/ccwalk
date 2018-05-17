package cc.cwalk.com.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import cc.cwalk.com.MyApplication;

/**
 * Created by Chen on 2018/5/17.
 * Des :${input}
 */

public class Utils {
    public static void hideSoft(View view){
        InputMethodManager imm = (InputMethodManager) MyApplication.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }
}
