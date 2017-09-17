package cn.taike.mq.domain;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by huayadlly on 2017/9/16.
 */
@Data
@Entity
@Table(name = "table_student",
        indexes = {@Index(name = "index_studentId_studentName",
                columnList = "studentId,studentName",
                unique = true)
        }
)
public class StudentEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String studentId;
    @Column
    private String studentName;
    @Column
    private String studentAddress;
    @Column
    private String collegeName;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createTime;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updateTime;

}
