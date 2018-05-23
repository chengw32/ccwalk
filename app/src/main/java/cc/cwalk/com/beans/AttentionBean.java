package cc.cwalk.com.beans;

import java.util.List;

/**
 * Author chen_gw
 * Date 2018/5/23 11:55
 * DES :
 */
public class AttentionBean extends BaseBean {

    /**
     * id : 1
     * attentionlist : [{"id":2,"befanstime":"2018-05-06"},{"id":3,"befanstime":"2018-05-06"}]
     */

    public int id;
    public List<AttentionlistBean> attentionlist;

    public static class AttentionlistBean {
        /**
         * id : 2
         * befanstime : 2018-05-06
         */

        public int id;
        public String befanstime;
    }
}
