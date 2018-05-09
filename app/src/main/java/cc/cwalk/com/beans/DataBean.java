package cc.cwalk.com.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chen on 2018/5/6.
 * Des :${input}
 */

public class DataBean implements Serializable{

    /**
     * name : 熊小莫
     * adress : 广州
     * attentiontime : 2018-5-6
     * befanstime : 2018-4-5
     * head : 03514dbb47703398b8a96b1a9ab013c6.jpg
     * sex : 1
     * videos : [{"numZang":32,"numEvaluate":54,"numPaly":2356,"isVideo":1,"time":"2018-5-6","videoUrl":"sample.mp4","videoImages":"sample.png","mtitle":"cwalk 各种快"},{"numZang":23,"numEvaluate":64,"numPaly":23356,"isVideo":1,"time":"2018-5-5","videoUrl":"sample2.flv","videoImages":"sample2.png","mtitle":"cwalk"},{"numZang":23,"numEvaluate":86,"numPaly":3586,"isVideo":0,"time":"2018-5-4","videoUrl":"slkdjgejgseslsgej.flv","videoImages":"slkdjgejgseslsgej.png","mtitle":"C-walk"},{"numZang":2,"numEvaluate":74,"numPaly":3426,"isVideo":1,"time":"2018-5-0","videoUrl":"062E0B53EA8BBB94F6B0CEFD87D8286B.flv","videoImages":"062E0B53EA8BBB94F6B0CEFD87D8286B.png","mtitle":"Cwalk Abelard-Hey stranger_高清"},{"numZang":124,"numEvaluate":63,"numPaly":473123,"isVideo":1,"time":"2018-4-6","videoUrl":"D7FEC457DAFFEE65EE67A42982E02921.mp4","videoImages":"D7FEC457DAFFEE65EE67A42982E02921.png","mtitle":"RARE CWALK_高清"},{"numZang":23,"numEvaluate":364,"numPaly":6326,"isVideo":0,"time":"2018-4-6","videoUrl":"F22630B947F92D63E79A84DA1DE398B9.mp4","videoImages":"F22630B947F92D63E79A84DA1DE398B9.png","mtitle":"CWalk - PuGGy Mixtape - The Beast_高清"},{"numZang":235,"numEvaluate":366,"numPaly":243763,"isVideo":1,"time":"2018-4-5","videoUrl":"9B9A4A54576B0FF0B8A36A4E5DE66382.mp4","videoImages":"9B9A4A54576B0FF0B8A36A4E5DE66382.png","mtitle":"【曳舞天下】Cwalk Tutorial [Beginners] II[高清版]"},{"numZang":23,"numEvaluate":56,"numPaly":46234,"isVideo":1,"time":"2018-4-23","videoUrl":"2244CD0594A81165D7186C1987C50D9E.flv","videoImages":"2244CD0594A81165D7186C1987C50D9E.png","mtitle":"【骑士】 2014 这才是真正的C-walk 超清[超清版]"},{"numZang":3,"numEvaluate":1,"numPaly":2356,"isVideo":0,"time":"2018-4-12","videoUrl":"FE73411CFCC4935215173B4B071B75A8.mp4","videoImages":"FE73411CFCC4935215173B4B071B75A8.png","mtitle":"TFC C-Walk Choreo _ Routine _ RS PROD_超清"},{"numZang":335,"numEvaluate":23,"numPaly":32356,"isVideo":0,"time":"2018-4-13","videoUrl":"644BB8748DF89F20BB4BDE08FBB139C1.mp4","videoImages":"644BB8748DF89F20BB4BDE08FBB139C1.png","mtitle":"C-Walk Choreo _ Студия DANCEHALL_超清"},{"numZang":325,"numEvaluate":366,"numPaly":36622,"isVideo":1,"time":"2018-4-6","videoUrl":"F44136D83A2AFB70BB13AAA32535825B.mp4","videoImages":"F44136D83A2AFB70BB13AAA32535825B.png","mtitle":"V.A.LOKOS MIXTAPE_高清"},{"numZang":235,"numEvaluate":366,"numPaly":356672,"isVideo":0,"time":"2018-4-6","videoUrl":"01AC2E0111D3A6BA0D2B1F9904D6EA3E.mp4","videoImages":"01AC2E0111D3A6BA0D2B1F9904D6EA3E.png","mtitle":"【珍藏】V.A. Lokos - Cwalk - Before You Go_超清"},{"numZang":3333,"numEvaluate":35562,"numPaly":352662,"isVideo":1,"time":"2018-4-27","videoUrl":"8AF705836C6694848E227F3D7F5D4ABD.mp4","videoImages":"8AF705836C6694848E227F3D7F5D4ABD.png","mtitle":"大神级舞步"},{"numZang":235,"numEvaluate":3562,"numPaly":22356,"isVideo":0,"time":"2018-4-21","videoUrl":"sample1.mp4","videoImages":"sample1.png","mtitle":"i love the rain"}]
     */

    private String name;
    private String adress;
    private String attentiontime;
    private String befanstime;
    private String head;
    private int sex;
    private List<VideosBean> videos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAttentiontime() {
        return attentiontime;
    }

    public void setAttentiontime(String attentiontime) {
        this.attentiontime = attentiontime;
    }

    public String getBefanstime() {
        return befanstime;
    }

    public void setBefanstime(String befanstime) {
        this.befanstime = befanstime;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean implements Serializable{
        /**
         * numZang : 32
         * numEvaluate : 54
         * numPaly : 2356
         * isVideo : 1
         * time : 2018-5-6
         * videoUrl : sample.mp4
         * videoImages : sample.png
         * mtitle : cwalk 各种快
         */

        private int numZang;
        private int numEvaluate;
        private int numPaly;
        private int isVideo;
        private String time;
        private String videoUrl;
        private String videoImages;
        private String mtitle;

        public int getNumZang() {
            return numZang;
        }

        public void setNumZang(int numZang) {
            this.numZang = numZang;
        }

        public int getNumEvaluate() {
            return numEvaluate;
        }

        public void setNumEvaluate(int numEvaluate) {
            this.numEvaluate = numEvaluate;
        }

        public int getNumPaly() {
            return numPaly;
        }

        public void setNumPaly(int numPaly) {
            this.numPaly = numPaly;
        }

        public int getIsVideo() {
            return isVideo;
        }

        public void setIsVideo(int isVideo) {
            this.isVideo = isVideo;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getVideoImages() {
            return videoImages;
        }

        public void setVideoImages(String videoImages) {
            this.videoImages = videoImages;
        }

        public String getTitle() {
            return mtitle;
        }

        public void setMtitle(String mtitle) {
            this.mtitle = mtitle;
        }
    }
}
