package com.allpai.user.service.impl;

import com.allpai.common.exception.ErrorCode;
import com.allpai.common.utils.CheckUtils;
import com.allpai.common.utils.MsgInfo;
import com.allpai.common.utils.R;
import com.allpai.common.utils.SessionUtils;
import com.allpai.entity.user.UserFeedbackEntity;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.user.vo.UserFeedbackAddInVo;
import com.allpai.user.mapper.UserFeedbackMapper;
import com.allpai.user.service.UserFeedbackService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/29 0029 15:38
 */
@Service("userFeedbackService")
public class UserFeedbackServiceImpl implements UserFeedbackService {
    @Autowired
    private UserFeedbackMapper userFeedbackMapper;

    @Override
    public UserFeedbackEntity queryObject(Long feedbackId){
        return userFeedbackMapper.queryObject(feedbackId);
    }

    @Override
    public List<UserFeedbackEntity> queryList(Map<String, Object> map){
        return userFeedbackMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map){
        return userFeedbackMapper.queryTotal(map);
    }

    @Override
    public void save(UserFeedbackEntity userFeedback){
        userFeedbackMapper.save(userFeedback);
    }

    @Override
    public void update(UserFeedbackEntity userFeedback){
        userFeedbackMapper.update(userFeedback);
    }

    @Override
    public void delete(Long feedbackId){
        userFeedbackMapper.delete(feedbackId);
    }

    @Override
    public void deleteBatch(Long[] feedbackIds){
        userFeedbackMapper.deleteBatch(feedbackIds);
    }

    /**
     * 新增用户反馈
     * @param userFeedbackAddInVo
     * @param request
     * @return
     */
    @Override
    public R add(UserFeedbackAddInVo userFeedbackAddInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        if(userInfoEntity == null) return R.error(ErrorCode.TokenInvalid.getMsg());
        Long userId = userInfoEntity.getUserId();
        String content = userFeedbackAddInVo.getContent();
        String contact = userFeedbackAddInVo.getContact();
        //图片路径处理
        String imgPath = userFeedbackAddInVo.getImgPath();
        UserFeedbackEntity userFeedbackEntity = new UserFeedbackEntity();
        if(imgPath == null || imgPath.equals("")){
            userFeedbackEntity.setImgPath(imgPath);
        }else{
            String[] path = imgPath.split(",",-1);
            if(path.length>5)return R.error(ErrorCode.InfoError.getCode(),"超出上传图片数量");
            userFeedbackEntity.setImgPath(imgPath);
        }
        if(userId == null)return R.error(ErrorCode.TokenInvalid.getCode(),"userid参数不能为空");
        if(StringUtils.isBlank(content)){
            return R.error(ErrorCode.InfoError.getCode(),"反馈内容不能为空");
        }else if(content.length()>200){
            return R.error(ErrorCode.InfoError.getCode(),"反馈内容超过输出范围,请您重新输入!");
        }else {
            userFeedbackEntity.setContent(content);
        }
        userFeedbackEntity.setUserId(userId);
        if(contact.length()==11){
            if(!CheckUtils.checkTel(contact)){
                return R.error(ErrorCode.InfoError.getCode(),"手机无效,请您重新输入!");
            }
        }else{
            if(!CheckUtils.checkQQ(contact)){
                return R.error(ErrorCode.InfoError.getCode(),"联系方式有误,请您重新输入!");
            }
        }
        userFeedbackEntity.setContact(contact);
        userFeedbackEntity.setCreateTime(new Date());
        userFeedbackMapper.save(userFeedbackEntity);
        return R.ok(MsgInfo.成功.toString());
    }
}
