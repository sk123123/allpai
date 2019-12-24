package com.allpai.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 21:35
 */
public class UserInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户ID
    private Long userId;
    //用户平台号
    private String userNum;
    //昵称
    private String nickName;
    //手机号
    private String mobile;
    //密码
    private String password;
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
    //令牌
    private String token;
    //微信授权标识
    private String wechatId;
    //qq授权标识
    private String qqId;
    //微博授权标识
    private String sinaId;
    //余额
    private Double balance;
    //修改时间
    private Date lastTime;
    //粉丝数
    private Long fansNum;
    //关注数
    private Long lookNum;
    //头像地址
    private String headUrl;
    //评论控制   0 可以评论   1 禁止评论
    private Integer commentControl;
    //创建时间
    private Date createTime;
    //登录时间
    private Date loginTime;
    //极光ID
    private String jpushId;
    //用户来源
    private Integer source;

    public UserInfoEntity() {
        if(wechatId == null) wechatId = "";
        if(qqId == null) qqId = "";
        if(sinaId == null) sinaId = "";
    }

    /**
     * 设置：用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * 获取：用户ID
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：用户平台号
     */
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    /**
     * 获取：用户平台号
     */
    public String getUserNum() {
        return userNum;
    }
    /**
     * 设置：昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    /**
     * 获取：昵称
     */
    public String getNickName() {
        return nickName;
    }
    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    /**
     * 获取：生日
     */
    public Date getBirthday() {
        return birthday;
    }
    /**
     * 设置：年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    /**
     * 获取：年龄
     */
    public Integer getAge() {
        return age;
    }
    /**
     * 设置：性别 0 没填写  1 男  2女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    /**
     * 获取：性别 0 没填写  1 男  2女
     */
    public Integer getSex() {
        return sex;
    }
    /**
     * 设置：签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }
    /**
     * 获取：签名
     */
    public String getSign() {
        return sign;
    }
    /**
     * 设置：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 获取：地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：令牌
     */
    public void setToken(String token) {
        this.token = token;
    }
    /**
     * 获取：令牌
     */
    public String getToken() {
        return token;
    }
    /**
     * 设置：微信授权标识
     */
    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }
    /**
     * 获取：微信授权标识
     */
    public String getWechatId() {
        return wechatId;
    }
    /**
     * 设置：qq授权标识
     */
    public void setQqId(String qqId) {
        this.qqId = qqId;
    }
    /**
     * 获取：qq授权标识
     */
    public String getQqId() {
        return qqId;
    }
    /**
     * 设置：微博授权标识
     */
    public void setSinaId(String sinaId) {
        this.sinaId = sinaId;
    }
    /**
     * 获取：微博授权标识
     */
    public String getSinaId() {
        return sinaId;
    }
    /**
     * 设置：余额
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    /**
     * 获取：余额
     */
    public Double getBalance() {
        return balance;
    }
    /**
     * 设置：修改时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
    /**
     * 获取：修改时间
     */
    public Date getLastTime() {
        return lastTime;
    }
    /**
     * 设置：粉丝数
     */
    public void setFansNum(Long fansNum) {
        this.fansNum = fansNum;
    }
    /**
     * 获取：粉丝数
     */
    public Long getFansNum() {
        return fansNum;
    }
    /**
     * 设置：关注数
     */
    public void setLookNum(Long lookNum) {
        this.lookNum = lookNum;
    }
    /**
     * 获取：关注数
     */
    public Long getLookNum() {
        return lookNum;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置：登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }
    /**
     * 设置：登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }


    public Integer getCommentControl() {
        return commentControl;
    }

    public void setCommentControl(Integer commentControl) {
        this.commentControl = commentControl;
    }

    public String getJpushId() {
        return jpushId;
    }

    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }


}
