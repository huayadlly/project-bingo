package cn.taike.dao;

import cn.taike.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by huayadlly on 2017/8/6.
 */
public class JdbcTemplateSample {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/basement?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String DRIVEN = "com.mysql.jdbc.Driver";

    private static DriverManagerDataSource dataSource = new DriverManagerDataSource();

    static {
        dataSource.setDriverClassName(DRIVEN);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

    //查询总记录数
    public void queryCount() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT COUNT(1) FROM book";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("总记录数：" + count);
    }

    public void queryAllList() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT * FROM book";
        List<Book> list = jdbcTemplate.query(sql, new myRowMapper());
        list.forEach(System.out::println);
    }

    public void queryById(Integer id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM book WHERE id = ?";
        Book book = jdbcTemplate.queryForObject(sql, new myRowMapper(), id);
        System.out.println("id为" + id + "的book:" + book);
    }

    class myRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                    rs.getInt("id"),
                    rs.getString("book_name"),
                    rs.getString("project_name"),
                    rs.getString("type"),
                    rs.getString("cover"),
                    rs.getString("video")
            );
        }
    }


    public static void main(String[] args) {
        JdbcTemplateSample sample = new JdbcTemplateSample();

        sample.queryCount();
        sample.queryAllList();
        sample.queryById(9);
    }

}
