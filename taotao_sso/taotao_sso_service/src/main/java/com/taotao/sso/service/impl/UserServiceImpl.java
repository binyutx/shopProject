package com.taotao.sso.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.mapper.UserMapper;
import com.taotao.manager.pojo.User;
import com.taotao.sso.redis.RedisUtils;
import com.taotao.sso.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author kepad
 * @description com.taotao.manager.service.impl
 * @create 2018/2/10
 * @since 1.0.0
 */
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean check(String param, Integer type) {
        User user = new User();
        switch (type){
            case 1:
                user.setUsername(param);
                break;
            case 2:
                user.setPhone(param);
            break;
            case 3:
                user.setEmail(param);
                break;
            default:
                break;
        }
        int count = this.userMapper.selectCount(user);
        //如果查到数据说明数据不可用 返回提示不可用
        if (count>0){
            return false;//不可以是返回false
        }else {
            return true;//可用为true
        }

    }
    @Autowired
    private RedisUtils redisUtils;
    @Value("${SSO_TICKET_KEY}")
    private String SSO_TICKET_KEY;
    //用法: 1.把json字符串转成对象，2把对象转成json字符串，3解析json字符串
    private final ObjectMapper MAPPER=new ObjectMapper();
    @Override
    public User queryUserByTicket(String ticket) {
        User user = null;
        //从redis中获取用户登录的信息
        String jsonStr = redisUtils.get(this.SSO_TICKET_KEY + ticket);
        if (StringUtils.isNotBlank(jsonStr)){
            //如果用户要检查登录，说说用户是活动状态，这时要重置用户的登录有效时间
            redisUtils.expire(this.SSO_TICKET_KEY + ticket,60*60);

            try {
                //将json字符串转换成对象 ,可能出现io流异常
               user =  MAPPER.readValue(jsonStr,User.class);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return user;
    }
    @Value("${SSO_TAOTAO_TICKET_INCR}")
    private String SSO_TAOTAO_TICKET_INCR;
    @Override
    public void doRegister(User user) {
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        //需要给用户密码加密，保证用户密码安全，使用md5加密
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        //保存用户
        this.userMapper.insert(user);
    }

    @Override
    public String doLogin(User user) {
        //根据用户名和密码查询用户
        //设置密码加密，因为数据使用md5加密的
        user.setUsername(user.getUsername());
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        User result = this.userMapper.selectOne(user);
        //判断用户是否为空，如果不为空，表示登录成功
        if (result != null){
            try {
                //生成唯一数ticket，可以使用redis的唯一数+用户id
                String ticket = ""+this.redisUtils.incr(this.SSO_TAOTAO_TICKET_INCR)+result.getId();
                //把ticket和用户数据放到redis中，模拟session，原来的session有效时间为半小时
                this.redisUtils.set(this.SSO_TICKET_KEY+ticket,MAPPER.writeValueAsString(result),60*30);
                //返回ticket
                return ticket;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        //如果查询的用户为空，返回空
        return null;
    }

}
