package cn.taike.spring.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by huayadlly on 2017/8/27.
 */
@Configuration
@ComponentScan("cn.taike.spring.test")
public class TestConfig {

    @Bean
    @Profile("dev")
    public TestBeans devTestBeans() {
        return new TestBeans("dev profile Bean");
    }

    @Bean
    @Profile("prod")
    public TestBeans prodTestBeans() {
        return new TestBeans("prod profile Bean");
    }
}
