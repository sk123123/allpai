package com.allpai.user.controller;

import com.allpai.common.utils.R;
import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 21:53
 */
@RestController
@RequestMapping("user/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @ResponseBody
    @RequestMapping(value="/user/{id}")
    //测试
    public UserInfoEntity list(@PathVariable(value="id")Long id){
        System.out.println(R.ok(userInfoService.queryObject(id).toString()+"**************************************"));
        return userInfoService.queryObject(id);
    }
}
