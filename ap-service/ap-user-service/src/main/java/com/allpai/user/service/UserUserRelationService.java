package com.allpai.user.service;

import com.allpai.common.utils.R;
import com.allpai.entity.user.UserUserRelationEntity;
import com.allpai.entity.user.vo.UserAddBlackInVo;
import com.allpai.entity.user.vo.UserAttentInVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 15:19
 */
public interface UserUserRelationService {
    UserUserRelationEntity queryObject(Long userUserId);

    List<UserUserRelationEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserUserRelationEntity userUserRelation);

    void update(UserUserRelationEntity userUserRelation);

    void delete(Long userUserId);

    void deleteBatch(Long[] userUserIds);

    boolean checkRelation(Long userId,Long toUserId,Integer type);

    R attentUser(UserAttentInVo userAttentInVo, HttpServletRequest request);

    R addBlack(UserAddBlackInVo userAddBlackInVo, HttpServletRequest request);

    UserUserRelationEntity findRelation(Long userId, Long toUserId,Integer type);
}
