/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserService
 * Author:   kepad
 * Date:     2018/2/10 4:19
 * Description: 单点登录的service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.sso.service;

import com.taotao.manager.pojo.User;

/**
 * 〈一句话功能简述〉<br> 
 * 〈单点登录的service〉
 *
 * @author kepad
 * @create 2018/2/10
 * @since 1.0.0
 */
public interface UserService  {

    /**
     * 校验用户是否存在
     * @param param
     * @param type
     * @return
     */
    Boolean check(String param, Integer type);

    User queryUserByTicket(String ticket);

    /**
     * 注册用户
     * @param user
     */
    void doRegister(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    String doLogin(User user);
}
