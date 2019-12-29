package com.allpai.entity.user.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 20:41
 * 用户评论控制参数输入
 */
public class UserCommentControlInVo {
    //类型 0 允许评论  1 禁止评论
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
