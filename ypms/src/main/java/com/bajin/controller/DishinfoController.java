package com.bajin.controller;

import com.bajin.service.IDishinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("dish")
public class DishinfoController {
    @Autowired
    private IDishinfoService dishinfoService;

    @RequestMapping("getDishinfo")
    public Map<String,Object> getDishinfo(){
        Map<String, Object> map = dishinfoService.getDishinfoList();
        return map;
    }
}
