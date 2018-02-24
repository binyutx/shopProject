package com.taotao.portal.controller;

import com.taotao.manager.pojo.User;
import com.taotao.portal.util.CookieUtils;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈注册的controller〉
 *
 * @author kepad
 * @description com.taotao.portal.controller
 * @create 2018/2/24
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${TT_TICKET}")
    private String TT_TICKET;
    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "doRegister",method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,Object> doRegister(User user){
        this.userService.doRegister(user);
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("status",200);
        return map;
    }

    /**
     * 用户登录
     * @param user
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "doLogin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,Object>doLogin(User user, HttpServletRequest request, HttpServletResponse response){
        //调用服务进行用户登录，需要返回ticket，目的是放到cookie中给用户
        String ticket = this.userService.doLogin(user);
       if (StringUtils.isNotBlank(ticket)){
           //如果登录成功ticket需要放到cookie中
           CookieUtils.setCookie(request,response,this.TT_TICKET,ticket,60*60*24,true);
           Map<String,Object>map = new HashMap<String, Object>();
           map.put("status",200);
           return map;
       }
       return null;
    }

}
