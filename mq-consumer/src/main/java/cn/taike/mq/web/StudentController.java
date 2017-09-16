package cn.taike.mq.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huayadlly on 2017/9/17.
 */
@RestController
public class StudentController {

    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    public Object addStudent() {

        return null;
    }
}
