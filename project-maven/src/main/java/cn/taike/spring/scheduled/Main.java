package cn.taike.spring.scheduled;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by huayadlly on 2017/8/27.
 */
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ScheduledConfig.class);
    }
}
