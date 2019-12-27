package com.allpai.video.controller.app;

import com.allpai.common.utils.R;
import com.allpai.entity.video.vo.VideoTopicAddInVo;
import com.allpai.entity.video.vo.VideoTopicFindInfoOutVo;
import com.allpai.video.service.VideoTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 23:30
 * 短视频话题
 */
@RestController
@RequestMapping("app/video/topic")
public class AppVideoTopicController {
    @Autowired
    private VideoTopicService videoTopicService;

    /**
     * 添加话题
     */
    @ResponseBody
    @RequestMapping("/add")
    public R add(@RequestBody VideoTopicAddInVo videoTopicAddInVo, HttpServletRequest request){
        return videoTopicService.add(videoTopicAddInVo, request);
    }

    /**
     * 查找热门话题
     */
    @ResponseBody
    @RequestMapping("/hotTopicRank")
    public R hotTopicRank(HttpServletRequest request){
        return videoTopicService.hotTopicRank(request);
    }

    /**
     * 话题分享
     */
    @ResponseBody
    @RequestMapping("/videoTopicShare")
    public R videoTopicShare(@RequestBody VideoTopicFindInfoOutVo videoTopicFindInfoOutVo, HttpServletRequest request){
        return videoTopicService.videoTopicShare(videoTopicFindInfoOutVo, request);
    }
}
