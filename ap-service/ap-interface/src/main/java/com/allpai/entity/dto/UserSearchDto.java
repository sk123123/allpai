package com.allpai.entity.dto;

import java.io.Serializable;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/11 0011 10:28
 * 用户搜索输出
 */
public class UserSearchDto implements Serializable{
    private static final long serialVersionUID = 1L;
    //用户名
    private String nickName;
    //用户图像
    private String headUrl;
    //用户平台号
    private String userNum;
    //用户粉丝数
    private String fansNum;
    //用户作品数
    private Integer workNum;
    //用户获赞数
    private Long goodNum;
    //是否关注(0：关注 ，1：未关注)
    private Integer type;
    //用户Id
    private Long userId;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getHeadUrl() {
        return headUrl;
    }
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public String getUserNum() {
        return userNum;
    }
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    public String getFansNum() {
        return fansNum;
    }
    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }
    public Integer getWorkNum() {
        return workNum;
    }
    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }
    public Long getGoodNum() {
        return goodNum;
    }
    public void setGoodNum(Long goodNum) {
        this.goodNum = goodNum;
    }
    public Integer getType() {
        if(type == null) type = 0;
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

}
