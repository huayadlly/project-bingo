package cn.taike.mq.web;

import cn.taike.mq.producer.StudentMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huayadlly on 2017/9/16.
 */
@RestController
public class StudentController {

    @Autowired
    private StudentMessageProducer producer;

    @RequestMapping(value = "/active/mq/send", method = RequestMethod.GET)
    public Object sendMessage(@RequestParam(value = "msg") String message) {

        producer.sendMessage(message);

        return "send message OK";
    }
}
