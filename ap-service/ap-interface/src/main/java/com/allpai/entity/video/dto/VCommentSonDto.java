package com.allpai.entity.video.dto;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 12:20
 * 短视频子评论
 */
public class VCommentSonDto {
    //评论的ID
    private Long commentId;
    //评论的用户iD
    private Long userId;
    //用户昵称
    private String nickName;
    //评论的内容
    private String content;
    private Long toUserId;
    private String toNickName;
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
}
