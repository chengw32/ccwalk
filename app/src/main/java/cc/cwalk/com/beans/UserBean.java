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
    public int sex ;//1 男 其他 女

    public UserBean(String name,String adress, int sex) {
        this.name = name;
        this.adress = adress;
        this.sex = sex;
    }
}
