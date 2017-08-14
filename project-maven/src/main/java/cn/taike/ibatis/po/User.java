package cn.taike.ibatis.po;

import lombok.Data;

import java.util.Date;

/**
 * Created by huayadlly on 2017/8/14.
 */
@Data
public class User {

    private Integer id;

    private String username;
    private String sex;
    private Date birthday;
    private String address;

}
