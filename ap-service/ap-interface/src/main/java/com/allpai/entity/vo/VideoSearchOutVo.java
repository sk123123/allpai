package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:12
 * 视频信息输出
 */
public class VideoSearchOutVo {
    //用户名
    private String nickName;
    //用户图像
    private String headUrl;
    //视频内容
    private String content;
    //视频url
    private String videoUrl;
    //视频点赞数
    private Long goodNum;
    //视频Id
    private Long videoId;
    //视频封面地址
    private String cover;
    //视频评论数
    private Long commentNum;
    //视频转发数
    private Long shareNum;
    //类型   0 取消点赞  1 点赞
    private Integer type;
    //用户ID
    private Long userId;
    //音乐名称
    private String musicName;


    public String getMusicName() {
        return musicName;
    }
    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Integer getType() {
        if(type == null) type = 0;
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Long getCommentNum() {
        return commentNum;
    }
    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }
    public Long getShareNum() {
        return shareNum;
    }
    public void setShareNum(Long shareNum) {
        this.shareNum = shareNum;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getHeadUrl() {
        return headUrl;
    }
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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
    public Long getVideoId() {
        return videoId;
    }
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
}
