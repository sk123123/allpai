package com.allpai.entity.video.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:29
 * 推荐视频作品列表输入
 */
public class VideoHotListInVo {
    private Integer page;
    private Integer limit;
    private Integer hotType;
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
    public Integer getHotType() {
        return hotType;
    }
    public void setHotType(Integer hotType) {
        this.hotType = hotType;
    }
}
