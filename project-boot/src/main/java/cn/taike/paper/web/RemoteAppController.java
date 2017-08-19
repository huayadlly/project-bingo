package cn.taike.paper.web;

import cn.taike.paper.handler.UserTokenHandler;
import cn.taike.bingo.exception.IllegalUserTokenException;
import cn.taike.paper.protocol.ImageRecognitionResponse;
import cn.taike.paper.service.RecognitionSubmitService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huayadlly on 2017/8/13.
 */
@Slf4j
@RestController
public class RemoteAppController {

    @Autowired
    private RecognitionSubmitService recognitionSubmitService;
    @Autowired
    private UserTokenHandler userTokenHandler;

    // app submit img url
    @RequestMapping(value = "/paper/recognition/img", method = RequestMethod.POST)
    public Object submitImage(@RequestParam(value = "access_token") String token,
                              @RequestBody ImageRequest imageRequest) {
        try {

            Long userId = userTokenHandler.exchangeToken(token);
            recognitionSubmitService.submit(userId, imageRequest);
            log.debug("app, submit token success.");
            return ResponseEntity.ok();

        } catch (IllegalUserTokenException e) {
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
    @RequestMapping(value = "/paper/recognition/result", method = RequestMethod.POST)
    public Object recognitionResult(@RequestBody ImageRecognitionResponse response) {
        try {

            recognitionSubmitService.saveRecognitionResponse(response);
            log.debug("paper, save recognition result success.");
            return ResponseEntity.ok();

        } catch (Exception e) {
            log.error("recognition, get recognition response error", e);
            return ResponseEntity.badRequest().build();
        }
    }


    // app get list
    @RequestMapping(value = "/paper/composition/list", method = RequestMethod.GET)
    public Object getCompositionList(@RequestParam(value = "access_token") String token) {
        try {
            Long userId = userTokenHandler.exchangeToken(token);
            List<ResponseList> list = recognitionSubmitService.getList(userId);
            return list;
        } catch (IllegalUserTokenException e) {
            log.error("Paper, user token error", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            log.error("Paper, get list error", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @Data
    public static class ResponseList {
        private Long user_id;
        private String paper_id;
        private String paper_name;
        private String page_id;
    }

    @Data
    public static class ResponseToApp {

        private ResponseList response;

        private String evaluation;
        private String update_time;
    }


    // app get detail
    @RequestMapping(value = "/paper/composition/detail", method = RequestMethod.GET)
    public Object getCompositionDetail(@RequestParam(value = "access_token") String token,
                                       @RequestBody ResponseList request) {
        try {
            Long userId = userTokenHandler.exchangeToken(token);
            ResponseToApp response = recognitionSubmitService.getCompositionDetail(userId, request);
            return response;

        } catch (IllegalUserTokenException e) {
            log.error("Paper, user token error", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            log.error("Paper, get detail error.", e);
            return ResponseEntity.badRequest().build();
        }
    }


}
