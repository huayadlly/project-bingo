package cn.taike.mq.service;

import cn.taike.mq.domain.StudentEntity;
import cn.taike.mq.domain.StudentJpaRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by huayadlly on 2017/9/16.
 */
@Service
public class StudentService {

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    // 保存
    public void saveStudent(String studentId) {

        Optional<StudentEntity> optEntity = studentJpaRepository.findByStudentId(studentId);
        if (optEntity.isPresent()) {

            StudentEntity studentEntity = optEntity.get();
            studentEntity.setUpdateTime(DateTime.now());

            studentJpaRepository.save(studentEntity);

        } else {
            StudentEntity student = new StudentEntity();
            student.setStudentId(studentId);
            student.setStudentName("Tom");
            student.setStudentAddress("北京");
            student.setCollegeName("hpu");

            DateTime now = DateTime.now();
            student.setCreateTime(now);
            student.setUpdateTime(now);

            studentJpaRepository.save(student);
        }
    }
}
