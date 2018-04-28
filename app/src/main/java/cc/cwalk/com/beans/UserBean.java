package cc.cwalk.com.beans;

import java.io.Serializable;

/**
 * Author chen_gw
 * Date 2018/4/24 16:35
 * DES :
 */
public class UserBean implements Serializable {
    public String name ;// 名字
    public String adress ;//所在地
    public String attentiontime ;//关注时间
    public String befanstime;//成为粉丝时间
    public String head ;
    public int sex ;//1 男 其他 女

    public UserBean(String name, String adress, String attentiontime, String befanstime, String head, int sex) {
        this.name = name;
        this.adress = adress;
        this.attentiontime = attentiontime;
        this.befanstime = befanstime;
        this.head = head;
        this.sex = sex;
    }
}
