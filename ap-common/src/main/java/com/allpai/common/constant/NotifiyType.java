package com.allpai.common.constant;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 13:17
 * 通知
 */
public enum NotifiyType {yes(0,"通知"),
    no(1,"不通知"),
    ;

    private int code;
    private String msg;
    private NotifiyType(int code,String msg) {
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
