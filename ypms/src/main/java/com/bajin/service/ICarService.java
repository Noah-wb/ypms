package com.bajin.service;

import com.bajin.pojo.Carinfo;
import com.bajin.vo.CarinfoVO;

import java.util.List;
import java.util.Map;

public interface ICarService {
    Map<String,Object> insert(Carinfo record);

    List<CarinfoVO> selectCar(Integer id);

    /**
     * 全部取消：情况餐车表数据
     */
    Map<String,Object> cancleAll(Integer id);
}
