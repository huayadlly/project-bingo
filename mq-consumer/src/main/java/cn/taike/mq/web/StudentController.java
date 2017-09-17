package cn.taike.mq.web;

import cn.taike.mq.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huayadlly on 2017/9/17.
 */
@Slf4j
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public Object addStudent(@RequestParam(value = "student_id") String studentId) {
        try {
            studentService.saveStudent(studentId);
            log.info("Student, save student success,student_id:[{}]", studentId);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error("Student, save student error");
            return ResponseEntity.badRequest().build();
        }
    }
}
