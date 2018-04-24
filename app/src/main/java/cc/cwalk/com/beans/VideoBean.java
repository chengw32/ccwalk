package cc.cwalk.com.beans;

import java.io.Serializable;

import cc.cwalk.com.utils.DataUtils;

/**
 * Author chen_gw
 * Date 2018/4/24 15:11
 * DES : 存储视频相关信息的类
 */
public class VideoBean implements Serializable {
    public String videoUrl ;
    public String videoImages ;
    public String beanId ;
    public String mtitle ;

    public VideoBean(String videoUrl, String videoImages, String beanId,String title) {
        this.videoUrl = DataUtils.baseUrl + videoUrl;
        this.videoImages = DataUtils.baseUrl + videoImages;
        this.beanId = beanId;
        this.mtitle = title;
    }
}
