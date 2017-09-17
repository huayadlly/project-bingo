package cn.taike.mq.producer;

import cn.taike.mq.config.QueueConfig;
import cn.taike.mq.msg.StudentMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by huayadlly on 2017/9/16.
 */
@Slf4j
@Component
public class StudentMessageProducer implements CommandLineRunner {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void run(String... args) throws Exception {
//        sendMessage("send message");
    }

    public void sendMessage(String msg) {
        jmsTemplate.send(QueueConfig.QUEUE_NAME, new StudentMessage(msg));
        log.debug("MQ, send message success...");
    }
}
