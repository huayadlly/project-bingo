package cn.taike.paper.handler;

import cn.taike.bingo.config.BingoProperties;
import cn.taike.bingo.util.DataFormatUtils;
import cn.taike.paper.domain.PaperInfoEntity;
import cn.taike.paper.domain.PaperInfoEntityJpaRepository;
import cn.taike.paper.protocol.CompositionEvaluations;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * Created by huayadlly on 2017/8/16.
 */
@Slf4j
@Component
public class CompositionEvaluationHandler {

    private static final String SPLIT_REGEX = "<br />\\r\\n";
    private static final String ORIGINAL_ID = "original";
    private static final String REPLACEMENT_ID = "replacement";

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

    // invoke evaluation composition method
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


    // get task from aliyun ons queue
    public void confirmEvaluation(Long userId, String paperId, String pageId, String confirmId) {
        try {
            // url
            String url = bingoProperties.getEvaluationCompositionUrl() + confirmId;

            // head
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String authString = bingoProperties.getEvaluationUserName() + ":" + bingoProperties.getEvaluationPassword();
            String authCode = Base64.encodeBase64String(authString.getBytes());
            headers.set("Authorization", "Basic " + authCode);

            // request
            HttpEntity request = new HttpEntity<>(headers);

            // response
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            log.debug("evaluation, get composition evaluation success");

            String body = response.getBody();
            CompositionEvaluations compositionEvaluations = DataFormatUtils.toEntity(body, CompositionEvaluations.class);
            CompositionEvaluations result = handHtml(compositionEvaluations);

            // save result
            Optional<PaperInfoEntity> optional = paperInfoEntityJpaRepository.findByUserIdAndPaperIdAndPageId(userId, paperId, pageId);
            if(optional.isPresent()){
                PaperInfoEntity entity = optional.get();
                entity.setCompositionEvaluations(result.toJson());
                entity.setUpdateTime(DateTime.now());
            }

        } catch (Exception e) {
            log.error("confirm evaluation, confirm evaluation result error.", e);
        }
    }

    private CompositionEvaluations handHtml(CompositionEvaluations evaluation) {
        try {
            List<CompositionEvaluations.Comments> comments = evaluation.getComments();
            comments.forEach(comment -> {
                String text = comment.getText();
                String[] split = text.split(SPLIT_REGEX);
                String modifyAdvice = split[0];
                String html = split[1];

                Document document = Jsoup.parse(html);
                String originalText = document.getElementsByClass(ORIGINAL_ID).first().text();
                String replacement = document.getElementsByClass(REPLACEMENT_ID).first().text();

                comment.setModifyAdvise(modifyAdvice);
                comment.setReplacementText(replacement);
                comment.setOriginalText(originalText);
            });
        } catch (Exception e) {
            log.error("evaluation, hand heml text error.", e);
        }
        return evaluation;
    }

}
