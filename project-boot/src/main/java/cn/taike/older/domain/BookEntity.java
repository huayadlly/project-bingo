package cn.taike.older.domain;

import cn.taike.bingo.util.BeanToJson;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by huayadlly on 2017/8/6.
 */
@Data
@Entity
@Table(name = "book",
        indexes = {
                @Index(name = "index_id_bookName", columnList = "id,bookName", unique = true)
        }
)
public class BookEntity implements BeanToJson {

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
