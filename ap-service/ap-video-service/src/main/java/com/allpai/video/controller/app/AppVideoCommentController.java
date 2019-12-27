package com.allpai.video.controller.app;

import com.allpai.common.utils.R;
import com.allpai.video.mapper.VideoCommentMapper;
import com.allpai.video.service.VideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 15:06
 * 短视频评论
 */
@RestController
@RequestMapping("app/video/comment")
public class AppVideoCommentController {
    @Autowired
    private VideoCommentService videoCommentService;
    @Autowired
    private VideoCommentMapper videoCommentMapper;
//    @Autowired
//    private UserCommentRelationService userCommentRelationService;
    /**
     * 评论更新数
     * @return
     */
    @ResponseBody
    @RequestMapping("/findCommentNoReadNum/{userId}")
    public int findCommentNoReadNum(@PathVariable(value = "userId")Long userId){
        return videoCommentMapper.findCommentNoReadNum(userId);
    }
}
