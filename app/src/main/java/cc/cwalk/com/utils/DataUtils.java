package cc.cwalk.com.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.recycles.RefreshLoadMoreRecyclerView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author chen_gw
 * Date 2018/4/24 15:01
 * DES :模拟数据获取
 */
public class DataUtils {
    public final static String baseUrl = "http://chengw32.com:8080/videos/";//视频跟缩略图的地址
    public final static String baseheadUrl = "http://chengw32.com:8080/heads/";//头像地址（单独的文件夹）
    private List<String> stringContent = new ArrayList();

    public void init() {
        initString();
    }

    public static DataUtils mInstance;


    private void initString() {
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


    public String getStringText() {
        return stringContent.get(new Random().nextInt(stringContent.size()));
    }


    public static DataUtils getInstance() {
        if (mInstance == null) mInstance = new DataUtils();
        return mInstance;
    }

 public void getDataList(final RefreshLoadMoreRecyclerView mRcView){
     DataUtils.getInstance().getJsonFromService(new StringCallback() {
         @Override
         public void success(String result) {
             //Gson解析数据
             List<DataBean> data = GsonUtil.getData(result);
             List dataContent = mRcView.getDataContent();
             dataContent.addAll(data);
             mRcView.complete();
         }
     });
 }

    public void getJsonFromService(final StringCallback callBack) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://chengw32.com:8080/wtf.txt")
                        .build();

                Response response = client.newCall(request).execute();
                e.onNext(response.body().string());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        callBack.success(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
