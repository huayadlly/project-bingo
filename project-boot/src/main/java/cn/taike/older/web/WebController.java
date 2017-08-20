package cn.taike.older.web;

import cn.taike.older.domain.BookEntity;
import cn.taike.older.domain.BookEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by huayadlly on 2017/8/6.
 */
@RestController
public class WebController {

    @Autowired
    private BookEntityJpaRepository bookEntityJpaRepository;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object get() {
        long count = bookEntityJpaRepository.count();
        System.out.println("记录数:" + count);
        return count;
    }

    //分页查询
    @RequestMapping(value = "/get/page", method = RequestMethod.GET)
    public Object getPageable() {

        Pageable pageable = new PageRequest(4, 4, new Sort(Sort.Direction.DESC, "bookName"));

        Page<BookEntity> bookPage = bookEntityJpaRepository.findAll(pageable);
        int totalPages = bookPage.getTotalPages();//总页数
        int number = bookPage.getNumber();//当前分页查询的页数
        long totalElements = bookPage.getTotalElements();//表中数据总个数

        int numberOfElements = bookPage.getNumberOfElements();
        int size = bookPage.getSize();

        List<BookEntity> bookEntityList = bookPage.getContent();

        return bookEntityList;
    }
}
