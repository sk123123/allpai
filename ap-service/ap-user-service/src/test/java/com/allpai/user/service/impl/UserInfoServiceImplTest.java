package com.allpai.user.service.impl;

import com.allpai.user.ApUserApplication;
import com.allpai.entity.vo.UserInfoRegInVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/11 0011 11:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={ApUserApplication.class,UserInfoServiceImplTest.class})
public class UserInfoServiceImplTest {
    @Autowired
    private UserInfoServiceImpl userInfoService;

    private MockHttpServletRequest request;

    @Test
    public void regist() {
        String mobile = "132712344321";
        String code = "353561";
        String password = "sk123123";
        UserInfoRegInVo userInfoRegInVo = new UserInfoRegInVo();
        userInfoRegInVo.setCode(code);
        userInfoRegInVo.setMobile(mobile);
        userInfoRegInVo.setPassword(password);
//        HttpServletRequest request = new HttpServletRequestWrapper();
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        userInfoService.regist(userInfoRegInVo,request);
    }
}