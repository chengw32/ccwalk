package cc.cwalk.com.utils;

import android.util.Log;

import cc.cwalk.com.MyApplication;

public class LogUtils {

	public static void e(Object str) {
		if (MyApplication.DEBUG && null != str) {
			Log.e("cc.cwalk.com", "-------------------------------------------------------------------------------------------------------------------------"
					+ "\n"
					+ "        " + str.toString()
					+ " \n"
					+ "------------------------------------------------------------------------------------------------------------\n" + "  ");
		}
	}

}
