package com.allpai.entity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户自身信息
 * @author sunkai
 * @version 1.0
 * @date 2019/12/11 0011 10:25
 */
public class UserInfoSelfDto implements Serializable{
    private static final long serialVersionUID = 1L;
    //用户ID
    private Long userId;
    //用户平台号
    private String userNum;
    //昵称
    private String nickName;
    //手机号
    private String mobile;
    //生日
    private Date birthday;
    //年龄
    private Integer age;
    //性别 0 没填写  1 男  2女
    private Integer sex;
    //签名
    private String sign;
    //地址
    private String address;
    //粉丝数
    private Long fansNum;
    //关注数
    private Long lookNum;
    //视频总赞数
    private Long goodNum;
    //头像地址
    private String headUrl;
    //评论更新数
    private Integer commentNew;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserNum() {
        return userNum;
    }
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Long getFansNum() {
        return fansNum;
    }
    public void setFansNum(Long fansNum) {
        this.fansNum = fansNum;
    }
    public Long getLookNum() {
        return lookNum;
    }
    public void setLookNum(Long lookNum) {
        this.lookNum = lookNum;
    }
    public Long getGoodNum() {
        return goodNum;
    }
    public void setGoodNum(Long goodNum) {
        this.goodNum = goodNum;
    }
    public String getHeadUrl() {
        return headUrl;
    }
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Integer getCommentNew() {
        return commentNew;
    }
    public void setCommentNew(Integer commentNew) {
        this.commentNew = commentNew;
    }
}
