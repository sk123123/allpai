package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:31
 * 喜欢视频列表输入
 */
public class VideoLikeListInVo {
    private Integer page;
    private Integer limit;
    private Long  userId;
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
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
