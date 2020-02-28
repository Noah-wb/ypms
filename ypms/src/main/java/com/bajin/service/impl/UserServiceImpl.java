package com.bajin.service.impl;

import com.bajin.dao.UserMapper;
import com.bajin.pojo.User;
import com.bajin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User login = userMapper.login(user);
        return login;
    }

    @Override
    public Map<String,Object> insert(User record) {
        Map<String,Object> map = new HashMap<>();
        //1.判断账号是否存在
        Integer result = userMapper.usernameIsExist(record.getUsername());
        if(result > 0){
            map.put("status",2);
            return map;
        }
        int insert = userMapper.insert(record);
        if(insert > 0){
            map.put("status",0);
            return map;
        }else{
            map.put("status",1);
            return map;
        }
    }

}
