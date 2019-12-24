package com.allpai.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 12:17
 * 短视频评论
 */
public class VideoCommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //评论的ID
    private Long commentId;
    //评论的用户iD
    private Long userId;
    //视频ID
    private Long videoId;
    //评论的内容
    private String content;
    //父ID
    private Long parentId;
    //点赞
    private Long goodNum;
    //创建时间
    private Date createTime;
    //对评论用户ID
    private Long toUserId;
    /**
     * 设置：评论的ID
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    /**
     * 获取：评论的ID
     */
    public Long getCommentId() {
        return commentId;
    }
    /**
     * 设置：评论的用户iD
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * 获取：评论的用户iD
     */
    public Long getUserId() {
        return userId;
    }
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
     * 设置：评论的内容
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 获取：评论的内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：父ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    /**
     * 获取：父ID
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     * 设置：点赞
     */
    public void setGoodNum(Long goodNum) {
        this.goodNum = goodNum;
    }
    /**
     * 获取：点赞
     */
    public Long getGoodNum() {
        return goodNum;
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
    public Long getToUserId() {
        return toUserId;
    }
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }
}
