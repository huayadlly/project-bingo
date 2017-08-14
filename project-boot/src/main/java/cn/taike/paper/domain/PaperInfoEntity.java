package cn.taike.paper.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by huayadlly on 2017/8/13.
 */
@Data
@Entity
@Table(name = "paper_table")
public class PaperInfoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String paperId;
    @Column
    private String pageId;
    @Column
    private String paperName;

    @Column
    private String qas;
    @Column
    private String wrapInfo;

}
