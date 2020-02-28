package com.bajin.dao;

import com.bajin.pojo.Dishtype;

public interface DishtypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dishtype record);

    int insertSelective(Dishtype record);

    Dishtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dishtype record);

    int updateByPrimaryKey(Dishtype record);
}