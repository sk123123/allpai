package com.allpai.entity.user.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 15:21
 * 拉黑用户信息输入
 */
public class UserAddBlackInVo {
    //用户ID
    private Long userId;
    //类型 0 取消拉黑 1拉黑
    private Integer type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
