package com.allpai.user.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.allpai.common.exception.ErrorCode;
import com.allpai.common.listener.OnlineUserListener;
import com.allpai.common.utils.*;
import com.allpai.entity.common.vo.SendCodeInVo;
import com.allpai.entity.common.vo.SendCodeInfoInVo;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.user.UserUserRelationEntity;
import com.allpai.entity.user.dto.UserInfoOneDto;
import com.allpai.entity.user.dto.UserInfoSelfDto;
import com.allpai.entity.user.vo.*;
import com.allpai.user.feignConfig.VideoFeignClient;
import com.allpai.user.mapper.UserInfoMapper;
import com.allpai.user.service.UserInfoService;
import com.allpai.user.service.UserLockInfoService;
import com.allpai.user.service.UserUserRelationService;
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
    @Autowired
    private VideoFeignClient videoFeignClient;
    @Autowired
    private UserUserRelationService userUserRelationService;

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

    /**
     * 三方授权登录
     * @param userAuthLoginInVo
     * @param request
     * @return
     */
    @Override
    public R authLogin(UserAuthLoginInVo userAuthLoginInVo, HttpServletRequest request) {
        UserInfoEntity userEntity  = new UserInfoEntity();
        UserInfoEntity dbUserEntity ;
        String type = userAuthLoginInVo.getType();
        String authKey = userAuthLoginInVo.getAuthKey();
        String jpushId = userAuthLoginInVo.getJpushId();
        boolean isFirst = true;

        if(StringUtils.isBlank(type)) return R.error(ErrorCode.ParamInvalid.getCode(),"type参数不能空");
        if(StringUtils.isBlank(authKey)) return R.error(ErrorCode.ParamInvalid.getCode(),"authKey参数不能空");
        if(StringUtils.isBlank(jpushId)) return  R.error(ErrorCode.ParamInvalid.getCode(),"jpushId参数不能为空");
        if(type.equals("qq")){
            dbUserEntity = getSingUserInfoEntity(null, userAuthLoginInVo.getAuthKey(), null,null,null);
            if(dbUserEntity != null){
                dbUserEntity.setQqId(userAuthLoginInVo.getAuthKey());
                isFirst = false;
            }else{
                userEntity.setQqId(userAuthLoginInVo.getAuthKey());
            }

        }else if(type.equals("wechat")){
            dbUserEntity = getSingUserInfoEntity(null, null, userAuthLoginInVo.getAuthKey(),null,null);
            if(dbUserEntity != null){
                dbUserEntity.setWechatId(userAuthLoginInVo.getAuthKey());
                isFirst = false;
            }else{
                userEntity.setWechatId(userAuthLoginInVo.getAuthKey());
            }
        }else{
            return R.error(ErrorCode.ParamInvalid.getCode(),"type参数不正确");
        }
        if(isFirst){
            if(StringUtils.isBlank(userAuthLoginInVo.getNickName()))
                return R.error(ErrorCode.ParamInvalid.getCode(),"nickName参数不能空");
            Map<String,Object> map = userNickNameJudgeService.authLogKeyCodeJudge(userAuthLoginInVo, request);
            String token = DataUtils.getToken(userAuthLoginInVo.getAuthKey());

            userEntity.setNickName(map.get("nickName").toString());
            userEntity.setSex(userAuthLoginInVo.getSex());
            userEntity.setCreateTime(new Date());
            userEntity.setLastTime(new Date());
            userEntity.setToken(token);
            userEntity.setLoginTime(new Date());
            userInfoMapper.saveAuth(userEntity);
            if(type.equals("qq")){
                userEntity = getSingUserInfoEntity(null, userAuthLoginInVo.getAuthKey(), null,null,null);
            }else if(type.equals("wechat")){
                userEntity = getSingUserInfoEntity(null, null, userAuthLoginInVo.getAuthKey(),null,null);
            }
            if(StringUtils.isBlank(userEntity.getUserNum()))
                userEntity.setUserNum(handleUserNum(userEntity.getUserId()));
            userInfoMapper.clearJpushId(userAuthLoginInVo.getJpushId());
            userEntity.setJpushId(userAuthLoginInVo.getJpushId());
            userEntity.setSource(getSource(request));
            userInfoMapper.update(userEntity);
            SessionUtils.setSessionUser(request, userEntity);
        }else{
            String token = DataUtils.getToken(userEntity.getUserId());
            dbUserEntity.setToken(token);
            dbUserEntity.setLastTime(new Date());
            dbUserEntity.setLoginTime(new Date());
            if(StringUtils.isBlank(dbUserEntity.getUserNum()))dbUserEntity.setUserNum(handleUserNum(dbUserEntity.getUserId()));
            userInfoMapper.clearJpushId(userAuthLoginInVo.getJpushId());
            userEntity.setJpushId(userAuthLoginInVo.getJpushId());
            userEntity.setSource(getSource(request));
            userInfoMapper.update(dbUserEntity);
            SessionUtils.setSessionUser(request, userEntity);
        }
        UserLoginOutVo outUser = new UserLoginOutVo();
        if(isFirst){
            BeanUtils.copyProperties(userEntity, outUser);
        }else{
            BeanUtils.copyProperties(dbUserEntity, outUser);
        }
        outUser.setFirst(isFirst);
        outUser.setGoodNum(videoFeignClient.getGoogNumTotal(outUser.getUserId()));

        //添加在线用户
        OnlineUserListener.addOnline(outUser.getUserId(), request);
        return R.ok().put(AppConstant.data.toString(),outUser);
    }
    /**
     * 找回密码
     * @param userFindPwdInVo
     * @param request
     * @return
     */
    @Override
    public R findPwd(UserFindPwdInVo userFindPwdInVo, HttpServletRequest request) {
        String mobile = userFindPwdInVo.getMobile();//手机号
        String code = userFindPwdInVo.getCode();
        String password = userFindPwdInVo.getPassword();
        if(StringUtils.isBlank(mobile)) return R.error(ErrorCode.ParamInvalid.getCode(),"mobile参数不能空");
        if(StringUtils.isBlank(code)) return R.error(ErrorCode.ParamInvalid.getCode(),"code参数不能空");
        if(StringUtils.isBlank(password)) return R.error(ErrorCode.ParamInvalid.getCode(),"password参数不能空");
        //解密后的密码
        password = AESUtils.decryptData(password);
        if(password == null) return  R.error(ErrorCode.ParamInvalid.getCode(),"password参数无效");

        String sessionCode = (String) request.getSession().getAttribute(AppConstant.FIND_PWD_CODE.toString());
        if(!sessionCode.equals(code)){
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.验证码错误.toString());
        }

        Date date = (Date) request.getSession().getAttribute(AppConstant.SEND_SMS_CODE_TIME.toString());
        if (DateUtils.miBetween(date, new Date()) > 10) {
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.验证码已过期.toString());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        List<UserInfoEntity> userList = userInfoMapper.queryList(map);
        if(userList != null && userList.size() > 1){
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.用户不存在.toString());
        }
        UserInfoEntity userEntity = userList.get(0);
        userEntity.setPassword(DataUtils.getMd5(password));
        userEntity.setLastTime(new Date());
        userInfoMapper.update(userEntity);
        return R.ok(MsgInfo.成功.toString());
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

    /**
     * 用户修改密码
     * @param userModifyPwdInVo
     * @param request
     * @return
     */
    @Override
    public R modifyPwd(UserModifyPwdInVo userModifyPwdInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        String code = userModifyPwdInVo.getCode();
        String oldPassWord = userModifyPwdInVo.getOldPassWord();
        String password = userModifyPwdInVo.getPassword();

        if(StringUtils.isBlank(code)) return R.error(ErrorCode.ParamInvalid.getCode(),"code参数不能空");
        if (request.getSession().getAttribute(AppConstant.MODIFY_CODE.toString()) == null)
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.验证码错误.toString());
        String sessionCode = (String) request.getSession().getAttribute(AppConstant.MODIFY_CODE.toString());
        if(!sessionCode.equals(code))
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.验证码错误.toString());

        if(StringUtils.isBlank(password)) return R.error(ErrorCode.ParamInvalid.getCode(),"password参数不能空");
        password = AESUtils.decryptData(password);
        if(password == null) return  R.error(ErrorCode.ParamInvalid.getCode(),"password参数无效");

        if(StringUtils.isBlank(oldPassWord)) return R.error(ErrorCode.ParamInvalid.getCode(),"oldPassWord参数不能空");
        oldPassWord = AESUtils.decryptData(oldPassWord);
        if(oldPassWord == null) return  R.error(ErrorCode.ParamInvalid.getCode(),"oldPassWord参数无效");

        Date date = (Date) request.getSession().getAttribute(AppConstant.SEND_SMS_CODE_TIME.toString());
        if (DateUtils.miBetween(date, new Date()) > 10) {
            return R.error(ErrorCode.InfoError.getCode(), MsgInfo.验证码已过期.toString());
        }

        //判断旧密码是否相等
        if(!DataUtils.getMd5(oldPassWord).equals(userInfoEntity.getPassword())){
            return R.error(ErrorCode.InfoError.getCode(), "旧密码不正确");
        }
        userInfoEntity.setPassword(DataUtils.getMd5(password));
        userInfoMapper.update(userInfoEntity);
        return R.ok(MsgInfo.成功.toString());
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

    /**
     * 用户信息更新
     * @param userInfoUpateInVo
     * @param request
     * @return
     */
    @Override
    public R updateInfo(UserInfoUpateInVo userInfoUpateInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        try {
            if(StringUtils.isBlank(userInfoUpateInVo.getNickName()) || userInfoUpateInVo.getNickName().equals("")) {
                R.error(ErrorCode.InfoError.getCode(),"用户昵称不能为空！");
            }else{
                Map<String, Object> mapKey = userNickNameJudgeService.updateKeyCodeJudge(userInfoUpateInVo, request);
                boolean falg = (boolean)mapKey.get("nickName");
                if(!falg)return R.error(ErrorCode.InfoError.getCode(),(String)mapKey.get("msg"));
                userInfoEntity.setNickName(mapKey.get("msg").toString());
            }
            if(userInfoUpateInVo.getSex() != null)
                userInfoEntity.setSex(userInfoUpateInVo.getSex());
            if(!StringUtils.isBlank(userInfoUpateInVo.getBirthday()))
                userInfoEntity.setBirthday(DateUtils.parseDay(userInfoUpateInVo.getBirthday()));
            if(userInfoUpateInVo.getSign() != null)
                userInfoEntity.setSign(userInfoUpateInVo.getSign());
            String address = CityUtils.getCity(userInfoUpateInVo.getAddress());
            if(!StringUtils.isBlank(address))userInfoEntity.setAddress(address);
            if(!StringUtils.isBlank(userInfoUpateInVo.getHeadUrl()))userInfoEntity.setHeadUrl(userInfoUpateInVo.getHeadUrl());
            userInfoMapper.update(userInfoEntity);
            SessionUtils.setSessionUser(request, userInfoEntity);
            return R.ok(MsgInfo.成功.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(ErrorCode.InsideError);
        }
    }

    /**
     * 查询个人信息
     * @param userFindInfoInVo
     * @param request
     * @return
     */
    @Override
    public R findUserInfo(UserFindInfoInVo userFindInfoInVo, HttpServletRequest request) {
        boolean isMySelf = false;//true 自己  false 他人
        boolean isLogin = true; //true 已登录  false未登录
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long toUserId = userFindInfoInVo.getUserId();
        Long userId = null;
        if(userInfoEntity == null  || userInfoEntity.getUserId() == null){
            isLogin = false;
        }else{
            userId = userInfoEntity.getUserId();
        }

        if(toUserId == null){
            isMySelf = true;
        }
        if(isMySelf){
            if(!isLogin)return R.error(ErrorCode.TokenInvalid);
            //获取自己的信息
            UserInfoSelfDto userInfo = userInfoMapper.queryUserInfoSelf(userId);
            userInfo.setCommentNew(videoFeignClient.findCommentNoReadNum(userId));
            userInfo.setGoodNum(videoFeignClient.getGoogNumTotal(userId));
            return R.ok().put(AppConstant.data.toString(),userInfo);
        }else{
            //个人中心访问他人用户信息
            UserOtherOutVo userOtherOutVo = new UserOtherOutVo();
            UserInfoOneDto userOneDto = userInfoMapper.queryUserInfoOne(toUserId);
            BeanUtils.copyProperties(userOneDto, userOtherOutVo);
            userOtherOutVo.setGoodNum(videoFeignClient.getGoogNumTotal(userId));
            userOtherOutVo.setAttent(userUserRelationService.checkRelation(userId, toUserId,1));

            Integer source = userFindInfoInVo.getSource();
            if(source != null && source == 1){
                //从关注进入他人个人中心,记录时间
                Integer relationType = 1;
                UserUserRelationEntity userRelationEntity = userUserRelationService.findRelation(userId, toUserId, relationType);
                if(userRelationEntity != null){
                    userRelationEntity.setLastTime(new Date());
                    userUserRelationService.update(userRelationEntity);
                }
            }
            return R.ok().put(AppConstant.data.toString(),userOtherOutVo);
        }
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
