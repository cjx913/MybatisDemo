package com.cjx913.mybatis.test;

import com.cjx913.mybatis.pojo.User;
import oracle.net.ns.SessionAtts;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AppTest {
    private SqlSessionFactory sqlSessionFactory = null;


    @Before
    public void init() throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void find() {
        /*SqlSession非线程安全，作为局部变量*/
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("findUserById",1);
        System.out.println(user.toString());
        List<User> list1 = session.selectList("findUserByName","aaaa");
        for(User u:list1){
            System.out.println(u.toString());
        }
        List<User> list2 = session.selectList("findUserByLikeName","cjx913");
        for(User u:list2){
            System.out.println(u.toString());
        }
        session.close();
    }

    @Test
    public void insert(){
        SqlSession session = sqlSessionFactory.openSession();
        User user = new User();
        user.setName("cjx91312");
        user.setPassword("cjx91312");
        session.insert("insertUser",user);
        System.out.println(user.getId());
        session.commit();
        session.close();
    }

    @Test
    public void update(){
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("findUserById",3);
        user.setPassword("dddd");
        session.update("updateUser",user);
        session.commit();
        session.close();
    }

    @After
    public void release(){

    }
}
