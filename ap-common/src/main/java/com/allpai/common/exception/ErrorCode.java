package com.allpai.common.exception;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 11:20
 * 状态码返回
 */
public enum ErrorCode {
    DataFormatError(10000,"数据格式错误"),
    SingInvalid(10001,"签名无效"),
    ParamInvalid(10002,"参数无效"),
    TokenInvalid(10003,"令牌无效或过期"),
    InsideError(500,"内部错误"),
    InfoError(30000,"信息错误"),
    ;

    private int code;
    private String msg;

    private ErrorCode(int code,String msg){
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
