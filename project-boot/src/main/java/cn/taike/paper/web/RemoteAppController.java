package cn.taike.paper.web;

import cn.taike.paper.context.ContextHandler;
import cn.taike.paper.exception.TokenErrorException;
import cn.taike.paper.protocol.ImageRecognitionResponse;
import cn.taike.paper.service.RecognitionSubmitService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huayadlly on 2017/8/13.
 */
@Slf4j
@RestController
public class RemoteAppController {

    @Autowired
    private RecognitionSubmitService recognitionSubmitService;

    // 处理app提交图片url
    @RequestMapping(value = "/paper/recognition/img", method = RequestMethod.POST)
    public Object submitImage(@RequestParam(value = "access_token") String token,
                              @RequestBody ImageRequest imageRequest) {
        try {
            Long userId = ContextHandler.exchangeToken(token);
            recognitionSubmitService.submit(userId, imageRequest);
            log.debug("app, submit token success.");

            return ResponseEntity.ok();
        } catch (TokenErrorException e) {
            log.error("app, token error exception.", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            log.error("app, submit recognition image error.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @Data
    public static class ImageRequest {
        private String imgUrl;
    }

    // python callback after send image
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object recognitionResult(@RequestBody ImageRecognitionResponse response) {
        try {
            recognitionSubmitService.saveRecognitionResponse(response);
            return ResponseEntity.ok();

        } catch (Exception e) {
            log.error("recognition, get recognition response error", e);
            return ResponseEntity.badRequest().build();
        }

    }


}
