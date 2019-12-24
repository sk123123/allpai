package com.allpai.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 11:11
 * 用户和话题关系表
 */
public class UserTopicRelationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //关系ID
    private Long userTopicId;
    //用户ID
    private Long userId;
    //话题ID
    private Long topicId;
    //类型 :  1 分享关系
    private Integer type;
    //分享的次数
    private Integer totalNum;
    //创建时间
    private Date createTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserTopicId() {
        return userTopicId;
    }

    public void setUserTopicId(Long userTopicId) {
        this.userTopicId = userTopicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
