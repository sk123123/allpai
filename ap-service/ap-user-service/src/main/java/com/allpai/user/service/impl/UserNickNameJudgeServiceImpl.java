package com.allpai.user.service.impl;

import com.allpai.entity.vo.UserAuthLoginInVo;
import com.allpai.entity.vo.UserInfoRegInVo;
import com.allpai.entity.vo.UserInfoUpateInVo;
import com.allpai.user.service.UserNickNameJudgeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/11 0011 10:51
 * 用户昵称
 */
@Service("userNickNameJudgeService")
public class UserNickNameJudgeServiceImpl implements UserNickNameJudgeService {
    private String [] keyCode = {"凡拍","客服","方圆","方圆百里","官方"};
    @Override
    public Map<String, Object> registKeyCodeJudge(UserInfoRegInVo userInfoRegInVo, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("nickName", getRandom());
        return map;
    }

    @Override
    public Map<String, Object> authLogKeyCodeJudge(UserAuthLoginInVo userAuthLoginInVo, HttpServletRequest request) {
        Map<String,Object> mapName = new HashMap<String,Object>();
        String nickName = userAuthLoginInVo.getNickName();
        for(int i=0;i<keyCode.length;i++){
            if(nickName.contains(keyCode[i])){
                mapName.put("nickName", getRandom());
                return mapName;
            }
        }
        mapName.put("nickName", nickName);
        return mapName;
    }

    @Override
    public Map<String, Object> updateKeyCodeJudge(UserInfoUpateInVo userInfoUpateInVo, HttpServletRequest request) {
        Map<String,Object> mapName = new HashMap<String,Object>();
        String nickName = userInfoUpateInVo.getNickName();
        for(int i=0;i<keyCode.length;i++){
            if(nickName.contains(keyCode[i])){
                mapName.put("nickName", false);
                //"凡拍","客服","方圆","方圆百里","官方"
                if(nickName.contains("凡拍")) {
                    mapName.put("msg", "您的昵称存在'凡拍'敏感字符，请重新输入！");
                    return mapName;
                }else if(nickName.contains("客服")== true) {
                    mapName.put("msg", "您的昵称存在'客服'敏感字符，请重新输入！");
                    return mapName;
                }else if(nickName.contains("方圆")) {
                    mapName.put("msg", "您的昵称存在'方圆'敏感字符，请重新输入！");
                    return mapName;
                }else if(nickName.contains("方圆百里")) {
                    mapName.put("msg", "您的昵称存在'方圆百里'敏感字符，请重新输入！");
                    return mapName;
                }else {
                    mapName.put("msg", "您的昵称存在'官方'敏感字符，请重新输入！");
                    return mapName;
                }
//				mapName.put("msg","您的昵称存在敏感字符，请重新输入！");
//				return mapName;
            }
        }
        mapName.put("nickName", true);
        mapName.put("msg", nickName);
        return mapName;
    }

    //商家入驻用户昵称修改
//    public Map<String, Object> registKeyCode(MerchantUserRegInVo merchantUserRegInVo, HttpServletRequest request) {
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("nickName", getRandom());
//        return map;
//    }

    /**
     * 随机生成6位数字（用户_123456）
     */
    private static String getRandom(){
        String sources = "1234567890";
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 6; j++)
        {
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        String nickName = "用户_"+ flag.toString();
        return nickName;
    }
}
