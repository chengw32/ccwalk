package cc.cwalk.com.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cc.cwalk.com.MyApplication;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.DetailBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.beans.VideoBean;

/**
 * Author chen_gw
 * Date 2018/4/24 15:01
 * DES :模拟数据获取
 */
public class DataUtils {
    public final static String baseUrl = "http://chengw32.com:8080/videos/";
    public final static String baseheadUrl = "http://chengw32.com:8080/heads/";
    private static List<VideoBean> videoContent = new ArrayList();
    private static List<UserBean> userContent = new ArrayList();
    private static List<DetailBean> detailContent = new ArrayList();
    private static List<String> stringContent = new ArrayList();

    public static void init() {
        initUser();
        initVideo();
        initDetail();
        initString();
    }

    private static void initString() {
        stringContent.add("岁月不老，情怀还在");
        stringContent.add("不仅仅是喜欢。。。。。");
        stringContent.add("D.LHX国内最强高清C WALK教程 by D.LHX：");
        stringContent.add("2018咯，6年啦还是一枚辣鸡，哈哈哈~");
        stringContent.add("支持一波,好多年前的回忆了(´･_･`)");
        stringContent.add("（￣▽￣）可以");
        stringContent.add("有意思哎");
        stringContent.add("棒的 学到了一些 背景也好好看啊哈哈哈");
        stringContent.add("很酷(°∀°)ﾉ");
        stringContent.add("大家不要急，多多练习，想我练了2小时，啥也不会。");
        stringContent.add("我一定要全部学会");
        stringContent.add("啊，不错啊加油啊，虽然我以前自学转笔鬼步跑酷弹琴的时候都是这么说的。但是你一定能比我做得更好吧(｀・ω・´)");
        stringContent.add("也不知道现在是什么风气，各种标题是“超帅曳步舞”的SEVE，还有各种“这不是SEVE”的曳步舞视频还有“这不是鬼步舞”的CWalk视频(°∀°)ﾉ我只想说一句话눈_눈“这也许就是大佬吧！");
        stringContent.add("这个干货啊啊啊啊！");
        stringContent.add("哪位大神有这个封面的视频啊");
        stringContent.add("麻烦问一下 错误的是因为脚跟没抬起来吗 感觉自己做的总是怪怪的诶");
        stringContent.add("身体要向抬起的腿的那一边转，而不是另一只腿的方向转");
        stringContent.add("xhop后脚跟贼难受");
        stringContent.add("有所有bgm吗→_→");
        stringContent.add("aus曳步快四年了，还是受不了跟各种混混非主流一起，较向往cwalk干净又酷的环境。如今学c快半年了，曳舞各种后遗症很影响，但还是比较顺利。对于会曳步舞又想学c的童鞋我只想说：不要认为有底子急于求成，该抬腿抬腿，该勾脚勾脚");
        stringContent.add("才跟着练了一会，脚腕好累");
        stringContent.add("妈妈，我要学这个");
        stringContent.add("后脚跟为支点的时候抬不高，站不稳");
        stringContent.add("还有应该要直线移动吧，移着移着就歪了，脚也并不拢了（￣へ￣）初学者求指教");
        stringContent.add("脚抬不太起来，特别是用脚后跟做支点的那只脚");
        stringContent.add("不要在意是什么舞，因为我也很纠结阿");
        stringContent.add("我奇迹般的发现10年前我居然学过cwalk，因为觉得简单只有8个动作而且可以随便跳。v步，滑步，蛇步，摇，双摇~~在大学还嘚瑟了一阵子~~我说seve这么熟悉~");
    }

    private static void initDetail() {
        detailContent.add(new DetailBean(1, 3, 4, 432, "2018-4-27"));
        detailContent.add(new DetailBean(0, 54, 65, 4662, "2018-4-26"));
        detailContent.add(new DetailBean(0, 36, 3, 32, "2018-4-25"));
        detailContent.add(new DetailBean(1, 36, 53, 712, "2018-4-24"));
        detailContent.add(new DetailBean(1, 36, 6, 284, "2018-4-23"));
        detailContent.add(new DetailBean(1, 2, 45, 258, "2018-4-22"));
        detailContent.add(new DetailBean(0, 35, 25, 458, "2018-4-21"));
        detailContent.add(new DetailBean(1, 25, 85, 2584, "2018-4-20"));
        detailContent.add(new DetailBean(0, 653, 25, 458, "2018-4-19"));
        detailContent.add(new DetailBean(1, 7, 5, 458, "2018-4-18"));
        detailContent.add(new DetailBean(0, 375, 85, 458, "2018-4-17"));
        detailContent.add(new DetailBean(0, 3, 45, 258, "2018-4-16"));
        detailContent.add(new DetailBean(0, 13, 82, 585, "2018-4-15"));
        detailContent.add(new DetailBean(0, 75, 52, 237, "2018-4-14"));
        detailContent.add(new DetailBean(1, 357, 358, 5538, "2018-10"));
        detailContent.add(new DetailBean(1, 58, 848, 38455, "2018-4-9"));
        detailContent.add(new DetailBean(0, 245, 32, 535, "2018-4-8"));
        detailContent.add(new DetailBean(0, 584, 24, 1585, "2018-4-7"));
        detailContent.add(new DetailBean(1, 245, 859, 452, "2018-4-6"));
        detailContent.add(new DetailBean(1, 85, 852, 554, "2018-4-5"));
    }

    public static void initVideo() {
        videoContent.add(new VideoBean("sample.mp4", "sample.png", "0", "cwalk 各种快"));
        videoContent.add(new VideoBean("sample1.mp4", "sample1.png", "1", "i love the rain"));
        videoContent.add(new VideoBean("sample2.flv", "sample2.png", "2", "cwalk"));
        videoContent.add(new VideoBean("slkdjgejgseslsgej.flv", "slkdjgejgseslsgej.png", "3", "C-walk"));
        videoContent.add(new VideoBean("062E0B53EA8BBB94F6B0CEFD87D8286B.flv", "062E0B53EA8BBB94F6B0CEFD87D8286B.png", "4", "Cwalk Abelard-Hey stranger_高清"));
        videoContent.add(new VideoBean("8AF705836C6694848E227F3D7F5D4ABD.mp4", "8AF705836C6694848E227F3D7F5D4ABD.png", "5", "大神级舞步"));
        videoContent.add(new VideoBean("D7FEC457DAFFEE65EE67A42982E02921.mp4", "D7FEC457DAFFEE65EE67A42982E02921.png", "6", "RARE CWALK_高清"));
        videoContent.add(new VideoBean("F22630B947F92D63E79A84DA1DE398B9.mp4", "F22630B947F92D63E79A84DA1DE398B9.png", "7", "CWalk - PuGGy Mixtape - The Beast_高清"));
        videoContent.add(new VideoBean("9B9A4A54576B0FF0B8A36A4E5DE66382.mp4", "9B9A4A54576B0FF0B8A36A4E5DE66382.png", "9", "【曳舞天下】Cwalk Tutorial [Beginners] II[高清版]"));
        videoContent.add(new VideoBean("2244CD0594A81165D7186C1987C50D9E.flv", "2244CD0594A81165D7186C1987C50D9E.png", "10", "【骑士】 2014 这才是真正的C-walk 超清[超清版]"));
        videoContent.add(new VideoBean("FE73411CFCC4935215173B4B071B75A8.mp4", "FE73411CFCC4935215173B4B071B75A8.png", "11", "TFC C-Walk Choreo _ Routine _ RS PROD_超清"));
        videoContent.add(new VideoBean("644BB8748DF89F20BB4BDE08FBB139C1.mp4", "644BB8748DF89F20BB4BDE08FBB139C1.png", "12", "C-Walk Choreo _ Студия DANCEHALL_超清"));
        videoContent.add(new VideoBean("F44136D83A2AFB70BB13AAA32535825B.mp4", "F44136D83A2AFB70BB13AAA32535825B.png", "13", "V.A.LOKOS MIXTAPE_高清"));
        videoContent.add(new VideoBean("01AC2E0111D3A6BA0D2B1F9904D6EA3E.mp4", "01AC2E0111D3A6BA0D2B1F9904D6EA3E.png", "14", "【珍藏】V.A. Lokos - Cwalk - Before You Go_超清"));
    }

    public static void initUser() {
        userContent.add(new UserBean("七炫", "厦门", "2018-4-28", "2018-4-28", "0aca472def0b8c0ccc9350674539f6a7.jpg", 0));
        userContent.add(new UserBean("熊小莫", "广州", "2018-4-28", "2018-4-28", "03514dbb47703398b8a96b1a9ab013c6.jpg", 0));
        userContent.add(new UserBean("GY癸酉", "福州", "2018-4-28", "2018-4-26", "05bc3aa3423cae4d0a2baec9535fe464.jpeg", 1));
        userContent.add(new UserBean("Y欧瑞", "福州", "2018-4-27", "2018-4-26", "2a25512470e56727305b5dd5aeb49bdb.png", 1));
        userContent.add(new UserBean("极光·繁星", "厦门", "2018-4-27", "2018-4-26", "2d4a88f1f2814263a0891a3899bb3d8f.jpg", 0));
        userContent.add(new UserBean("凳子o", "厦门", "2018-4-27", "2018-4-23", "6a270402690fd97046a0944740a265c5.jpg", 0));
        userContent.add(new UserBean("CW小嚄", "上海", "2018-4-26", "2018-4-22", "8bde2581fcc001c2ca90ef56293226ac.jpg", 0));
        userContent.add(new UserBean("gwhh", "上海", "2018-4-26", "2018-4-18", "8ecaf4ccd0d9bde63be9ca977235b6fc.jpg", 0));
        userContent.add(new UserBean("用户pou", "北京", "2018-4-26", "2018-3-28", "9f1ee91e8c65b00ca11ab66fc2cf453b.jpg", 0));
        userContent.add(new UserBean("用户eeeee", "北京", "2018-4-26", "2018-3-26", "dd45308282eb82d1451ea28d9ca4339a.jpg", 0));
        userContent.add(new UserBean("撩个屁", "北京", "2018-4-25", "2018-3-28", "366dfdf17c2631aae519e03e857c11b5.jpg", 1));
        userContent.add(new UserBean("手机用户546", "北京", "2018-4-25", "2018-3-25", "686b0b3ebaf9064a970842ba8fb4bc9e.jpg", 1));
        userContent.add(new UserBean("手机用户784", "北京", "2018-4-25", "2018-3-23", "5907c5c3b5edcd5d9413a4e6ae7a4aa2.jpg", 1));
        userContent.add(new UserBean("手机用户268", "厦门", "2018-4-25", "2018-3-23", "b2df9f6b960ccc5aac81437f59ae36a1.jpg", 0));
        userContent.add(new UserBean("奇偶ie好", "厦门", "2018-4-24", "2018-3-23", "b39368ea665ab78cfc86af15a5115da7.jpeg", 1));
        userContent.add(new UserBean("王卫国", "厦门", "2018-4-24", "2018-3-23", "bf9a2fbc87ba4d5e4ae3a087bac1592e.png", 1));
        userContent.add(new UserBean("溪水不与泉流", "厦门", "2018-4-24", "2018-3-22", "d8d9fcecb6441e8e3b5f9f1276243cef.jpg", 1));
    }

    public static String getStringText(){
        return stringContent.get(getRandom(stringContent.size()));
    }
    //
//    public static String initUser(){
//        try {
//            InputStream is = MyApplication.getContext().getResources().getAssets().open("user.txt");
//            int size = is.available();
//            // Read the entire asset into a local byte buffer.
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            // Convert the buffer into a string.
//            return new String(buffer, "utf-8");
//            // Finally stick the string into the text view.
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "文件读取错误";
//    }
//
//public static List<UserBean> getUserList(){
//       return getList(initUser(),UserBean.class);
//}
    public static <T> T getData(String jsonString, Class<T> clazz) {
        return getGson().fromJson(jsonString, clazz);
    }

    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            return new Gson();
        } else {
            return gson;
        }
    }

    public static <T> List<T> getList(String json, Class<T> clazz) {
        List<T> lst = new ArrayList<T>();
        try {
            JsonElement data = new JsonParser().parse(json);
            if (data.isJsonArray()) {
                JsonArray array = data.getAsJsonArray();
                for (final JsonElement elem : array) {
                    lst.add(new Gson().fromJson(elem, clazz));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }


    private static List<DataBean> dataList = new ArrayList();

    public static List<DataBean> getDataList() {
        dataList.clear();
        for (int i = 0; i < 10; i++) {
            DataBean e = new DataBean();
            e.userBean = getUserData();
            e.detailBeans = getDetailList();
            dataList.add(e);
        }
        return dataList;
    }

    public static UserBean getUserData() {
        while (true) {
            UserBean e = userContent.get(getRandom(userContent.size()));
            if (dataList.contains(e)) continue;
            return e;
        }
    }
    public static UserBean getSingleUserData() {
           return   userContent.get(getRandom(userContent.size()));
    }

    public static List<DetailBean> getDetailList() {
        List<DetailBean> list = new ArrayList<>();
        while (true) {
            DetailBean e = detailContent.get(getRandom(detailContent.size()));
            if (list.contains(e)) continue;
            e.videoBeans = getVideoInfo();
            list.add(e);
            if (list.size() == detailContent.size()) break;
        }
        return list;
    }

    public static List<VideoBean> getVideoInfo() {
        List<VideoBean> list = new ArrayList<>();
        while (true){
            VideoBean e = videoContent.get(getRandom(videoContent.size()));
            if (list.contains(e))continue;
            list.add(e);
            if (list.size() == videoContent.size())return list;
        }
    }

    public static Random mRandom;

    public static int getRandom(int seed) {
        if (null == mRandom) mRandom = new Random();
        return mRandom.nextInt(seed);
    }


}
