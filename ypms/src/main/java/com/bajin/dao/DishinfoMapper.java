package com.bajin.dao;

import com.bajin.pojo.Dishinfo;
import com.bajin.vo.DishinfoVO;

import java.util.List;

public interface DishinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dishinfo record);

    int insertSelective(Dishinfo record);

    Dishinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dishinfo record);

    int updateByPrimaryKey(Dishinfo record);

    //查询首页菜品信息
    List<DishinfoVO> getDishinfoList();
}