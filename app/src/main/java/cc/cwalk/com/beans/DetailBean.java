package cc.cwalk.com.beans;

/**
 * Created by Chen on 2018/4/25.
 * Des  帖子数据详情
 */

public class DetailBean {
   public int numZang ;//赞数量
    public int numEvaluate ;//评论数量
    public  int numPaly;//播放次数
    public  String time ;

    public DetailBean( int numZang, int numEvaluate, int numPaly, String time) {
        this.numZang = numZang;
        this.numEvaluate = numEvaluate;
        this.numPaly = numPaly;
        this.time = time;
    }
}
