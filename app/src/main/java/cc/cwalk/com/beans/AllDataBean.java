package cc.cwalk.com.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Author chen_gw
 * Date 2018/4/24 16:35
 * DES : 全部的数据
 */
public class AllDataBean extends BaseBean{

    /**
     * userid : 1
     * type : 1
     * evaluate : [{"userid":2,"time":"2018-5-17","des":"岁月不老，情怀还在"},{"time":"2018-5-17","des":"不仅仅是喜欢。。。。。"},{"time":"2018-5-17","des":"D.LHX国内最强高清C WALK教程 by D.LHX："}]
     * video : {"numPaly":2356,"time":"2018-5-6","videoUrl":"sample.mp4","videoImages":"sample.png","images":["sample.png","sample.png"],"content":"cwalk 各种快"}
     * zang : [{"id":1},{"id":2}]
     */

    public int userid;
    public int type;
    public VideoBean video;
    public List<EvaluateBean> evaluate;
    public List<ZangBean> zang;

    public static class VideoBean implements Serializable {
        /**
         * numPaly : 2356
         * time : 2018-5-6
         * videoUrl : sample.mp4
         * videoImages : sample.png
         * images : ["sample.png","sample.png"]
         * content : cwalk 各种快
         */

        public int numPaly;
        public String time;
        public String videoUrl;
        public String videoImages;
        public String content;
        public List<String> images;
    }

    public static class EvaluateBean  implements Serializable {
        /**
         * userid : 2
         * time : 2018-5-17
         * des : 岁月不老，情怀还在
         */

        public int userid;
        public String time;
        public String des;
    }

    public static class ZangBean  implements Serializable  {
        /**
         * id : 1
         */

        public int id;
    }
}
