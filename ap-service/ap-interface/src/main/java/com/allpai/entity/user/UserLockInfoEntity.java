package com.allpai.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:41
 * 用户锁定信息
 */
public class UserLockInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户锁定信息的ID
    private Long lockId;
    //用户ID
    private Long userId;
    //创建时间
    private Date createTime;

    /**
     * 设置：用户锁定信息的ID
     */
    public void setLockId(Long lockId) {
        this.lockId = lockId;
    }
    /**
     * 获取：用户锁定信息的ID
     */
    public Long getLockId() {
        return lockId;
    }
    /**
     * 设置：
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * 获取：
     */
    public Long getUserId() {
        return userId;
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
}
