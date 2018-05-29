package cc.cwalk.com.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cc.cwalk.com.beans.ActivityBean;
import cc.cwalk.com.beans.AttentionBean;
import cc.cwalk.com.beans.CreditsBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.FansBean;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.NoticeBean;
import cc.cwalk.com.beans.PurchaseBean;
import cc.cwalk.com.beans.TeachingBean;
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
//		Collections.shuffle(data);//模拟数据变化
//		for (int i = 0; i < data.size(); i++) {
//			List<DataBean.VideosBean> videos = data.get(i).getVideos();
//			Collections.shuffle(videos);
//            List<DataBean.StrBean> str = data.get(i).getStr();
//			Collections.shuffle(str);
//        }
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
    public static  List getNoticeList(String jsonString) {
		Type listType = new TypeToken<List<NoticeBean>>() {}.getType();
		List<NoticeBean> data = getGson().fromJson(jsonString, listType);
		return data ;
    }
    public static  List getPurchaseList(String jsonString) {
		Type listType = new TypeToken<List<PurchaseBean>>() {}.getType();
		List<PurchaseBean> data = getGson().fromJson(jsonString, listType);
		return data ;
    }
    public static  List getActivityList(String jsonString) {
		Type listType = new TypeToken<List<ActivityBean>>() {}.getType();
		List<ActivityBean> data = getGson().fromJson(jsonString, listType);
		return data ;
    }

	public static List getHotList(String jsonString) {
		Type listType = new TypeToken<List<AllDataBean>>() {}.getType();
		List<AllDataBean> data = getGson().fromJson(jsonString, listType);
		return data ;
	}

	public static List getTeachingList(String jsonString) {
		Type listType = new TypeToken<List<TeachingBean>>() {}.getType();
		List<TeachingBean> data = getGson().fromJson(jsonString, listType);
		return data ;
	}

	public static List getGroupMemberList(String user) {
		Type listType = new TypeToken<List<UserBean>>() {}.getType();
		List<UserBean> data = getGson().fromJson(user, listType);
		return data ;
	}

	public static List getJoinList(String joinList) {
		Type listType = new TypeToken<List<UserBean>>() {}.getType();
		List<UserBean> data = getGson().fromJson(joinList, listType);
		return data ;
	}

	public static List getGroupPayList(String groupPayList) {
		Type listType = new TypeToken<List<ActivityBean>>() {}.getType();
		List<ActivityBean> data = getGson().fromJson(groupPayList, listType);
		return data ;
	}

    public static List getCreditsList(String creditsList) {
		Type listType = new TypeToken<List<CreditsBean>>() {}.getType();
		List<CreditsBean> data = getGson().fromJson(creditsList, listType);
		return data ;
    }

    public static List<AllDataBean> getCommunityList(String communityList) {
		Type listType = new TypeToken<List<AllDataBean>>() {}.getType();
		List<AllDataBean> data = getGson().fromJson(communityList, listType);
		return data ;
    }
}
