package com.allpai.entity.music.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/14 0014 16:15
 * 音乐列表参数输入
 */
public class MusicListInVo {
    //风格类型
    private Integer styleType;
    private Integer page;
    private Integer limit;

    public Integer getStyleType() {
        return styleType;
    }

    public void setStyleType(Integer styleType) {
        this.styleType = styleType;
    }

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
