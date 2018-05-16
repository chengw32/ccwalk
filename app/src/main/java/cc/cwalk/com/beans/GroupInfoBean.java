package cc.cwalk.com.beans;

import java.io.Serializable;

/**
 * Author chen_gw
 * Date 2018/5/14 15:06
 * DES :
 */
public class GroupInfoBean extends BaseBean{

    /**
     * name : 张伟
     * niname : 海草`
     * sex : 1
     * creater : 1
     * manager : 1
     * grade : 大三
     * college : 理工
     * money : 400
     * time : 2018-5-14
     * profession : 计算机科学与技术
     * jointime : 2018-5-6
     * persondes : 做一条有梦想的咸鱼
     * title : 团服定制
     * des : 由于社团统一管理决定订制团服，自由参与。到负责人报名然后交费用。开始制作后不退款。请知晓。
     * head : 03514dbb47703398b8a96b1a9ab013c6.jpg
     */

    private String name;
    private String niname;
    private int sex;
    private int creater;
    private int manager;
    private String grade;
    private String college;
    private String money;
    private String time;
    private String profession;
    private String jointime;
    private String persondes;
    private String activity ;
    private String activitydes ;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivitydes() {
        return activitydes;
    }

    public void setActivitydes(String activitydes) {
        this.activitydes = activitydes;
    }

    private String title;
    private String des;
    private String head;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNiname() {
        return niname;
    }

    public void setNiname(String niname) {
        this.niname = niname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCreater() {
        return creater;
    }

    public void setCreater(int creater) {
        this.creater = creater;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getJointime() {
        return jointime;
    }

    public void setJointime(String jointime) {
        this.jointime = jointime;
    }

    public String getPersondes() {
        return persondes;
    }

    public void setPersondes(String persondes) {
        this.persondes = persondes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
