package cc.cwalk.com.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Author chen_gw
 * Date 2018/5/28 15:56
 * DES :
 */
public class CreditsBean extends  BaseBean {

    /**
     * id : 1
     * creditslist : [{"des":"签到","time":"2018-05-06"},{"des":"签到","time":"2018-05-05"}]
     */

    public int id;
    public List<CreditslistBean> creditslist;

    public static class CreditslistBean implements Serializable {
        /**
         * des : 签到
         * time : 2018-05-06
         */

        public String des;
        public String time;
    }
}
