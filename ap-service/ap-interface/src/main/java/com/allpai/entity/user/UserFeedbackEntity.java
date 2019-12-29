package com.allpai.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/29 0029 15:35
 * 用户反馈
 */
public class UserFeedbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //反馈ID
    private Long feedbackId;
    //用户ID
    private Long userId;
    //反馈内容
    private String content;
    //联系方式
    private String contact;
    //图片路径
    private String imgPath;
    //创建时间
    private Date createTime;

    /**
     * 设置：反馈ID
     */
    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }
    /**
     * 获取：反馈ID
     */
    public Long getFeedbackId() {
        return feedbackId;
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
     * 设置：反馈内容
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 获取：反馈内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：联系方式
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
    /**
     * 获取：联系方式
     */
    public String getContact() {
        return contact;
    }
    /**
     * 设置：图片路径
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    /**
     * 获取：图片路径
     */
    public String getImgPath() {
        return imgPath;
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
