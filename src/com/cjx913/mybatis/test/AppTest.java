package com.cjx913.mybatis.test;

import com.cjx913.mybatis.mapper.UserMapper;
import com.cjx913.mybatis.pojo.Order;
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
    public void insert(){
        SqlSession session = sqlSessionFactory.openSession();
        User user = new User();
        user.setName("cjx91311");
        user.setPassword("cjx91311");
//        要加命名空间
        session.insert("com.cjx913.mybatis.mapper.UserMapper.insertUser",user);
        System.out.println(user.getUser_id());
        session.commit();
        session.close();
    }

    @Test
    public void find() {
        /*SqlSession非线程安全，作为局部变量*/
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("com.cjx913.mybatis.mapper.UserMapper.findUserById",1);
        System.out.println(user.toString());
      /*  List<User> list1 = session.selectList("com.cjx913.mybatis.mapper.UserMapper.findUserByName","aaaa");
        for(User u:list1){
            System.out.println(u.toString());
        }
        List<User> list2 = session.selectList("com.cjx913.mybatis.mapper.UserMapper.findUserByLikeName","cjx913");
        for(User u:list2){
            System.out.println(u.toString());
        }*/
        session.close();
    }

    @Test
    public void update(){
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("com.cjx913.mybatis.mapper.UserMapper.findUserById",2);
        user.setPassword("cjx913www");
        session.update("com.cjx913.mybatis.mapper.UserMapper.updateUser",user);
        session.commit();
        session.close();
    }

    /**
     * 一对一ResultMapper
     */
    @Test
    public void findUserWithMessageById(){
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = null;
        try {
            user = userMapper.findUserWithMessageById(1);
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        System.out.println("*****************");
        System.out.println(user.toString());
        System.out.println("*****************");
        System.out.println(user.getMessage().toString());
        System.out.println("*****************");
    }

    @Test
    public void findUserWithOrderListById(){
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = null;
        try {
            user = userMapper.findUserWithOrderListById(1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        for(Order o:user.getOrderList()){
            System.out.println(o.toString());
        }
    }


    @After
    public void release(){

    }
}
