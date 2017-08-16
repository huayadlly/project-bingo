package cn.taike.paper.handler;

import cn.taike.paper.config.BingoProperties;
import cn.taike.paper.domain.PaperInfoEntityJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;


/**
 * Created by huayadlly on 2017/8/16.
 */
@Slf4j
@Component
public class CompositionEvaluationHandler {

    @Autowired
    private PaperInfoEntityJpaRepository paperInfoEntityJpaRepository;
    @Autowired
    private BingoProperties bingoProperties;

    private RestTemplate restTemplate;

    public CompositionEvaluationHandler(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .setReadTimeout(30_000)
                .setConnectTimeout(30_000)
                .build();
    }

    public void submitComposition(Long userId, String paperId, String pageId, String text) {
        try {
            // body
            String body = "{\"text\":\" " + text + "\"}";

            // url
            String visitUrl = bingoProperties.getEvaluationCompositionUrl() + "/api/v1";

            // header
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String authString = bingoProperties.getEvaluationUserName() + ":" + bingoProperties.getEvaluationPassword();
            String authCode = Base64.encodeBase64String(authString.getBytes());
            headers.set("Authorization", "Basic " + authCode);

            // request
            HttpEntity<String> request = new HttpEntity<>(body, headers);

            // response
            ResponseEntity<String> response = restTemplate.exchange(visitUrl, HttpMethod.POST, request, String.class);
            log.debug("Composition, submit composition to evaluation success.");

            if (Objects.equals(response.getStatusCode(), HttpStatus.ACCEPTED)) {
                URI locationConfirm = response.getHeaders().getLocation();

                // send queue to aliyun queue

            }

        } catch (Exception e) {
            log.error("evaluation, submit composition error", e);
        }
    }

}
