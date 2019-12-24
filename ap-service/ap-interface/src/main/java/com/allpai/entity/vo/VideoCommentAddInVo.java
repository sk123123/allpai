package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 12:24
 * 增加短视频评论输入
 */
public class VideoCommentAddInVo {
    //视频ID
    private Long videoId;
    //评论的内容
    private String content;
    //父ID
    private Long parentId;
    //对评论的用户ID
    private Long toUserId;

    public Long getVideoId() {
        return videoId;
    }
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public Long getToUserId() {
        return toUserId;
    }
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }
}
