package cc.cwalk.com.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Author chen_gw
 * Date 2018/5/23 11:55
 * DES :
 */
public class FansBean extends  BaseBean {

    /**
     * id : 1
     * fanslist : [{"id":2,"befanstime":"2018-05-06"},{"id":3,"befanstime":"2018-05-06"}]
     */

    public int id;
    public List<FanslistBean> fanslist;

    public static class FanslistBean implements Serializable {
        /**
         * id : 2
         * befanstime : 2018-05-06
         */

        public int id;
        public String befanstime;
    }
}
