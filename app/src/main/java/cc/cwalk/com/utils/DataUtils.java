package cc.cwalk.com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import cc.cwalk.com.MyApplication;
import cc.cwalk.com.beans.AttentionBean;
import cc.cwalk.com.beans.DataBean;
import cc.cwalk.com.beans.FansBean;
import cc.cwalk.com.beans.GroupInfoBean;
import cc.cwalk.com.beans.AllDataBean;
import cc.cwalk.com.beans.UserBean;
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
//    public final static String baseUrl = "http://chengw32.com:8080/videos/";//视频跟缩略图的地址
//    public final static String baseheadUrl = "http://chengw32.com:8080/heads/";//头像地址（单独的文件夹）

    private static DataUtils mInstance;

    public static DataUtils getInstance() {
        if (mInstance == null) mInstance = new DataUtils();
        return mInstance;
    }


   public void initData(){
        initUser("user.txt");
        initNewest("newest.txt");
        initFans("fans.txt");
        initAttention("attention.txt");
        initNotice("notice.txt");
        initPurchase("purchase.txt");
        initActivity("activity.txt");
        initHot("hot.txt");
        initTeaching("teaching.txt");
        initGroupMember("groupmember.txt");
        initJoin("joinlist.txt");
        initGroupPaylist("payactivity.txt");
        initCreditslist("credits.txt");

        SPUtils.setIsInit(true);
   }

    private void initCreditslist(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setCreditslist(assetsFile);
    }

    private void initGroupPaylist(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setGroupPaylist(assetsFile);
    }

    private void initJoin(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setJoinlist(assetsFile);
    }

    private void initGroupMember(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setGroupmemberglist(assetsFile);
    }

    private void initTeaching(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setTeachinglist(assetsFile);
    }

    private void initHot(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setHotList(assetsFile);
    }

    private void initActivity(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setActivityList(assetsFile);
    }

    private void initPurchase(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setPurchaseList(assetsFile);
    }

    private void initNotice(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setNoticeList(assetsFile);
    }

    private void initAttention(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setAttentionList(assetsFile);
    }

    private void initFans(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setFans(assetsFile);
    }

    private void initNewest(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setNewest(assetsFile);
    }

    private void initUser(String s) {
        String assetsFile = getAssetsFile(s);
        SPUtils.setUser(assetsFile);
    }

    public int getRandow(int seed) {
        return new Random().nextInt(seed);
    }

    public void getDataList(final RefreshLoadMoreRecyclerView mRcView) {
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

    public void getGroupList(final RefreshLoadMoreRecyclerView mRcView) {
        DataUtils.getInstance().getJsonGroup(new StringCallback() {
            @Override
            public void success(String result) {
                //Gson解析数据
                List<GroupInfoBean> data = GsonUtil.getGroupData(result);
                List dataContent = mRcView.getDataContent();
                dataContent.addAll(data);
                mRcView.complete();
            }
        });
    }

    public void getJsonGroup(final StringCallback callBack) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://chengw32.com:8080/groupmember.txt")
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


    private String getAssetsFile(String fileName) {
        try {
            InputStream is = MyApplication.getContext().getAssets().open(fileName);
            int lenght = is.available();
            byte[] buffer = new byte[lenght];
            is.read(buffer);
            return new String(buffer, "utf8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "" ;
    }

    //--------------------------用户数据-----------------------------------

    public  List getUserList() {
        return GsonUtil.getUserList(SPUtils.getUser());
    }
    public  List getGroupMemberList() {
        return GsonUtil.getGroupMemberList(SPUtils.getGroupmemberList());
    }
    public  List getJoinList() {
        return GsonUtil.getJoinList(SPUtils.getJoinList());
    }
    public  UserBean getUserById(int id) {
        List<UserBean> userList =getUserList();
        for (int i = 0; i <userList.size() ; i++) {
            if (id == userList.get(i).id)return userList.get(i);
        }
        return new UserBean() ;
    }

    public int getCreater(){
        return DataUtils.getInstance().getUserById(SPUtils.getId()).creater;
    }


    //--------------------------最新-----------------------------------

    public List<AllDataBean> getNewestList(RefreshLoadMoreRecyclerView mRcView) {
        List<AllDataBean> list = GsonUtil.getNewestList(SPUtils.getNewest());
        List dataContent = mRcView.getDataContent();
        dataContent.addAll(list);
        mRcView.complete();
        return list;
    }
    //--------------------------获取所有数据-----------------------------------

    public List<AllDataBean> getAllList() {
        List<AllDataBean> list = GsonUtil.getAllList(SPUtils.getNewest());
        return list;
    }
    public List<AllDataBean> getAllList(RefreshLoadMoreRecyclerView mRcView) {
        List<AllDataBean> list = GsonUtil.getAllList(SPUtils.getNewest());
        List dataContent = mRcView.getDataContent();
        dataContent.addAll(list);
        mRcView.complete();
        return list;
    }


    //--------------------------获取粉丝列表-----------------------------------
    public List<FansBean> getFansList() {
        List<FansBean> list = GsonUtil.getFansList(SPUtils.getFansList());
        return list;
    }
    //--------------------------获取关注列表-----------------------------------
    public List getAttentionList() {
        return GsonUtil.getAttentionList(SPUtils.getAttentionList());

    }
    //--------------------------获取群公告列表-----------------------------------
    public List getNoticeList() {
        return GsonUtil.getNoticeList(SPUtils.getNoticeList());

    }
    //--------------------------获取采购列表-----------------------------------
    public List getPurchaseList() {
        return GsonUtil.getPurchaseList(SPUtils.getPurchaseList());
    }
    //--------------------------获取活动列表-----------------------------------
    public List getActivitysList() {
        return GsonUtil.getActivityList(SPUtils.getActivityList());
    }


    public List getHotList() {
        return GsonUtil.getHotList(SPUtils.getHotList());
    }

    public List getTeaching() {
        return GsonUtil.getTeachingList(SPUtils.getTeachingList());
    }
    public List getGroupPay() {
        return GsonUtil.getGroupPayList(SPUtils.getGroupPayList());
    }

    public List getCreditsList() {
        return GsonUtil.getCreditsList(SPUtils.getCreditsList());
    }
}
