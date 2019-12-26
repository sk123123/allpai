package com.allpai.entity.video;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 22:39
 * 短视频话题
 */
public class VideoTopicInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //话题ID
    private Long topicId;
    //话题的标题
    private String topicTitle;
    //话题介绍
    private String introduce;
    //热度值
    private Long hotNum;
    //封面
    private String cover;
    //创建时间
    private Date createTime;
    //用户ID
    private Long userId;
    //参与人数
    private Long partakeNum;
    //分享次数
    private Long shareNum;

    public Long getShareNum() {
        return shareNum;
    }
    public void setShareNum(Long shareNum) {
        this.shareNum = shareNum;
    }
    public Long getPartakeNum() {
        return partakeNum;
    }
    public void setPartakeNum(Long partakeNum) {
        this.partakeNum = partakeNum;
    }
    /**
     * 设置：话题ID
     */
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
    /**
     * 获取：话题ID
     */
    public Long getTopicId() {
        return topicId;
    }
    /**
     * 设置：话题的标题
     */
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }
    /**
     * 获取：话题的标题
     */
    public String getTopicTitle() {
        return topicTitle;
    }
    /**
     * 设置：话题介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    /**
     * 获取：话题介绍
     */
    public String getIntroduce() {
        return introduce;
    }
    /**
     * 设置：热度值
     */
    public void setHotNum(Long hotNum) {
        this.hotNum = hotNum;
    }
    /**
     * 获取：热度值
     */
    public Long getHotNum() {
        return hotNum;
    }
    /**
     * 设置：封面
     */
    public void setCover(String cover) {
        this.cover = cover;
    }
    /**
     * 获取：封面
     */
    public String getCover() {
        return cover;
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
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
