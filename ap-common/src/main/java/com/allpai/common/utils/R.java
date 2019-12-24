package com.allpai.common.utils;

import com.allpai.common.exception.ErrorCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 11:18
 * 数据返回
 */
public class R extends HashMap<String,Object> implements Serializable {
    private static final long serialVersionUID = 1L;
    public R() {
        put("code", 0);
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R error(ErrorCode errrorCode) {
        R r = new R();
        r.put("code", errrorCode.getCode());
        r.put("msg", errrorCode.getMsg());
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
