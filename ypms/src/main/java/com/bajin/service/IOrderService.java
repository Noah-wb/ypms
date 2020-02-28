package com.bajin.service;

import com.bajin.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    List<OrderVO> queryOrder(Integer id);

    public Long createOrder(Integer id);

    public Map<String, Object> pay(Integer userId, Long orderNo);

    public String aliCallback(Map<String, String> params);

    public Map<String, Object> queryOrderPayStatus(Long orderNo);
}
