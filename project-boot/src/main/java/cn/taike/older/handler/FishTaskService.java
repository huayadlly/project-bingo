package cn.taike.older.handler;

import cn.taike.bingo.config.BingoProperties;
import cn.taike.older.domain.BookEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * Created by huayadlly on 2017/8/11.
 */
@Slf4j
@Component
public class FishTaskService {


    @Autowired
    private BingoProperties bingoProperties;

    private RestTemplate restTemplate;

    public FishTaskService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .setConnectTimeout(30_000)
                .setReadTimeout(30_000).build();
    }

    public Object insertTable(BookEntity bookEntity) {

        try {
            // url
            String url = bingoProperties.getCallbackUrl();

            // body
            String body = bookEntity.toJson();

            // header
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            // request
            HttpEntity<String> requestEntity = new HttpEntity<>(body, httpHeaders);

            // response
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            // response body
            String responseBody = response.getBody();
            // response head
            HttpHeaders responseHeaders = response.getHeaders();
            // response status code
            HttpStatus responseStatusCode = response.getStatusCode();
            if (Objects.equals(responseStatusCode, HttpStatus.SEE_OTHER)) {
                System.out.println("response headers code is: " + responseStatusCode.value());
            }

            return responseBody;
        } catch (JsonProcessingException e) {
            log.error("FishTask, parse bean to json error");
            return null;
        }
    }
}


