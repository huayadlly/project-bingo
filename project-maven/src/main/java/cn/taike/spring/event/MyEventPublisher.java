package cn.taike.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by huayadlly on 2017/8/26.
 */
@Component
public class MyEventPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void publishEvent(String msg) {
        applicationContext.publishEvent(new MyEvent(this, msg));
    }
}
