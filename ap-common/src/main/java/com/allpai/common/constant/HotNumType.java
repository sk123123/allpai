package com.allpai.common.constant;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 18:11
 */
public enum HotNumType {
    LIKE(1,"点赞"),
    COMMENT(2,"评论"),
    SHARE(3,"分享转发"),
    ATTENT(4,"关注")
    ;

    private int code;
    private String msg;

    private HotNumType(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
