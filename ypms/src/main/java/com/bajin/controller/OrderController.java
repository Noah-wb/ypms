package com.bajin.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.bajin.auth.entity.UserInfo;
import com.bajin.auth.utils.JwtUtils;
import com.bajin.auth.utils.RsaUtils;
import com.bajin.common.Const;
import com.bajin.service.IOrderService;
import com.bajin.vo.OrderVO;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("queryOrder")
    public Map<String,Object> queryOrder(@CookieValue(value = "usertoken",required = false) String token, HttpServletRequest request, HttpServletResponse response){
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
        List<OrderVO> orderList = orderService.queryOrder(userInfo.getId().intValue());
        map.put("code",0);
        map.put("status",0);
        map.put("data",orderList);
        return map;
    }

    /**
     * 生成订单
     */
    @RequestMapping("createOrder")
    public Object createOrder(@CookieValue(value = "usertoken",required = false) String token, HttpServletRequest request, HttpServletResponse response){
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

        Long order = orderService.createOrder(userInfo.getId().intValue());
        map.put("code",0);
        map.put("status",3);
        map.put("data",order);
        return map;
    }

    /**
     * 支付
     * 2个参数 用户id  订单编号
     */
    @RequestMapping("pay")
    public Object pay(@CookieValue(value = "usertoken",required = false) String token, HttpServletRequest request, HttpServletResponse response,Long orderNo){
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

        System.out.println(userInfo.getId());
        System.out.println(orderNo);
        map = orderService.pay(userInfo.getId().intValue(), orderNo);
        return map;
    }

    /**
     * 当支付成功的时候支付宝访问该地址
     * @param request
     * @return
     */
    @RequestMapping("alipay_callback")
    public String alipayCallback(HttpServletRequest request){
        System.out.println("-------------------alipayCallback----------------------***");
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for(Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
            String name = (String)iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for(int i = 0 ; i <values.length;i++){

                valueStr = (i == values.length -1)?valueStr + values[i]:valueStr + values[i]+",";
            }
            params.put(name,valueStr);
            System.out.println(name);
            System.out.println(valueStr);
        }
        //非常重要,验证回调的正确性,是不是支付宝发的.并且呢还要避免重复通知.
        params.remove("sign_type");
        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());

            if(!alipayRSACheckedV2){
                System.out.println("非法请求,验证不通过,再恶意请求我就报警找网警了");
                return "failed";
            }
        } catch (AlipayApiException e) {
            System.out.println("支付宝验证回调异常"+e);
            return "failed";
        }

        String s = orderService.aliCallback(params);
        return "s";
    }

    /**
     * 2.查询订单支付状态
     * @param token
     * @param orderNo
     * @return
     */
    @RequestMapping("query_order_pay_status")
    public Map<String, Object> queryOrderPayStatus(@CookieValue(value = "usertoken",required = false) String token, HttpServletRequest request, HttpServletResponse response,Long orderNo){
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

        Map<String, Object> stringObjectMap = orderService.queryOrderPayStatus(orderNo);

        return stringObjectMap;
    }


}
