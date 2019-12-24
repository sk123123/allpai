package com.allpai.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 12:54
 * 用户配置
 */
public class UserConfigEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //用户配置ID
    private Long configId;
    //用户ID
    private Long userId;
    //点赞通知
    private Integer likeNotify;
    //关注通知
    private Integer lookNotify;
    //评论通知
    private Integer commentNotify;
    //私信通知
    private Integer chatNotify;
    //wifi下是否播放 0: 不播放   1:播放
    private Integer wifiPlay;
    //读评论的时间
    private Date readCommentTime;
    //创建时间
    private Date createTime;

    public UserConfigEntity() {
        if(likeNotify == null) likeNotify = 0;
        if(lookNotify == null) lookNotify = 0;
        if(commentNotify == null) commentNotify = 0;
        if(chatNotify == null) chatNotify = 0;
        if(wifiPlay == null) wifiPlay = 0;
    }


    public Integer getWifiPlay() {
        return wifiPlay;
    }

    public void setWifiPlay(Integer wifiPlay) {
        this.wifiPlay = wifiPlay;
    }


    /**
     * 设置：
     */
    public void setConfigId(Long configId) {
        this.configId = configId;
    }
    /**
     * 获取：
     */
    public Long getConfigId() {
        return configId;
    }
    /**
     * 设置：用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * 获取：用户ID
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：点赞通知
     */
    public void setLikeNotify(Integer likeNotify) {
        this.likeNotify = likeNotify;
    }
    /**
     * 获取：点赞通知
     */
    public Integer getLikeNotify() {
        return likeNotify;
    }
    /**
     * 设置：关注通知
     */
    public void setLookNotify(Integer lookNotify) {
        this.lookNotify = lookNotify;
    }
    /**
     * 获取：关注通知
     */
    public Integer getLookNotify() {
        return lookNotify;
    }
    /**
     * 设置：评论通知
     */
    public void setCommentNotify(Integer commentNotify) {
        this.commentNotify = commentNotify;
    }
    /**
     * 获取：评论通知
     */
    public Integer getCommentNotify() {
        return commentNotify;
    }
    /**
     * 设置：私信通知
     */
    public void setChatNotify(Integer chatNotify) {
        this.chatNotify = chatNotify;
    }
    /**
     * 获取：私信通知
     */
    public Integer getChatNotify() {
        return chatNotify;
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


    public Date getReadCommentTime() {
        return readCommentTime;
    }


    public void setReadCommentTime(Date readCommentTime) {
        this.readCommentTime = readCommentTime;
    }

}
