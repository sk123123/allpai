package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:15
 * 用户修改密码输入
 */
public class UserModifyPwdInVo {
    //验证码
    private String code;
    //旧密码
    private String oldPassWord;
    //密码
    private String password;

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
    public String getOldPassWord() {
        return oldPassWord;
    }
    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }
}
