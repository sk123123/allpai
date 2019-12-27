package com.allpai.user.mapper;

import com.allpai.entity.user.dto.UserInfoLookDto;
import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.user.dto.UserInfoOneDto;
import com.allpai.entity.user.dto.UserInfoSelfDto;
import com.allpai.entity.user.dto.UserSearchDto;
import com.allpai.entity.user.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 用户信息表
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 21:47
 */
@Mapper
public interface UserInfoMapper extends BaseMapper {
    void saveBase(UserInfoEntity userInfoEntity);

    void saveAuth(UserInfoEntity userInfoEntity);

    List<UserInfoLookDto> queryLookList(Map<String, Object> map);

    int queryLookTotal(Map<String, Object> map);

    List<UserInfoLookDto> querybackList(Map<String, Object> map);

    int querybackTotal(Map<String, Object> map);

    int clearJpushId(String jpushId);

    String queryJpushId(Long userId);

    UserInfoSelfDto queryUserInfoSelf(Long userId);

    UserInfoOneDto queryUserInfoOne(Long userId);

    List<UserSearchDto> queryUserSearch(Map<String, Object> map);

    int queryUserSearchTotal(String content);

    UserInfoEntity queryNumber(Object id);
}
