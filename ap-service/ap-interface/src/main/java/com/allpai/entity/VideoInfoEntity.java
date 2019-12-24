package com.allpai.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:03
 * 短视频信息
 */
public class VideoInfoEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    //视频ID
    private Long videoId;
    //用户ID
    private Long userId;
    //封面
    private String cover;
    //视频内容
    private String content;
    //位置
    private String place;
    //详细位置
    private String placeDetail;
    //可见权限 : 0:自己可见,1:公布
    private Integer seeAuth;
    //视频Url
    private String videoUrl;
    //存储类型  0:草稿箱  1:发布后的视频
    private Integer storeType;
    //分享数
    private Long shareNum;
    //视频点赞
    private Long goodNum;
    //热度值
    private Long hotNum;
    //点播视频ID
    private String vodVideoId;
    //更新时间
    private Date lastTime;
    //创建时间
    private Date createTime;
    //音乐名字
    private String musicName;
    //带水印的视频地址
    private String waterVideoUrl;
    //话题ID
    private Long topicId;

    /**
     * 设置：视频ID
     */
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
    /**
     * 获取：视频ID
     */
    public Long getVideoId() {
        return videoId;
    }
    /**
     * 设置：
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * 获取：
     */
    public Long getUserId() {
        return userId;
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
     * 设置：视频内容
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 获取：视频内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：位置
     */
    public void setPlace(String place) {
        this.place = place;
    }
    /**
     * 获取：位置
     */
    public String getPlace() {
        return place;
    }
    /**
     * 设置：可见权限 : 0:自己可见,1:公布
     */
    public void setSeeAuth(Integer seeAuth) {
        this.seeAuth = seeAuth;
    }
    /**
     * 获取：可见权限 : 0:自己可见,1:公布
     */
    public Integer getSeeAuth() {
        return seeAuth;
    }
    /**
     * 设置：视频Url
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    /**
     * 获取：视频Url
     */
    public String getVideoUrl() {
        return videoUrl;
    }
    /**
     * 设置：存储类型  0:草稿箱  1:发布后的视频
     */
    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }
    /**
     * 获取：存储类型  0:草稿箱  1:发布后的视频
     */
    public Integer getStoreType() {
        return storeType;
    }
    /**
     * 设置：分享数
     */
    public void setShareNum(Long shareNum) {
        this.shareNum = shareNum;
    }
    /**
     * 获取：分享数
     */
    public Long getShareNum() {
        return shareNum;
    }
    /**
     * 设置：视频点赞
     */
    public void setGoodNum(Long goodNum) {
        this.goodNum = goodNum;
    }
    /**
     * 获取：视频点赞
     */
    public Long getGoodNum() {
        return goodNum;
    }
    /**
     * 设置：更新时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
    /**
     * 获取：更新时间
     */
    public Date getLastTime() {
        return lastTime;
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
    public String getVodVideoId() {
        return vodVideoId;
    }
    public void setVodVideoId(String vodVideoId) {
        this.vodVideoId = vodVideoId;
    }
    public String getMusicName() {
        return musicName;
    }
    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }
    public String getWaterVideoUrl() {
        return waterVideoUrl;
    }
    public void setWaterVideoUrl(String waterVideoUrl) {
        this.waterVideoUrl = waterVideoUrl;
    }
    public Long getTopicId() {
        return topicId;
    }
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
    public String getPlaceDetail() {
        return placeDetail;
    }
    public void setPlaceDetail(String placeDetail) {
        this.placeDetail = placeDetail;
    }
}
