/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserMapper
 * Author:   kepad
 * Date:     2018/2/3 16:41
 * Description: 测试接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.model.User;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试接口〉
 *
 * @author kepad
 * @create 2018/2/3
 * @since 1.0.0
 */


public interface UserMapper {
    //根据id查询用户
    User queryUserById(Long id);
    //增加用户
    void saveUser(User user);
    //更新用户
    void updateUserByid(User user);
    //删除用户
    void deleteUserById(Long id);
}
