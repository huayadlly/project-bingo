package cn.taike.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by huayadlly on 2017/8/26.
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    /**
     * @param myEvent 事件监听器： 监听到事件后得到事件的消息，然后做相应的逻辑处理
     */
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        String message = myEvent.getMessage();
        System.out.println("监听到事件：" + message);
    }
}
