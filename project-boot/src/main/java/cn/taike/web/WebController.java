package cn.taike.web;

import cn.taike.jpa.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huayadlly on 2017/8/6.
 */
@RestController
public class WebController {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object get() {
        long count = bookJpaRepository.count();
        System.out.println("记录数:" + count);
        return count;
    }

}
