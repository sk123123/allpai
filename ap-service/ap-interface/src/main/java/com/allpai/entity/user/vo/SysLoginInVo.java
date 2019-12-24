package com.allpai.entity.user.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/20 0020 14:02
 * 系统用户参数输入
 */
public class SysLoginInVo {
    private String username;
    private String password;
    private String captcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
