package com.allpai.entity.music.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/14 0014 16:16
 * 音乐搜索
 */
public class MusicSearchInVo {
    private String searchKey;

    private Integer page;

    private Integer limit;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
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
