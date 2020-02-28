package com.bajin.service.impl;

import com.bajin.dao.CarinfoMapper;
import com.bajin.pojo.Carinfo;
import com.bajin.service.ICarService;
import com.bajin.vo.CarinfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements ICarService {
    
    @Autowired
    private CarinfoMapper carinfoMapper;
    @Override
    public Map<String,Object> insert(Carinfo record) {
        //判断用户之前有没有像餐车添加该菜品
        Integer isAdd = carinfoMapper.isAddDish(record);
        int insert = 0;
        if(isAdd == 0){//之前没有添加过该菜品信息
            insert = carinfoMapper.insert(record);
        }else{//之前添加过该菜品信息
            insert = carinfoMapper.updataCarNum(record);
        }
        Map<String,Object> map = new HashMap<>();
        if(insert > 0){
            map.put("status",0);
            return map;
        }else{
            map.put("status",1);
            return map;
        }
    }

    @Override
    public List<CarinfoVO> selectCar(Integer id) {
        List<CarinfoVO> carinfoVOS = carinfoMapper.selectCar(id);
        return carinfoVOS;
    }

    @Override
    public Map<String,Object> cancleAll(Integer id) {
        Map<String,Object> map = new HashMap<>();
        Integer integer = carinfoMapper.cancleAll(id);
        if(integer > 0){
            map.put("status",0);
            return  map;
        }else{
            map.put("status",1);
            return map;
        }
    }
}
