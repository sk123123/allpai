package com.allpai.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 15:15
 * 用户与用户关系表
 */
public class UserUserRelationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long userUserId;
    //用户ID
    private Long userId;
    //被关注的用户ID
    private Long toUserId;
    //创建时间
    private Date createTime;
    //类型  1 关注关系   2  拉黑关系
    private Integer type;
    //最后得修改时间
    private Date lastTime;

    /**
     * 设置：
     */
    public void setUserUserId(Long userUserId) {
        this.userUserId = userUserId;
    }
    /**
     * 获取：
     */
    public Long getUserUserId() {
        return userUserId;
    }
    /**
     * 设置：用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * 获取：用户ID
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：被关注的用户ID
     */
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }
    /**
     * 获取：被关注的用户ID
     */
    public Long getToUserId() {
        return toUserId;
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
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Date getLastTime() {
        return lastTime;
    }
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
