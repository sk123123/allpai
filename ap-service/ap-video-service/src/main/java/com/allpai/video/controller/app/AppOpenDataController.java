package com.allpai.video.controller.app;

import com.allpai.common.utils.R;
import com.allpai.entity.video.vo.VideoHotListInVo;
import com.allpai.video.service.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 14:20
 * 开发的数据不一定需要用户登录
 */
public class AppOpenDataController {
    @Autowired
    private VideoInfoService videoInfoService;
//    @Autowired
//    private UserAdvertService userAdvertService;
//    @Autowired
//    private UserVideoRelationService userVideoRelationService;
//    @Autowired
//    private UserActivityService userActivityService;
//    @Autowired
//    private UserConfigService userConfigService;
//    @Autowired
//    private VideoTopicService videoTopicService;
//    @Autowired
//    private UserInfoService userInfoService;

    /**
     * 热门推荐列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/hotlist")
    public R hotlist(@RequestBody VideoHotListInVo videoHotListInVo, HttpServletRequest request){
        return videoInfoService.hotlist(videoHotListInVo, request);
    }

//    /**
//     * 启动广告
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/startPage")
//    public R startPage(@RequestBody StartPageInVo startPageInVo ){
//        return userAdvertService.startPage(startPageInVo);
//    }
//
//    /**
//     * 启动广告
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/addPage")
//    public R add(@RequestBody UserAdvertInVo userAdvertInVo ){
//        return userAdvertService.add(userAdvertInVo);
//    }
//
//    /**
//     * 视频转发记录
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/share")
//    public R share(@RequestBody VideoShareInVo videoShareInVo,HttpServletRequest request){
//        return userVideoRelationService.share(videoShareInVo, request);
//    }
//    /**
//     * 用户活动添加
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/add")
//    public R add(@RequestBody UserActivityAddOutVo userActivityAddInVo,HttpServletRequest request){
//        return userActivityService.add(userActivityAddInVo, request);
//    }
//
//    /**
//     * 用户活动信息(上下架)
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/queryUserActivity")
//    public Map<String,Object> queryUserActivity(HttpServletRequest request){
//        return userActivityService.queryUserActivity(request);
//    }
//
//    /**
//     * 获取视频识别数据
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/findVcaData")
//    public R findVcaData(@RequestBody VideoVcaInVo videoVcaInVo){
//        return videoInfoService.findVcaData(videoVcaInVo);
//    }
//
//    /**
//     * 匹配版本号(安卓)
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/updateVersion")
//    public R updateVersionNumber(HttpServletRequest request){
//        return userConfigService.updateVersionNumber(request);
//    }
//
//    /**
//     * 查找话题信息
//     */
//    @ResponseBody
//    @RequestMapping("/findTopicInfo")
//    public R findTopicInfo(@RequestBody VideoTopicFindInfoInVo videoTopicFindInfoInVo,HttpServletRequest request){
//        return videoTopicService.findTopicInfo(videoTopicFindInfoInVo, request);
//    }
//
//    /**
//     * 查找话题视频列表
//     * @param videoShareInVo
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/findTopicVideoList")
//    public R findTopicVideoList(@RequestBody VideoTopicFindListInVo videoTopicFindListInVo,HttpServletRequest request){
//        return videoInfoService.findTopicVideoList(videoTopicFindListInVo, request);
//    }
//
//    /**
//     * 主页搜索
//     * @param homePageSearchInVo
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/homePageSearch")
//    public R homePageSearch(@RequestBody HomePageSearchInVo homePageSearchInVo,HttpServletRequest request){
//        return videoInfoService.homePageSearch(homePageSearchInVo, request);
//    }
//    /**
//     * 主页搜索热词
//     * @param homePageSearchInVo
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/homePageHotSearch")
//    public R homePageHotSearch(@RequestBody HomePageHotSearchInVo homePageHotSearchInVo,HttpServletRequest request){
//        return videoInfoService.homePageHotSearch(homePageHotSearchInVo, request);
//    }
//
//    /**
//     * 话题分享
//     * @param topicShareNumInVo
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/topicShare")
//    public R topicShare(@RequestBody TopicShareNumInVo topicShareNumInVo,HttpServletRequest request){
//        return videoTopicService.topicShare(topicShareNumInVo, request);
//    }
//
//    /**
//     * 查询个人信息
//     */
//    @ResponseBody
//    @RequestMapping("/findUserInfo")
//    public R findUserInfo(@RequestBody UserFindInfoInVo userFindInfoInVo,HttpServletRequest request){
//        return userInfoService.findUserInfo(userFindInfoInVo,request);
//    }
//
//    /**
//     * 个人作品列表
//     */
//    @ResponseBody
//    @RequestMapping("/worklist")
//    public R worklist(@RequestBody VideoWorkListInVo videoWorkListInVo,HttpServletRequest request){
//        return videoInfoService.worklist(videoWorkListInVo,request);
//    }
//
//    /**
//     * 查找视频信息
//     * @param videoShareInVo
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/findVideoInfo")
//    public R findVideoInfo(@RequestBody VideoFindInfoInVo videoFindInfoInVo,HttpServletRequest request){
//        Long videoId = videoFindInfoInVo.getVideoId();
//        return videoInfoService.findVideoInfo(videoId, request);
//    }

}
