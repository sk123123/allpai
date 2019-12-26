package com.allpai.entity.user.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 12:26
 * 用户评论删除参数输出
 */
public class UserCommentDeleteInVo {
    //评论ID
    private Long[] commentIds;

    public Long[] getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(Long[] commentIds) {
        this.commentIds = commentIds;
    }
}
