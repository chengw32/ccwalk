package cc.cwalk.com.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chen on 2018/4/25.
 * Des  帖子数据详情
 */

public class DetailBean extends  BaseBean{
   public int numZang ;//赞数量
    public int numEvaluate ;//评论数量
    public  int numPaly;//播放次数
    public  String time ;
    public int isVideo ;
    public List<VideoBean> videoBeans ;

    public DetailBean(int isVideo, int numZang, int numEvaluate, int numPaly, String time) {
        this.isVideo = isVideo;
        this.numZang = numZang;
        this.numEvaluate = numEvaluate;
        this.numPaly = numPaly;
        this.time = time;
    }
}
