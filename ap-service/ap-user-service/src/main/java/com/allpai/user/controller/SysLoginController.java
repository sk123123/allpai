package com.allpai.user.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.allpai.common.utils.R;
import com.allpai.common.utils.ShiroUtils;
import com.allpai.entity.user.vo.SysLoginInVo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/19 0019 18:01
 * 后台登录
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController {
    @Autowired
    private Producer producer;

    /**
     * 处理验证码
     */
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.setUseCache(false);
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 登录
     */
//    @CrossOrigin(origins = "*")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R login(@RequestBody SysLoginInVo sysLoginInVo) throws IOException {
        String username = sysLoginInVo.getUsername();
        String password = sysLoginInVo.getPassword();
        String captcha = sysLoginInVo.getCaptcha();
//        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//        if (!captcha.equalsIgnoreCase(kaptcha)) {
//            return R.error("验证码不正确");
//        }
        try {
            Subject subject = ShiroUtils.getSubject();
            // sha256加密
            password = new Sha256Hash(password).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error(e.getMessage());
        } catch (LockedAccountException e) {
            return R.error(e.getMessage());
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }

        return R.ok();
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login.html";
    }
}
