package com.bajin.service;

import com.bajin.pojo.User;

import java.util.Map;

public interface IUserService {

    User login(User user);

    Map<String,Object> insert(User record);

}
