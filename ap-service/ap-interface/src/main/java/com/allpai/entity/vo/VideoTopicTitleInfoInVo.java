package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 22:46
 * 短视频话题标题参数输入
 */
public class VideoTopicTitleInfoInVo {
    //话题的标题
    private String topicTitle;
    //参与人数
    private Long partakeNum;
    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
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
