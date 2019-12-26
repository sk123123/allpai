package com.allpai.entity.user.vo;
/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:18
 * 用户黑名单列表输入
 */
public class PageListInVo {
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
