package cn.taike.paper.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by huayadlly on 2017/8/13.
 */
@Slf4j
@Component
public class RecognitionPaperHandler {

    private static ObjectMapper mapper = new ObjectMapper();

    private RestTemplate restTemplate;

    public RecognitionPaperHandler(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .setConnectTimeout(30_000)
                .setReadTimeout(30_000)
                .build();
    }

    // 将客户端提交的图片转发第三方处理，restTemplate实现http请求
    public void sendImage(Long userId, String taskId, String imageUrl) {
        try {
            log.debug("recognition, start send image.");

            // body : http请求体，必须是JSON格式
            String body = mapper.writeValueAsString(new RequestBody(userId, taskId, imageUrl));

            // heads
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // request
            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

            // response
            ResponseEntity<String> response = restTemplate.exchange("", HttpMethod.POST, requestEntity, String.class);
            log.debug("Recognition, send image success.");

        } catch (Exception e) {
            log.error("Recognition, submit image error.", e);
        }
    }

    @Data
    @NoArgsConstructor
    public static class RequestBody {
        private Long userId;
        private String taskId;
        private String imageUrl;

        public RequestBody(Long userId, String taskId, String imageUrl) {
            this.userId = userId;
            this.taskId = taskId;
            this.imageUrl = imageUrl;
        }
    }


}
