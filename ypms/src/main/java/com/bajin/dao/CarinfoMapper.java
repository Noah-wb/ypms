package com.bajin.dao;

import com.bajin.pojo.Carinfo;
import com.bajin.vo.CarinfoVO;

import java.util.List;

public interface CarinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Carinfo record);

    int insertSelective(Carinfo record);

    Carinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Carinfo record);

    int updateByPrimaryKey(Carinfo record);
    //判断用户之前有没有像餐车添加该菜品
    Integer isAddDish(Carinfo record);
    //修改餐车菜品的数量
    Integer updataCarNum(Carinfo record);

    /**
     * 查询餐车
     * id:用户id
     */

    List<CarinfoVO> selectCar(Integer id);

    /**
     * 全部取消：情况餐车表数据
     */
    Integer cancleAll(Integer id);

    //根据用户主键查询餐车信息
    List<Carinfo> queryCarByUid(Integer id);
}