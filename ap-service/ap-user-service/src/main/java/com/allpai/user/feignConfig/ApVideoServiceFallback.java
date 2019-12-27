package com.allpai.user.feignConfig;

import com.allpai.entity.video.VideoInfoEntity;
import com.allpai.entity.video.vo.VideoHotnumInVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/17 0017 17:41
 */
@Component
public class ApVideoServiceFallback implements FallbackFactory<VideoFeignClient> {
    Map map = new HashMap<String , String>(){{
        put("code", "0");
        put("msg","ap-video-service服务异常");
    }};
    @Override
    public VideoFeignClient create(Throwable throwable) {
        return new VideoFeignClient() {
            @Override
            public VideoInfoEntity queryObject(Long videoId) {
                return null;
            }

            @Override
            public Long getGoogNumTotal(Long userId) {
                return null;
            }

            @Override
            public int findCommentNoReadNum(Long userId) {
                return 0;
            }

            @Override
            public void updateVideoHotnum(VideoHotnumInVo videoHotnumInVo) {

            }
        };
    }
}
