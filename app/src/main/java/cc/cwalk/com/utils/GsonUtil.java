package cc.cwalk.com.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cc.cwalk.com.beans.AttentionBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.FansBean;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.UserBean;

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

	public static List getData(String jsonString) {
		Type listType = new TypeToken<List<DataBean>>() {}.getType();
		List<DataBean> data = getGson().fromJson(jsonString, listType);
		Collections.shuffle(data);//模拟数据变化
		for (int i = 0; i < data.size(); i++) {
			List<DataBean.VideosBean> videos = data.get(i).getVideos();
			Collections.shuffle(videos);
            List<DataBean.StrBean> str = data.get(i).getStr();
			Collections.shuffle(str);
        }
		return data ;
	}
	public static List getGroupData(String jsonString) {
		Type listType = new TypeToken<List<GroupInfoBean>>() {}.getType();
		List<GroupInfoBean> data = getGson().fromJson(jsonString, listType);
//		Collections.shuffle(data);//模拟数据变化
		return data ;
	}


    public static List getUserList(String jsonString) {
		Type listType = new TypeToken<List<UserBean>>() {}.getType();
		List<UserBean> data = getGson().fromJson(jsonString, listType);
		return data ;
    }
    public static  List getNewestList(String jsonString) {
		Type listType = new TypeToken<List<AllDataBean>>() {}.getType();
		List<AllDataBean> data = getGson().fromJson(jsonString, listType);
		List<AllDataBean> backList = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			if (1 == data.get(i).type){backList.add(data.get(i));}
		}
		return backList ;
    }
    public static  List getAllList(String jsonString) {
		Type listType = new TypeToken<List<AllDataBean>>() {}.getType();
		List<AllDataBean> data = getGson().fromJson(jsonString, listType);
		return data ;
    }
    public static  List getFansList(String jsonString) {
		Type listType = new TypeToken<List<FansBean>>() {}.getType();
		List<FansBean> data = getGson().fromJson(jsonString, listType);
		return data ;
    }
    public static  List getAttentionList(String jsonString) {
		Type listType = new TypeToken<List<AttentionBean>>() {}.getType();
		List<AttentionBean> data = getGson().fromJson(jsonString, listType);
		return data ;
    }
}
