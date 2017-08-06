package cn.taike.jpa;

import cn.taike.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huayadlly on 2017/8/6.
 */
public interface BookJpaRepository extends JpaRepository<Book, Integer> {
}
