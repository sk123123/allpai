package com.allpai.entity.user.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/29 0029 15:37
 * 用户反馈入参
 */
public class UserFeedbackAddInVo {
    //反馈内容
    private String content;
    //联系方式
    private String contact;
    //图片路径
    private String imgPath;

    public UserFeedbackAddInVo(){}
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
