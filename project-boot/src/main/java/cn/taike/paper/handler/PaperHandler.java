package cn.taike.paper.handler;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by huayadlly on 2017/8/13.
 */
@Component
public class PaperHandler {

    private RestTemplate restTemplate;

    public PaperHandler(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .setConnectTimeout(30_000)
                .setReadTimeout(30_000)
                .build();
    }

    public void handResponse() {

    }


}
