package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:30
 * 视频关注作品列表输入
 */
public class VideoLookListInVo {
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
}
