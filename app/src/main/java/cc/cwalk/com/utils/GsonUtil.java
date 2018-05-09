package cc.cwalk.com.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import cc.cwalk.com.beans.DataBean;

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
		Type listType = new TypeToken<List<DataBean>>() {}.getType();
		List<DataBean> data = getGson().fromJson(jsonString, listType);
		Collections.shuffle(data);//模拟数据变化
		for (int i = 0; i < data.size(); i++) {
			List<DataBean.VideosBean> videos = data.get(i).getVideos();
			Collections.shuffle(videos);
		}
		return data ;
	}


}
