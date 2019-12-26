package com.allpai.entity.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 12:21
 * 用户评论
 */
public class UserCommentDto implements Serializable {
    private static final long serialVersionUID = 1L;
    //评论的ID
    private Long commentId;
    //评论的用户ID
    private Long userId;
    //用户昵称
    private String nickName;
    //评论的内容
    private String content;
    //视频用户ID
    private Long videoUserId;
    private Long toUserId;
    private String toNickName;
    //用户图像地址
    private String headUrl;
    //封面
    private String cover;
    //创建时间
    private Date createTime;
    //视频ID
    private Long videoId;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getToUserId() {
        return toUserId;
    }
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }
    public String getToNickName() {
        return toNickName;
    }
    public void setToNickName(String toNickName) {
        this.toNickName = toNickName;
    }
    public Long getCommentId() {
        return commentId;
    }
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    public String getHeadUrl() {
        return headUrl;
    }
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getVideoId() {
        return videoId;
    }
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
    public Long getVideoUserId() {
        return videoUserId;
    }
    public void setVideoUserId(Long videoUserId) {
        this.videoUserId = videoUserId;
    }
}
