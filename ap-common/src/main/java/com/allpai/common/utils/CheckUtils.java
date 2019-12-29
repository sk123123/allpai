package com.allpai.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:23
 */
public class CheckUtils {
    /**
     * 检查手机号是否符合规范
     * @param tel true 符合   false 不符合
     * @return
     */
    public static boolean checkTel(String tel){
        if (Pattern.matches("^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$", tel)) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 验证QQ号码
     * @param QQ true 符合   false 不符合
     * @return
     */
    public static boolean checkQQ(String QQ) {
        String regex = "^[1-9][0-9]{4,} $";
        if (Pattern.matches(regex, QQ)) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 检查参数是否符合规范
     * @param param 参数
     * @param checkArray 检查的数组值
     * @return
     */
    public static boolean checkParam(String param,String[] checkArray){
        for(String str : checkArray){
            if(param.equals(str)){
                return true;
            }
        }
        return false;
    }

    /**
     * 检查邮箱号是否符合规范
     * @return
     */
    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }
}
