package cn.taike.mq.mq;

import cn.taike.mq.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by huayadlly on 2017/9/17.
 */
@Slf4j
@Component
public class MessageListener {

    @Autowired
    private StudentService studentService;

    @JmsListener(destination = "queue_name")
    public void getMessage(StudentMessage message) {
        String msg = message.getMessage();
        log.debug("Student, get message success,msg:[{}]", msg);

        studentService.saveStudent(msg);

    }

}
