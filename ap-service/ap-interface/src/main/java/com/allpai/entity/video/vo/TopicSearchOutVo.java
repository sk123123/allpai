package com.allpai.entity.video.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 23:13
 * 话题搜索输出
 */
public class TopicSearchOutVo {
    //话题ID
    private Long topicId;
    //话题的标题
    private String topicTitle;
    //参与人数
    private Long partakeNum;

    public Long getTopicId() {
        return topicId;
    }
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
    public String getTopicTitle() {
        return topicTitle;
    }
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }
    public Long getPartakeNum() {
        return partakeNum;
    }
    public void setPartakeNum(Long partakeNum) {
        this.partakeNum = partakeNum;
    }
}
