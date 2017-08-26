package cn.taike.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by huayadlly on 2017/8/26.
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        publisher.publishEvent("send event message");

        context.close();
    }
}
