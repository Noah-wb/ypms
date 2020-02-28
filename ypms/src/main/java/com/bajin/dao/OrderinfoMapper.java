package com.bajin.dao;

import com.bajin.pojo.Orderinfo;
import com.bajin.vo.OrderVO;

import java.util.List;

public interface OrderinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orderinfo record);

    int insertSelective(Orderinfo record);

    Orderinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orderinfo record);

    int updateByPrimaryKey(Orderinfo record);

    List<OrderVO> queryOrder(Integer id);

    Orderinfo selectByOrderNo(Long id);
}