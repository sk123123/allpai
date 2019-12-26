package com.allpai.entity.user.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 13:14
 * 用户配置
 */
public class UserConfigInVo {
    //点赞通知
    private Integer likeNotify;
    //关注通知
    private Integer lookNotify;
    //评论通知
    private Integer commentNotify;
    //私信通知
    private Integer chatNotify;
    public Integer getLikeNotify() {
        return likeNotify;
    }
    public void setLikeNotify(Integer likeNotify) {
        this.likeNotify = likeNotify;
    }
    public Integer getLookNotify() {
        return lookNotify;
    }
    public void setLookNotify(Integer lookNotify) {
        this.lookNotify = lookNotify;
    }
    public Integer getCommentNotify() {
        return commentNotify;
    }
    public void setCommentNotify(Integer commentNotify) {
        this.commentNotify = commentNotify;
    }
    public Integer getChatNotify() {
        return chatNotify;
    }
    public void setChatNotify(Integer chatNotify) {
        this.chatNotify = chatNotify;
    }

}
