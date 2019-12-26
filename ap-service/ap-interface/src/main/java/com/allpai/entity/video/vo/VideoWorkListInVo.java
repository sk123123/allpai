package com.allpai.entity.video.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:28
 * 视频作品列表
 */
public class VideoWorkListInVo {
    private Integer page;
    private Integer limit;
    private Integer storeType;
    private Long userId;
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
    public Integer getStoreType() {
        return storeType;
    }
    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
