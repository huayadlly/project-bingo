package cn.taike.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by huayadlly on 2017/8/6.
 */
@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String bookName;
    @Column
    private String projectName;
    @Column
    private String type;
    @Column
    private String cover;
    @Column
    private String video;

}
