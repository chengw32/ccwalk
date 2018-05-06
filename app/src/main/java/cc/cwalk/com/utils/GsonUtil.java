package cc.cwalk.com.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.xxxBean;

public class GsonUtil {
	private static Gson gson;

	public static Gson getGson() {
		if (gson == null) {
			return new Gson();
		} else {
			return gson;
		}
	}

	public static String toJosn(List list){
		return getGson().toJson(list);
	}

	/**
	 * 将字符转化为对象
	 *
	 * @author:qiuchen
	 * @createTime:2012-9-24
	 * @param <T>
	 * @param jsonString
	 * @return
	 */
	public static List getData(String jsonString) {
		Type listType = new TypeToken<List<xxxBean>>() {}.getType();
		return getGson().fromJson(jsonString, listType);
	}


}
