package com.allpai.user.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.allpai.common.exception.ErrorCode;
import com.allpai.common.listener.OnlineUserListener;
import com.allpai.common.utils.*;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.vo.*;
import com.allpai.user.mapper.UserInfoMapper;
import com.allpai.user.service.UserInfoService;
import com.allpai.user.service.UserLockInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 21:51
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserNickNameJudgeServiceImpl userNickNameJudgeService;
    @Autowired
    private UserLockInfoService userLockInfoService;
    @Override
    public UserInfoEntity queryObject(Long userId) {
        return (UserInfoEntity) userInfoMapper.queryObject(userId);
    }

    @Override
    public List<UserInfoEntity> queryList(Map<String, Object> map) {
        return userInfoMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    public void save(UserInfoEntity userInfo) {
        userInfoMapper.save(userInfo);
    }

    @Override
    public void update(UserInfoEntity userInfo) {
        userInfoMapper.update(userInfo);
    }

    @Override
    public void delete(Long userId) {
        userInfoMapper.delete(userId);
    }

    @Override
    public void deleteBatch(Long[] userIds) {
        userInfoMapper.deleteBatch(userIds);
    }

    /**
     * 用户注册
     * @param userInfoRegInVo
     * @param request
     * @return
     */
    @Override
    public R regist(UserInfoRegInVo userInfoRegInVo, HttpServletRequest request) {
        String mobile = userInfoRegInVo.getMobile();
        String code = userInfoRegInVo.getCode();
        //密码
        if(StringUtils.isBlank(userInfoRegInVo.getPassword()))
            return R.error(ErrorCode.ParamInvalid.getCode(),"password参数不能空");
        String password =
                userInfoRegInVo.getPassword();
//        AESUtils.decryptData(userInfoRegInVo.getPassword());//base64解密
        if (StringUtils.isEmpty(password) || !Pattern.matches("^[a-z0-9A-Z]{8,16}$", password)) {
            return R.error(ErrorCode.InfoError.getCode(), "请输入8~16位字母或数字");
        }
        //验证码
        if (request.getSession().getAttribute(AppConstant.REG_CODE.toString()) == null) {
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.验证码错误.toString());
        }
        String sessionCode = (String) request.getSession().getAttribute(AppConstant.REG_CODE.toString());
        if(StringUtils.isEmpty(code) || !sessionCode.equals(code)) {
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.验证码错误.toString());
        }
        Date date = (Date) request.getSession().getAttribute(AppConstant.SEND_SMS_CODE_TIME.toString());
        if (DateUtils.miBetween(date, new Date()) > 10) {
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.验证码已过期.toString());
        }
        //手机号
        if(request.getSession().getAttribute(AppConstant.SEND_MOBILE.toString()) == null){
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.手机号为空.toString());
        }
        String sessionMobile = String.valueOf(request.getSession().getAttribute(AppConstant.SEND_MOBILE.toString()));
        if(!StringUtils.isEmpty(mobile)){
            if(!userInfoRegInVo.getMobile().equals(sessionMobile)){
                return R.error(ErrorCode.InfoError.getCode(), MsgInfo.验证的手机号不一致.toString());
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("mobile", userInfoRegInVo.getMobile());
        List<UserInfoEntity> userList = userInfoMapper.queryList(map);
        if(userList != null && userList.size() > 0){
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.该号码已注册.toString());
        }
        Map<String, Object> mapKey = userNickNameJudgeService.registKeyCodeJudge(userInfoRegInVo, request);
        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setMobile(mobile);
        userInfo.setPassword(DataUtils.getMd5(password));
        userInfo.setNickName(mapKey.get("nickName").toString());
        userInfo.setCreateTime(new Date());
        userInfo.setLastTime(new Date());
        userInfoMapper.save(userInfo);
        return R.ok(MsgInfo.成功.toString());
    }

    /**
     * 登录前验证码发送
     * @param sendCodeVo
     * @param request
     * @return
     */
    @Override
    public R sendCode(SendCodeInVo sendCodeVo, HttpServletRequest request) {
        if(StringUtils.isBlank(sendCodeVo.getMobile())) return R.error(ErrorCode.ParamInvalid.getCode(),"mobile参数不能空");
        if(StringUtils.isBlank(sendCodeVo.getType())) return R.error(ErrorCode.ParamInvalid.getCode(),"type参数不能空");

        HttpSession session = request.getSession();
        String mobile = sendCodeVo.getMobile();
        if (session.getAttribute(AppConstant.SEND_SMS_CODE_TIME.toString()) != null) {
            Date date = (Date) session.getAttribute(AppConstant.SEND_SMS_CODE_TIME.toString());
            if (DateUtils.miBetween(date, new Date()) < 1) {
                return R.error(ErrorCode.InfoError.getCode(), MsgInfo.请勿再一分钟内重复获取验证码.toString());
            }
        }

        if (!CheckUtils.checkTel(mobile)) {
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.手机号码格式不正确.toString());
        }

        UserInfoEntity dbUEntity = getSingUserInfoEntity(mobile, null, null,null,null);
        int smsType = 0;
        if(AppConstant.reg.toString().equals(sendCodeVo.getType())){
            if(dbUEntity != null)return R.error(ErrorCode.InfoError.getCode(), MsgInfo.该号码已注册.toString());
            smsType = 0;
        }else if(AppConstant.find.toString().equals(sendCodeVo.getType())){
            if(dbUEntity == null)return R.error(ErrorCode.InfoError.getCode(), MsgInfo.用户不存在.toString());
            smsType = 1;
        }else{
            return R.error(ErrorCode.ParamInvalid.getCode(),"type参数无效");
        }

        String code = DataUtils.getCode();
        try {
            SendSmsResponse smsRes = SmsUtils.sendSms(sendCodeVo.getMobile(), code,smsType);
            if(smsRes.getCode().equals("OK")){
                if(AppConstant.reg.toString().equals(sendCodeVo.getType())){
                    session.setAttribute(AppConstant.REG_CODE.toString(), code);
                }else if(AppConstant.find.toString().equals(sendCodeVo.getType())){
                    session.setAttribute(AppConstant.FIND_PWD_CODE.toString(), code);
                }
                session.setAttribute(AppConstant.SEND_MOBILE.toString(), mobile);
                session.setAttribute(AppConstant.SEND_SMS_CODE_TIME.toString(), new Date());

                return R.ok(MsgInfo.短信发送成功.toString());
            }else{
                return R.error(ErrorCode.InfoError.getCode(), MsgInfo.短信发送失败.toString());
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.短信发送失败.toString());
        }
    }

    @Override
    public R login(UserLoginInVo userLoginInVo, HttpServletRequest request) {
        if(StringUtils.isBlank(userLoginInVo.getPassword()))
            return R.error(ErrorCode.ParamInvalid.getCode(),"password参数不能为空");
        String password = userLoginInVo.getPassword();
//                AESUtils.decryptData(userLoginInVo.getPassword());
        if(password == null)
            return  R.error(ErrorCode.ParamInvalid.getCode(),"password参数无效");
        if(StringUtils.isBlank(userLoginInVo.getJpushId()))
            return  R.error(ErrorCode.ParamInvalid.getCode(),"jpushId参数不能为空");
        UserInfoEntity userEntity = getSingUserInfoEntity(userLoginInVo.getMobile(),null,null,null,null);
        if(userEntity == null ){
            return R.error(ErrorCode.InfoError.getCode(),MsgInfo.用户不存在.toString());
        }
        //判断是否锁定
        if(userLockInfoService.judgeIsLock(userEntity.getUserId())){
            return R.error(ErrorCode.InfoError.getCode(),"账号已被锁定,请24小时后再试");
        }
//DataUtils.getMd5(password)
        if(!userEntity.getPassword().equals(password)){
            int longErrorNum = getLoginError(request);
            longErrorNum ++ ;
            countLoginError(request, userEntity.getUserId(),longErrorNum);
            //记录错误数
            if(longErrorNum > 2){
                int num = 6 - longErrorNum;
                if( num == 0){
                    return R.error(ErrorCode.InfoError.getCode(),"账号已被锁定,请24小时后再试");
                }else{
                    return R.error(ErrorCode.InfoError.getCode(),"密码错误,再错" + num + "次，账号将被锁定");
                }
            }else{
                return R.error(ErrorCode.InfoError.getCode(),MsgInfo.用户名或密码错误.toString());
            }
        }

        UserLoginOutVo userInfo = new UserLoginOutVo();
        String token;
        if(StringUtils.isBlank(userEntity.getToken())){//是否是第一次登录
            userInfo.setFirst(true);
            token = userEntity.getToken();
        }else{
            userInfo.setFirst(false);
            token = DataUtils.getToken(userEntity.getUserId());
        }
        userEntity.setToken(token);
        userEntity.setLastTime(new Date());
        userEntity.setLoginTime(new Date());
        if(StringUtils.isBlank(userEntity.getUserNum()))
            userEntity.setUserNum(handleUserNum(userEntity.getUserId()));
        userInfoMapper.clearJpushId(userLoginInVo.getJpushId());
        userEntity.setJpushId(userLoginInVo.getJpushId());
        userEntity.setSource(getSource(request));
        userInfoMapper.update(userEntity);
        SessionUtils.setSessionUser(request, userEntity);
        BeanUtils.copyProperties(userEntity, userInfo);
//        userInfo.setGoodNum(getGoodNum(userInfo.getUserId()));
        //添加在线用户
        OnlineUserListener.addOnline(userInfo.getUserId(), request);
        request.getSession().setAttribute(Constant.LoginError.LOGINERRORNUM.toString(), 0);
        return R.ok().put(AppConstant.data.toString(),userInfo);
    }

    @Override
    public R authLogin(UserAuthLoginInVo userAuthLoginInVo, HttpServletRequest request) {
        return null;
    }

    @Override
    public R findPwd(UserFindPwdInVo userFindPwdInVo, HttpServletRequest request) {
        return null;
    }

    /**
     * 获取单个用户信息
     * @param mobile
     * @param qqId
     * @param wechatId
     * @param token
     * @param userNum
     * @return
     */
    @Override
    public UserInfoEntity getSingUserInfoEntity(String mobile, String qqId, String wechatId, String token, String userNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", 0);
        map.put("limit", 1);
        if(mobile != null) map.put("mobile", mobile);
        if(qqId != null) map.put("qqId", qqId);
        if(wechatId != null) map.put("wechatId", wechatId);
        if(token != null) map.put("token", token);
        if(userNum != null) map.put("userNum", userNum);
        List<UserInfoEntity> userList = userInfoMapper.queryList(map);
        if(userList != null && userList.size() > 0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public R modifyPwd(UserModifyPwdInVo userModifyPwdInVo, HttpServletRequest request) {
        return null;
    }

    @Override
    public R sendCodeInfoInVo(SendCodeInfoInVo sendCodeInfoInVo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        if(StringUtils.isBlank(sendCodeInfoInVo.getType())) return R.error(ErrorCode.ParamInvalid.getCode(),"type参数不能空");
        String code = DataUtils.getCode();
        try {
            SendSmsResponse smsRes = SmsUtils.sendSms(userInfoEntity.getMobile(), code,1);
            if(smsRes.getCode().equals("OK")){
                if(AppConstant.modify.toString().equals(sendCodeInfoInVo.getType())){
                    session.setAttribute(AppConstant.MODIFY_CODE.toString(), code);
                }
                session.setAttribute(AppConstant.SEND_SMS_CODE_TIME.toString(), new Date());
            }else{
                return R.error(ErrorCode.InfoError.getCode(), MsgInfo.短信发送失败.toString());
            }
            return R.ok(MsgInfo.短信发送成功.toString());
        } catch (ClientException e) {
            e.printStackTrace();
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.短信发送失败.toString());
        }
    }

    @Override
    public R updateInfo(UserInfoUpateInVo userInfoUpateInVo, HttpServletRequest request) {
        return null;
    }

    @Override
    public R findUserInfo(UserFindInfoInVo userFindInfoInVo, HttpServletRequest request) {
        return null;
    }

    @Override
    public void handleFanNum(Long userId, Long num) {

    }

    @Override
    public void handleLookNum(Long userId, Long num) {

    }

    @Override
    public R looklist(UserLookListInVo userLookListInVo, HttpServletRequest request) {
        return null;
    }

    @Override
    public R exit(HttpServletRequest request) {
        return null;
    }

    @Override
    public R blacklist(PageListInVo userBackListInVo, HttpServletRequest request) {
        return null;
    }

    @Override
    public R deleteBlackListInfo(UserDeleteBlackListInfoInVo userDeleteBlackListInfoInVo, HttpServletRequest request) {
        return null;
    }

    /**
     * 统计登录出错的次数
     */
    public void countLoginError(HttpServletRequest request,Long userId,int longErrorNum){
        request.getSession().setAttribute(Constant.LoginError.LOGINERRORNUM.toString(), longErrorNum);
        if(longErrorNum > 5){
            userLockInfoService.saveLoginError(userId);
            request.getSession().setAttribute(Constant.LoginError.LOGINERRORNUM.toString(), 0);
        }
    }

    /**
     * 获取登录出错的次数
     */
    public int getLoginError(HttpServletRequest request){
        int longErrorNum = 0;
        if(request.getSession().getAttribute(Constant.LoginError.LOGINERRORNUM.toString()) != null)
            longErrorNum =	(int) request.getSession().getAttribute(Constant.LoginError.LOGINERRORNUM.toString());
        return longErrorNum;
    }
    /**
     * 处理APP的来源
     */
    public int getSource(HttpServletRequest request){
        String  app_type = "ios";
//                request.getHeader("app_type");
        if(app_type.equals("android")){
            return 1;
        }else if(app_type.equals("ios")){
            return 2;
        }
        return 0;
    }
    /**
     * 处理平台ID
     * @return
     */
    public String handleUserNum(Long userId){
        return 100000 + userId + "";
    }

    /**
     * 返回视频点赞数
     */
//    public Long getGoodNum(Long userId){
//        //视频点赞
//        Long goodNum = videoInfoService.getGoogNumTotal(userId);
//        return goodNum;
//    }
}
