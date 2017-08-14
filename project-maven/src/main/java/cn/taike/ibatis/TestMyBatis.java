package cn.taike.ibatis;

import cn.taike.ibatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by huayadlly on 2017/8/14.
 */
public class TestMyBatis {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "SqlMapConfig.xml";
        try {
            InputStream stream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据用户id查询用户
    public static void findById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("findUserById", 10);
        System.out.println("user: " + user);

    }

    public static void main(String[] args) {
        findById();
    }

}
