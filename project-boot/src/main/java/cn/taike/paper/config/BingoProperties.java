package cn.taike.paper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Created by huayadlly on 2017/8/16.
 */
@Data
@Component
@ConfigurationProperties(prefix = "paper.recognition")
public class BingoProperties {

    @NotNull
    private String callbackUrl;

    @NotNull
    private String submitImageUrl;
    @NotNull
    private String evaluationCompositionUrl;
    @NotNull
    private String evaluationUserName;
    @NotNull
    private String evaluationPassword;

}
