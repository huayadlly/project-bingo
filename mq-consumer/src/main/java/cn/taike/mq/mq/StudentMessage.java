package cn.taike.mq.mq;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by huayadlly on 2017/9/17.
 */
public class StudentMessage implements MessageCreator {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StudentMessage(String msg) {
        this.message = msg;
    }

    @Override
    public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage(this.message);
    }
}
