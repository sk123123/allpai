//package com.allpai.user.config;
//
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//
//import org.apache.shiro.mgt.SecurityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * @author sunkai
// * @version 1.0
// * @date 2019/12/20 0020 12:08
// * shiro配置
// */
//@Configuration
//public class ShiroConfig {
//
//    @Bean
//    public UserRealm userRealm(){
//        UserRealm userRealm = new UserRealm();
//        return userRealm;
//    }
//    /**
//     * 权限管理,配置主要的realm的管理认证
//     * @return
//     */
//    @Bean
//    public SecurityManager securityManager(){
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 设置realm
//        securityManager.setRealm(userRealm());
//        return securityManager;
//    }
//
//    /**
//     * Filter工厂，设置对应的过滤条件和跳转条件
//     */
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 必须设置 SecurityManager
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
////        // 登录
////        shiroFilterFactoryBean.setLoginUrl("/login");
//        // 设置无权限时跳转的 ur
////        shiroFilterFactoryBean.setUnauthorizedUrl("/index");
//        // 设置拦截器
//        Map<String,String> map = new LinkedHashMap<String, String>();
//        // 普通用户
//        map.put("/user/**", "anon");
//        // 系统
//        map.put("/sys/login", "anon");
//        // 开放登陆接口
////        map.put("/login", "anon");
//        //验证码
//        map.put("/captcha.jpg", "anon");
//        // 登出
////        map.put("/logout","logout");
//        // 对所有用户认证
//        map.put("/**","authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//        System.out.println("Shiro拦截器工厂类注入成功");
//        return shiroFilterFactoryBean;
//        }
//
//    /**
//     * AOP式方法级权限检查
//     * @param securityManager
//     * @return
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//}
