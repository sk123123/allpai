package com.allpai.entity.video.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:30
 * 视频作品发布输入
 */
public class VideoPublishInVo {
    //可见权限 : 0:自己可见,1:公布
    private Integer seeAuth;
    //位置
    private String place;
    //封面
    private String cover;
    //视频内容(标题)
    private String content;
    //存储类型  0:草稿箱  1:发布后的视频
    private Integer storeType;
    //点播视频ID
    private String vodVideoId;
    //音乐名字
    private String musicName;
    //话题ID
    private Long topicId;
    //详细位置
    private String placeDetail;

    public Integer getSeeAuth() {
        return seeAuth;
    }
    public void setSeeAuth(Integer seeAuth) {
        this.seeAuth = seeAuth;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getStoreType() {
        return storeType;
    }
    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
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
