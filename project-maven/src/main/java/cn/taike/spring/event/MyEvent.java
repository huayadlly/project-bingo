package cn.taike.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by huayadlly on 2017/8/26.
 */

/**
 * spring event : event
 * extend: ApplicationEvent
 */
public class MyEvent extends ApplicationEvent {

    public static final Long serialVersionUID = 1L;

    private String message;

    public MyEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
