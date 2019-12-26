package com.allpai.entity.video.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:10
 * 短视频话题
 */
public class VideoTopicInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    //热度值
    private Long hotNum;
    //封面
    private String cover;
    //创建时间
    private Date createTime;
    //话题视频点赞数
    private Long goodNum;
    //视频Url
    private String videoUrl;
    //视频Id
    private Long videoId;

    public Long getVideoId() {
        return videoId;
    }
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
    public String getVideoUrl() {
        return videoUrl;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getGoodNum() {
        return goodNum;
    }
    public void setGoodNum(Long goodNum) {
        this.goodNum = goodNum;
    }
    /**
     * 设置：热度值
     */
    public void setHotNum(Long hotNum) {
        this.hotNum = hotNum;
    }
    /**
     * 获取：热度值
     */
    public Long getHotNum() {
        return hotNum;
    }
    /**
     * 设置：封面
     */
    public void setCover(String cover) {
        this.cover = cover;
    }
    /**
     * 获取：封面
     */
    public String getCover() {
        return cover;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
}
