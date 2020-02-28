package com.bajin.service.impl;

import com.bajin.dao.DishinfoMapper;
import com.bajin.service.IDishinfoService;
import com.bajin.vo.DishinfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DishinfoServiceImpl implements IDishinfoService {
    @Autowired
    private DishinfoMapper dishinfoMapper;
    @Override
    public Map<String,Object> getDishinfoList() {
        Map<String,Object> map = new HashMap<>();
        List<DishinfoVO> dishinfoList = dishinfoMapper.getDishinfoList();
        map.put("status",0);
        map.put("data",dishinfoList);
        return map;
    }
}
