package com.allpai.entity.video.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 22:41
 * 话题新增参数输入
 */
public class VideoTopicAddInVo {
    //话题标题
    private String topicTitle;
    //话题介绍
    private String introduce;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }
}
