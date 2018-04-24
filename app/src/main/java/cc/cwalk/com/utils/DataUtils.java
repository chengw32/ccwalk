package cc.cwalk.com.utils;

import java.util.ArrayList;
import java.util.List;

import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.beans.VideoBean;

/**
 * Author chen_gw
 * Date 2018/4/24 15:01
 * DES :
 */
public class DataUtils {
    public final static String baseUrl = "http://chengw32.com:8080/videos/" ;
    private static List<VideoBean> videoContent = new ArrayList();
    private static List<UserBean> userContent = new ArrayList();
    public static void init(){
        initUser();
        initVideo();
    }
    public static void initVideo(){
        videoContent.add(new VideoBean("sample.mp4","sample.png","0","cwalk 各种快"));
        videoContent.add(new VideoBean("sample1.mp4","sample1.png","1","i love the rain"));
        videoContent.add(new VideoBean("sample2.mp4","sample2.png","2","cwalk"));
        videoContent.add(new VideoBean("slkdjgejgseslsgej.flv","slkdjgejgseslsgej.png","3","C-walk"));
        videoContent.add(new VideoBean("062E0B53EA8BBB94F6B0CEFD87D8286B.flv","062E0B53EA8BBB94F6B0CEFD87D8286B.png","4","Cwalk Abelard-Hey stranger_高清"));
        videoContent.add(new VideoBean("8AF705836C6694848E227F3D7F5D4ABD.mp4","8AF705836C6694848E227F3D7F5D4ABD.png","5","大神级舞步"));
        videoContent.add(new VideoBean("D7FEC457DAFFEE65EE67A42982E02921.mp4","D7FEC457DAFFEE65EE67A42982E02921.png","6","RARE CWALK_高清"));
        videoContent.add(new VideoBean("F22630B947F92D63E79A84DA1DE398B9.mp4","F22630B947F92D63E79A84DA1DE398B9.png","7","CWalk - PuGGy Mixtape - The Beast_高清"));
        videoContent.add(new VideoBean("FA38EB4390814549A7D7F0FA9B5320DB.mp4","FA38EB4390814549A7D7F0FA9B5320DB.png","8","Cwalk  I Just wannna LOVE标清.mp4"));
        videoContent.add(new VideoBean("9B9A4A54576B0FF0B8A36A4E5DE66382.mp4","9B9A4A54576B0FF0B8A36A4E5DE66382.png","9","【曳舞天下】Cwalk Tutorial [Beginners] II[高清版]"));
        videoContent.add(new VideoBean("2244CD0594A81165D7186C1987C50D9E.flv","2244CD0594A81165D7186C1987C50D9E.png","10","【骑士】 2014 这才是真正的C-walk 超清[超清版]"));
        videoContent.add(new VideoBean("FE73411CFCC4935215173B4B071B75A8.mp4","FE73411CFCC4935215173B4B071B75A8.png","11","TFC C-Walk Choreo _ Routine _ RS PROD_超清"));
        videoContent.add(new VideoBean("644BB8748DF89F20BB4BDE08FBB139C1.mp4","644BB8748DF89F20BB4BDE08FBB139C1.png","12","C-Walk Choreo _ Студия DANCEHALL_超清"));
        videoContent.add(new VideoBean("F44136D83A2AFB70BB13AAA32535825B.mp4","F44136D83A2AFB70BB13AAA32535825B.png","13","V.A.LOKOS MIXTAPE_高清"));
        videoContent.add(new VideoBean("01AC2E0111D3A6BA0D2B1F9904D6EA3E.mp4","01AC2E0111D3A6BA0D2B1F9904D6EA3E.png","14","【珍藏】V.A. Lokos - Cwalk - Before You Go_超清"));
    }

    public static void initUser(){
        userContent.add(new UserBean("七炫","厦门",0));
        userContent.add(new UserBean("熊小莫","广州",0));
        userContent.add(new UserBean("GY癸酉","福州",1));
        userContent.add(new UserBean("Y欧瑞","福州",1));
        userContent.add(new UserBean("极光·繁星","厦门",0));
        userContent.add(new UserBean("凳子o","厦门",0));
        userContent.add(new UserBean("CW小嚄","上海",0));
        userContent.add(new UserBean("gwhh","上海",0));
        userContent.add(new UserBean("用户pou","北京",0));
        userContent.add(new UserBean("用户eeeee","北京",0));
        userContent.add(new UserBean("撩个屁","北京",1));
        userContent.add(new UserBean("手机用户546","北京",1));
        userContent.add(new UserBean("手机用户784","北京",1));
        userContent.add(new UserBean("手机用户268","厦门",0));
        userContent.add(new UserBean("奇偶ie好","厦门",1));
        userContent.add(new UserBean("王卫国","厦门",1));
    }

    public static VideoBean getVideoInfo(int position){

        return videoContent.get(position % videoContent.size());
    }
    public static UserBean getUserInfo(int position){

        return userContent.get(position % userContent.size());
    }

}
