package com.allpai.entity.common.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:13
 * 发送验证码输入
 */
public class SendCodeInVo {
    //发送类型
    private String type;
    //手机号
    private String mobile;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
