package com.allpai.entity.video.dto;

import java.util.Date;
import java.util.List;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 12:19
 * 短视频父评论
 */
public class VideoCommentDto {
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
    //点赞数
    private Long goodNum;
    //创建时间
    private Date createTime;
    //头像地址
    private String headUrl;
    //用户昵称
    private String nickName;
    //子评论总数
    private Integer sonNum;
    //是否点赞
    private boolean good;

    //子评论列表
    private List<VCommentSonDto> sonList;

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
    public String getHeadUrl() {
        return headUrl;
    }
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public List<VCommentSonDto> getSonList() {
        return sonList;
    }
    public void setSonList(List<VCommentSonDto> sonList) {
        this.sonList = sonList;
    }
    public Integer getSonNum() {
        return sonNum;
    }
    public void setSonNum(Integer sonNum) {
        this.sonNum = sonNum;
    }
    public boolean isGood() {
        return good;
    }
    public void setGood(boolean good) {
        this.good = good;
    }
}
