package com.cjx913.mybatis.test;

import com.cjx913.mybatis.mapper.OrderMapper;
import com.cjx913.mybatis.mapper.UserMapper;
import com.cjx913.mybatis.pojo.Order;
import com.cjx913.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class OrderTest {
    private SqlSessionFactory sqlSessionFactory = null;


    @Before
    public void init() throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findOrderWithUserById(){
        SqlSession session = sqlSessionFactory.openSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Order order= null;
        try {
            order = orderMapper.findOrderWithUserById(1);
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        System.out.println(order.toString());

        System.out.println(order.getUser().toString());
    }


    @After
    public void release(){

    }
}
