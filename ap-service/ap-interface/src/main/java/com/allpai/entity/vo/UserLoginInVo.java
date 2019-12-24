package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/11 0011 11:51
 * 用户登录输入
 */
public class UserLoginInVo {
    //手机号
    private String mobile;
    //密码
    private String password;
    //极光ID
    private String jpushId;

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getJpushId() {
        return jpushId;
    }
    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }
}
