package com.bajin.controller;

import com.bajin.auth.entity.UserInfo;
import com.bajin.auth.utils.CookieUtils;
import com.bajin.auth.utils.JwtUtils;
import com.bajin.auth.utils.RsaUtils;
import com.bajin.common.Const;
import com.bajin.pojo.User;
import com.bajin.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用
 *  0 表示成功
 *  1 表示失败
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 登录
     */
    @RequestMapping("login")
    public Map<String,Object> login(User user, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        //如果login有值表示登录成功，否则登录失败
        User login = userService.login(user);
        if(login != null){
            String token = "";
            try {
                token = JwtUtils.generateToken(new UserInfo(login.getId().longValue(), login.getUsername()), RsaUtils.getPrivateKey(Const.PRIVATE_KEY_PATH), 30);
                CookieUtils.newBuilder(response).httpOnly().maxAge(600).request(request).build("usertoken", token);
                map.put("status",0);
                return map;
            } catch (Exception e) {
                map.put("status",1);
                return map;
            }

        }else{
            map.put("status",1);
            return map;
        }
    }

    /**
     * 解析token获取用户信息
     * @param token
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getUserinfo")
    public Map<String,Object> getUserinfo(@CookieValue(value = "usertoken",required = false) String token, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        //解析token
        UserInfo userInfo = null;
        if(StringUtils.isNotBlank(token)){
            try {
                userInfo = JwtUtils.getUserInfo(RsaUtils.getPublicKey(Const.PUBLIC_KEY_PATH), token);
                System.out.println(userInfo);
            } catch (Exception e) {
                //e.printStackTrace();
                map.put("status",1);
                return map;
            }
        }else{
            map.put("status",1);
            return map;
        }
        map.put("status",0);
        map.put("data",userInfo);
        return map;
    }

    /**
     * 注销登录
     */
    @RequestMapping("loginOut")
    public Map<String,Object> loginOut(@CookieValue(value = "usertoken",required = false) String token, HttpServletRequest request, HttpServletResponse response){
        //将token改成不能被解密的字符串
        CookieUtils.newBuilder(response).httpOnly().maxAge(0).request(request).build("usertoken", "******");
        Map<String,Object> map = new HashMap<>();
        map.put("status",0);
        return map;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    public Map<String,Object> register(User user){
        System.out.println(user+"//////////");
        Map<String, Object> map = userService.insert(user);
        return map;
    }

}
