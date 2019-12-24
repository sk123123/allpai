package com.allpai.video.controller;

import com.allpai.common.utils.PageUtils;
import com.allpai.common.utils.R;
import com.allpai.entity.VideoInfoEntity;
import com.allpai.entity.vo.PageListInVo;
import com.allpai.video.service.VideoInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 14:00
 * 短视频信息
 */
@RestController
@RequestMapping("video/videoinfo")
public class VideoInfoController {
    @Autowired
    private VideoInfoService videoInfoService;

    @RequestMapping("/videoinfo.html")
    public String list(){
        return "video/videoinfo.html";
    }

    @RequestMapping("/videoinfo_add.html")
    public String add(){
        return "video/videoinfo_add.html";
    }
    @ResponseBody
    @RequestMapping(value="/{id}")
    //测试
    public VideoInfoEntity list(@PathVariable(value="id")Long id){
        System.out.println(R.ok(videoInfoService.queryObject(id).toString()+"**************************************"));
        return videoInfoService.queryObject(id);
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("videoinfo:list")
    public R list(@RequestBody PageListInVo pageListInVo){
        Integer page = pageListInVo.getPage();
        Integer limit = pageListInVo.getLimit();
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据
        List<VideoInfoEntity> videoInfoList = videoInfoService.queryList(map);
        int total = videoInfoService.queryTotal(map);

        PageUtils pageUtil = new PageUtils(videoInfoList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{videoId}")
    @RequiresPermissions("videoinfo:info")
    public VideoInfoEntity info(@PathVariable("videoId") Long videoId){
        VideoInfoEntity videoInfo = videoInfoService.queryObject(videoId);

//        return R.ok().put("videoInfo", videoInfo);
        return videoInfo;
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("videoinfo:save")
    public R save(@RequestBody VideoInfoEntity videoInfo){
        videoInfoService.save(videoInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("videoinfo:update")
    public R update(@RequestBody VideoInfoEntity videoInfo){
        videoInfoService.update(videoInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions("videoinfo:delete")
    public R delete(@RequestBody Long[] videoIds){
        videoInfoService.deleteBatch(videoIds);

        return R.ok();
    }
}
