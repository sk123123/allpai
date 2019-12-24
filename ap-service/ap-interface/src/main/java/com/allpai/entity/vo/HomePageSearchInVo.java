package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:33
 * 主页搜索
 */
public class HomePageSearchInVo {
    //搜索类型(0:综合 1：视频 2：用户   3 ：话题)
    private Integer type;
    //输入内容
    private String content;
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
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
