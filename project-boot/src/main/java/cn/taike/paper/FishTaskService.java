package cn.taike.paper;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by huayadlly on 2017/8/11.
 */
public class FishTaskService {

    private static final String url = "";

    private RestTemplate restTemplate;

    public FishTaskService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .setConnectTimeout(30_000)
                .setReadTimeout(30_000).build();
    }

    public Object insertTable() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);


        return null;
    }
}


