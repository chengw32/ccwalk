package cc.cwalk.com.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cc.cwalk.com.beans.DetailBean;
import cc.cwalk.com.beans.UserBean;
import cc.cwalk.com.beans.VideoBean;

/**
 * Author chen_gw
 * Date 2018/4/24 15:01
 * DES :模拟数据获取
 */
public class DataUtils {
    public final static String baseUrl = "http://chengw32.com:8080/videos/" ;
    private static List<VideoBean> videoContent = new ArrayList();
    private static List<UserBean> userContent = new ArrayList();
    private static List<DetailBean> detailContent = new ArrayList();
    private static List<String> stringContent = new ArrayList();
    public static void init(){
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
        detailContent.add(new DetailBean(1,3,4,432,"2018-4-27"));
        detailContent.add(new DetailBean(0,54,65,4662,"2018-4-26"));
        detailContent.add(new DetailBean(0,36,3,32,"2018-4-25"));
        detailContent.add(new DetailBean(1,36,53,712,"2018-4-24"));
        detailContent.add(new DetailBean(1,36,6,284,"2018-4-23"));
        detailContent.add(new DetailBean(1,2,45,258,"2018-4-22"));
        detailContent.add(new DetailBean(0,35,25,458,"2018-4-21"));
        detailContent.add(new DetailBean(1,25,85,2584,"2018-4-20"));
        detailContent.add(new DetailBean(0,653,25,458,"2018-4-19"));
        detailContent.add(new DetailBean(1,7,5,458,"2018-4-18"));
        detailContent.add(new DetailBean(0,375,85,458,"2018-4-17"));
        detailContent.add(new DetailBean(0,3,45,258,"2018-4-16"));
        detailContent.add(new DetailBean(0,13,82,585,"2018-4-15"));
        detailContent.add(new DetailBean(0,75,52,237,"2018-4-14"));
        detailContent.add(new DetailBean(1,357,358,5538,"2018-10"));
        detailContent.add(new DetailBean(1,58,848,38455,"2018-4-9"));
        detailContent.add(new DetailBean(0,245,32,535,"2018-4-8"));
        detailContent.add(new DetailBean(0,584,24,1585,"2018-4-7"));
        detailContent.add(new DetailBean(1,245,859,452,"2018-4-6"));
        detailContent.add(new DetailBean(1,85,852,554,"2018-4-5"));
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
        userContent.add(new UserBean("溪水不与泉流","厦门",1));
    }


    /**
     * Creat By_Chen
     * Time 2018/4/25 22:17
     * Des 获取视频信息
     * */
    public static VideoBean getVideoInfo(int position){

        return videoContent.get(position % videoContent.size());
    }


    /**
     * Creat By_Chen
     * Time 2018/4/25 22:17
     * Des 获取用户信息
     * */
    public static UserBean getUserInfo(int position){

        return userContent.get(position % userContent.size());
    }

    //获取帖子数据信息
    public static DetailBean getDetail(int position){
        return detailContent.get(position % detailContent.size());
    }
    //获取文本信息
    public static String getString(int position){
        return stringContent.get(position % stringContent.size());
    }

}
