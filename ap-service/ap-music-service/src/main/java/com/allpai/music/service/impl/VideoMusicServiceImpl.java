package com.allpai.music.service.impl;

import com.allpai.common.exception.ErrorCode;
import com.allpai.common.utils.MsgInfo;
import com.allpai.common.utils.PageUtils;
import com.allpai.common.utils.R;
import com.allpai.common.utils.SessionUtils;
import com.allpai.entity.VideoMusicEntity;
import com.allpai.entity.dto.VideoMusicDto;
import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.vo.MusicListInVo;
import com.allpai.entity.vo.MusicSearchInVo;
import com.allpai.entity.vo.MusicUploadInVo;
import com.allpai.music.mapper.VideoMusicMapper;
import com.allpai.music.service.VideoMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/14 0014 16:22
 * 视频音乐
 */
@Service("videoMusicService")
public class VideoMusicServiceImpl implements VideoMusicService {
    @Autowired
    private VideoMusicMapper videoMusicMapper;
    @Override
    public VideoMusicEntity queryObject(Long musicId) {
        return null;
    }

    @Override
    public List<VideoMusicEntity> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    public void save(VideoMusicEntity videoMusic) {

    }

    @Override
    public void update(VideoMusicEntity videoMusic) {

    }

    @Override
    public void delete(Long musicId) {

    }

    @Override
    public void deleteBatch(Long[] musicIds) {

    }

    /**
     * 音乐列表
     * @param videoMusicListInVo
     * @param request
     * @return
     */
    @Override
    public R musicList(MusicListInVo videoMusicListInVo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Integer limit = videoMusicListInVo.getLimit();
        Integer page = videoMusicListInVo.getPage();
        Integer styleType = videoMusicListInVo.getStyleType();
        if(styleType == 0) styleType = 1;
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);
        map.put("styleType", styleType);
        if(isCheckIng(request))map.put("musicCheck", 1);
        List<VideoMusicDto> list = videoMusicMapper.queryMusicList(map);
        int total = videoMusicMapper.queryTotal(map);
        PageUtils pageUtil = new PageUtils(list, total, limit, page);
        return R.ok().put("page", pageUtil);
    }

    @Override
    public R musicSearch(MusicSearchInVo musicSearchInVo, HttpServletRequest request) {
        return null;
    }


    /**
     * 音乐上传
     * @param musicUploadInVo
     * @param request
     * @return
     */
    @Override
    public R musicUpload(MusicUploadInVo musicUploadInVo, HttpServletRequest request) {
        Integer uploadType = musicUploadInVo.getUploadType();
        if(uploadType == null)return R.error(ErrorCode.ParamInvalid.getCode(),"uploadType参数不能空");
        VideoMusicEntity videoMusicEntity = new VideoMusicEntity();
        if(uploadType == 0){
            videoMusicEntity.setUserId(0L);
        }else if( uploadType == 1){
            SysMenuEntity.UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
            videoMusicEntity.setUserId(userInfoEntity.getUserId());
        }else{
            return R.error(ErrorCode.ParamInvalid.getCode(),"uploadType参数不正确");
        }
        videoMusicEntity.setCoverUrl(musicUploadInVo.getCoverUrl());
        videoMusicEntity.setCreateTime(new Date());
        videoMusicEntity.setMusicName(musicUploadInVo.getMusicName());
        videoMusicEntity.setMusicUrl(musicUploadInVo.getMusicUrl());
        videoMusicEntity.setSingerName(musicUploadInVo.getSingerName());
        videoMusicEntity.setStyleType(musicUploadInVo.getStyleType());
        videoMusicEntity.setTimeLength(musicUploadInVo.getTimeLength());
        videoMusicEntity.setRang(0.0);
        videoMusicEntity.setLyric("");
        videoMusicMapper.save(videoMusicEntity);
        return R.ok(MsgInfo.成功.toString());
    }
    /**
     * 检查APP版本是否在审核中 true:是在审核中， false:不在
     * @param request
     * @return
     */
    public boolean isCheckIng(HttpServletRequest request){
        String checkIng  = "1.1";
        String  version = request.getHeader("version");
        version = version.trim();
        String  app_type = request.getHeader("app_type");
        try {
            if(app_type.equals("ios")){
                if(checkIng.equals(version) ) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
