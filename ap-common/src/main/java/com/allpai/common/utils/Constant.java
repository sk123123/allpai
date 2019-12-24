package com.allpai.common.utils;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:52
 * 常量定义
 */
public class Constant {
    //菜单类型
    public enum MenuType {
        CATALOG(0),//目录
        MENU(1),//菜单
        BUTTON(2);//按钮
        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        NORMAL(0),//正常
        PAUSE(1);//暂停
        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum LoginError{
        LOGINERRORNUM("登录的错误数"),
        LOGINERRORTIME("登录的错误时间");

        private String msg;
        private LoginError(String msg){
            this.msg = msg;
        }
        public String getMsg(){
            return msg;
        }
    }
}
