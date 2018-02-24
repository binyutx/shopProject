package com.taotao.sso.controller;

import com.taotao.manager.pojo.User;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.security.auth.callback.Callback;

/**
 * 〈一句话功能简述〉<br>
 * 〈单点登录的Controller〉
 *
 * @author kepad
 * @description com.taotao.manager.controller
 * @create 2018/2/10
 * @since 1.0.0
 */
@RequestMapping(value = "user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    // http://sso.taotao.com/user/check/{param}/{type}
    @RequestMapping(value = "/check/{param}/{type}",method = RequestMethod.GET)
    public ResponseEntity<String> check(@PathVariable(value = "param")String param ,@PathVariable(value = "type")Integer type,String callback){
        //如果传入的type不是123就返回参数无效代码
       if (type == null || type < 1 || type > 3){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(callback+"(false)");
       }
        try {
            //200 如果没有发生异常就直接返回成功
           Boolean flag =  this.userService.check(param,type);
           String  resultStr="";
           if (StringUtils.isNotBlank(callback)){
               resultStr=callback+"("+flag+")";
           }else {
               resultStr +=flag;
           }
            return ResponseEntity.ok(resultStr);

        }catch (Exception e){
            e.printStackTrace();
        }
        //出错就返回500异常
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(callback+"(false)");

    }
    //http://sso.taotao.com/user/{ticket}
    @RequestMapping(value = "{ticket}",method = RequestMethod.GET)
    public ResponseEntity<User> queryUserByTicket(@PathVariable(value = "ticket")String ticket){
            //如果传的ticket为空返回参数无效代码
            if (StringUtils.isNotBlank(ticket)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        try {
            User user =  this.userService.queryUserByTicket(ticket);
            if (user!=null){
                return ResponseEntity.ok(user);
            }else {
                //如果找不到数据就说明没有登录，返回404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //出错就返回500异常
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);


    }
}
