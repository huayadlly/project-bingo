package cn.taike.mq.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by huayadlly on 2017/9/16.
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByStudentId(String studentId);

}
