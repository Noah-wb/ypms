package com.bajin.controller;

import com.bajin.auth.entity.UserInfo;
import com.bajin.auth.utils.JwtUtils;
import com.bajin.auth.utils.RsaUtils;
import com.bajin.common.Const;
import com.bajin.pojo.Carinfo;
import com.bajin.service.ICarService;
import com.bajin.vo.CarinfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private ICarService carService;
    /**
     * 放入餐车
     */
    @RequestMapping("insertCar")
    public Map<String,Object> insertCar(Integer id, @CookieValue(value = "usertoken",required = false) String token, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        //解析token
        UserInfo userInfo = null;
        if(StringUtils.isNotBlank(token)){
            try {
                userInfo = JwtUtils.getUserInfo(RsaUtils.getPublicKey(Const.PUBLIC_KEY_PATH), token);
                System.out.println(userInfo);
            } catch (Exception e) {
                //token解析失败表示未登录
                map.put("status",3);
                return map;
            }

        }else{
            //token为空表示用户未登录
            map.put("status",3);
            return map;
        }

        Carinfo carinfo =  new Carinfo();
        carinfo.setDishid(id);
        carinfo.setUserid(userInfo.getId().intValue());
        Map<String, Object> result = carService.insert(carinfo);
        return result;
    }

    @RequestMapping("selectCar")
    public Object selectCar(@CookieValue(value = "usertoken",required = false) String token, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        //解析token
        UserInfo userInfo = null;
        if(StringUtils.isNotBlank(token)){
            try {
                userInfo = JwtUtils.getUserInfo(RsaUtils.getPublicKey(Const.PUBLIC_KEY_PATH), token);
                System.out.println(userInfo);
            } catch (Exception e) {
                //token解析失败表示未登录
                map.put("code",0);
                map.put("status",3);
                return map;
            }
        }else{
            //token为空表示用户未登录
            map.put("code",0);
            map.put("status",3);
            return map;
        }

        List<CarinfoVO> carList = carService.selectCar(userInfo.getId().intValue());
        map.put("code",0);
        map.put("count",6);
        map.put("data",carList);
        return map;
    }

    /**
     * 全部取消
     */
    @RequestMapping("cancleAll")
    public Object cancleAll(@CookieValue(value = "usertoken",required = false) String token, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        //解析token
        UserInfo userInfo = null;
        if(StringUtils.isNotBlank(token)){
            try {
                userInfo = JwtUtils.getUserInfo(RsaUtils.getPublicKey(Const.PUBLIC_KEY_PATH), token);
                System.out.println(userInfo);
            } catch (Exception e) {
                //token解析失败表示未登录
                map.put("code",0);
                map.put("status",3);
                return map;
            }
        }else{
            //token为空表示用户未登录
            map.put("code",0);
            map.put("status",3);
            return map;
        }

        map = carService.cancleAll(userInfo.getId().intValue());
        return map;
    }
}
