package com.cjx913.mybatis.test;


import com.cjx913.mybatis.mapper.ProductMapper;
import com.cjx913.mybatis.pojo.Product;
import com.cjx913.mybatis.pojo.ProductExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * 使用逆向工程创建POJO、Mapper.java、Mapper.xml
 * Product的测试类
 */

public class ProductTest {
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
        ProductMapper productMapper = session.getMapper(ProductMapper.class);
        Product product = new Product();
        product.setProductName("asdfg");
        product.setProductPicture("sdasdashhwioqw2321");
        product.setProductPrice(BigDecimal.valueOf(22.23));
        product.setProductRemark("poiuytrewqasdfg");
        try {
            productMapper.insert(product);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

    @Test
    public void update(){
        SqlSession session = sqlSessionFactory.openSession();
        ProductMapper productMapper = session.getMapper(ProductMapper.class);
        Product product = productMapper.selectByPrimaryKey(2);
        product.setProductName("asdfg111");
        product.setProductPicture("sdasdashhwioqw2321111");
        product.setProductPrice(BigDecimal.valueOf(225.23));
        product.setProductRemark("poiuytrewqasdfg111");
        try {
            productMapper.updateByPrimaryKeySelective(product);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void selectByExample(){
        SqlSession session = sqlSessionFactory.openSession();
        ProductMapper productMapper = session.getMapper(ProductMapper.class);
        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andProductIdBetween(3,5);
        List <Product> productList = productMapper.selectByExample(productExample);
        for(Product p:productList){
            System.out.println(p.toString());
        }
    }
}
