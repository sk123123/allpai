package com.allpai.entity.user.vo;

import java.io.Serializable;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/11 0011 10:38
 * 用户注册参数输入(app端)
 */
public class UserInfoRegInVo implements Serializable{
    private static final Long serialVersionUID = 1L;
    //手机号
    private String mobile;
    //验证码
    private String code;
    //密码
    private String password;

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfoRegInVo [mobile=" + mobile + ", code=" + code + ", password=" + password + "]";
    }
}
