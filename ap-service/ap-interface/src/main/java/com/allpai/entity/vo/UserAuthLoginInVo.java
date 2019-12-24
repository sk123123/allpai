package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/11 0011 10:48
 * 用户授权登录输入
 */
public class UserAuthLoginInVo {
    //昵称
    private String nickName;
    //性别 0 没填写  1 男  2女
    private Integer sex;
    //授权标识
    private String authKey;
    //授权标识
    private String type;
    //极光ID
    private String jpushId;

    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getAuthKey() {
        return authKey;
    }
    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getJpushId() {
        return jpushId;
    }
    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }
}
