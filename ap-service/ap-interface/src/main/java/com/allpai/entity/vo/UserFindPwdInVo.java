package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:14
 * 用户找回密码输入
 */
public class UserFindPwdInVo {
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
        return "UserInfoRegInVo [mobile=" + mobile + ", code=" + code
                + ", password=" + password + "]";
    }
}
