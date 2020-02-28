package com.bajin.dao;

import com.bajin.bo.OrderDetailBO;
import com.bajin.pojo.OrderDetile;

import java.util.List;

public interface OrderDetileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetile record);

    int insertSelective(OrderDetile record);

    OrderDetile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDetile record);

    int updateByPrimaryKey(OrderDetile record);

    Integer insetOrderDetaile(List<OrderDetile> list);

    List<OrderDetailBO> getByOrderNo(Long id);
}