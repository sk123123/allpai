package com.allpai.entity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户关注
 * @author sunkai
 * @version 1.0
 * @date 2019/12/11 0011 10:24
 */
public class UserInfoLookDto implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户ID
    private Long userId;
    //用户平台号
    private String userNum;
    //昵称
    private String nickName;
    //签名
    private String sign;
    //粉丝数
    private Long fansNum;
    //关注数
    private Long lookNum;
    //头像地址
    private String headUrl;
    //创建时间
    private Date createTime;
    //作品数
    private Long workNum;
    //更新标记
    private boolean updateFlag;
    //视频总赞数
    private Long goodNum;

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

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public Long getWorkNum() {
        return workNum;
    }
    public void setWorkNum(Long workNum) {
        this.workNum = workNum;
    }
    public boolean isUpdateFlag() {
        return updateFlag;
    }
    public void setUpdateFlag(boolean updateFlag) {
        this.updateFlag = updateFlag;
    }
    public Long getGoodNum() {
        return goodNum;
    }
    public void setGoodNum(Long goodNum) {
        this.goodNum = goodNum;
    }

}
