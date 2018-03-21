package com.cjx913.mybatis.mapper;


import com.cjx913.mybatis.pojo.Order;

public interface OrderMapper {
    public Order findOrderWithUserById(int id) throws Exception;

}
