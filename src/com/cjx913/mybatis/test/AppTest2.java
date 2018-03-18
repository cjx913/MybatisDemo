package com.cjx913.mybatis.test;

import com.cjx913.mybatis.mapper.UserMapper;
import com.cjx913.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AppTest2 {
    private SqlSessionFactory sqlSessionFactory = null;
    @Before
    public void init() throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findUserById() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserById(1);
        System.out.println("***************************************************************");
        System.out.println(user.toString());
        System.out.println("***************************************************************");
        List<User> list = userMapper.findAllUser();
        for(User u:list){
            System.out.println(u.toString());
        }
        session.close();
    }

    @Test
    public void insertUser() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setName("aaaa");
        user.setPassword("bbbb");
        userMapper.insertUser(user);
        session.commit();
        session.close();
    }

    @Test
    public void deleteUser() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        userMapper.deleteUser(userMapper.findUserById(3));

        session.commit();
        session.close();
    }
}
