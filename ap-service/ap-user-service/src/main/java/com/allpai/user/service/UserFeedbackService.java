package com.allpai.user.service;

import com.allpai.common.utils.R;
import com.allpai.entity.user.UserFeedbackEntity;
import com.allpai.entity.user.vo.UserFeedbackAddInVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/29 0029 15:30
 * 用户反馈
 */
public interface UserFeedbackService {
    UserFeedbackEntity queryObject(Long feedbackId);

    List<UserFeedbackEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserFeedbackEntity userFeedback);

    void update(UserFeedbackEntity userFeedback);

    void delete(Long feedbackId);

    void deleteBatch(Long[] feedbackIds);

    R add(UserFeedbackAddInVo userFeedbackAddInVo, HttpServletRequest request );
}
