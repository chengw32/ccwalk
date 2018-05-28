package cc.cwalk.com.utils;

import java.util.ArrayList;
import java.util.List;

import cc.cwalk.com.beans.CreditsBean;

/**
 * Author chen_gw
 * Date 2018/5/28 17:58
 * DES :
 */
public class CreditsUtils {
    public static void addCredits(String type){
        //添加签到到列表
        List<CreditsBean> creditsList = DataUtils.getInstance().getCreditsList();
        List<CreditsBean.CreditslistBean> myCredits = null;
        CreditsBean creditsBean = null;
        for (int i = 0; i < creditsList.size(); i++) {
            creditsBean = creditsList.get(i);
            //匹配到当前登录的签到列表
            if (SPUtils.getId() == creditsBean.id) {
                myCredits = creditsBean.creditslist;
                break;
            }
        }
        if (null == myCredits) {
            //如果签到列表为空 也要新建这个id对应的签到数据
            myCredits = new ArrayList<>();
            creditsBean = new CreditsBean();
            creditsList.add(0, creditsBean);
        }

        //新建签到数据bean
        creditsBean.id = SPUtils.getId();
        creditsBean.creditslist = myCredits;
        //新建具体签到数据
        CreditsBean.CreditslistBean b = new CreditsBean.CreditslistBean();
        b.des = type;
        b.time = Utils.getTime();
        myCredits.add(0,b);

        SPUtils.setCreditslist(GsonUtil.toJosn(creditsList));

    }
}
