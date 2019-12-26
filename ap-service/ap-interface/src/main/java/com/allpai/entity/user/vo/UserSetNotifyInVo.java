package com.allpai.entity.user.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 13:02
 * 用户配置通知参数输入
 */
public class UserSetNotifyInVo {
    //通知类型   1:点赞   2:关注  3:评论 4:私信
    private Integer notifyType;
    //操作类型   0:通知   1: 关闭通知
    private Integer operateType;
    public Integer getNotifyType() {
        return notifyType;
    }
    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }
    public Integer getOperateType() {
        return operateType;
    }
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }
}
