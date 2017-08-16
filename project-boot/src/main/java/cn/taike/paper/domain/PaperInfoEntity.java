package cn.taike.paper.domain;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

/**
 * Created by huayadlly on 2017/8/13.
 */
@Data
@Entity
@Table(name = "paper_table",
        indexes = {
                @Index(name = "index_userId_paperId_pageId", columnList = "paperId,pageId")
        }
)
public class PaperInfoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long userId;
    @Column
    private String taskId;

    @Column
    private String paperId;
    @Column
    private String pageId;
    @Column
    private String paperName;

    @Column(columnDefinition = "TEXT")
    private String qas;
    @Column(columnDefinition = "TEXT")
    private String wrapInfo;
    @Column(columnDefinition = "TEXT")
    private String subImgKeys;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createTime;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updateTime;

}
