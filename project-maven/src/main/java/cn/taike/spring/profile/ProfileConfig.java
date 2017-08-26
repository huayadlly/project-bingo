package cn.taike.spring.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by huayadlly on 2017/8/26.
 */
@Configuration
public class ProfileConfig {

    @Bean
    @Profile("development")
    public ProfileSample devProfileSample() {
        return new ProfileSample("Bean from development profile.");
    }

    @Bean
    @Profile("product")
    public ProfileSample prodProfileSample() {
        return new ProfileSample("Bean from product profile");
    }
}
