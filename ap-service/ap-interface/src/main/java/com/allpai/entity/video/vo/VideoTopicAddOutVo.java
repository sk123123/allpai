package com.allpai.entity.video.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 22:42
 * 新增视频话题参数输出
 */
public class VideoTopicAddOutVo {
    //话题ID
    private Long topicId;
    //封面
    private String cover;
    //参与人数
    private Long partakeNum;

    public Long getPartakeNum() {
        return partakeNum;
    }
    public void setPartakeNum(Long partakeNum) {
        this.partakeNum = partakeNum;
    }
    public Long getTopicId() {
        return topicId;
    }
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }

}
