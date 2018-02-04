/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: NewUserMapper
 * Author:   kepad
 * Date:     2018/2/3 21:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.model.User;
import com.github.abel533.mapper.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author kepad
 * @create 2018/2/3
 * @since 1.0.0
 */


public interface NewUserMapper extends Mapper<User> {
    //分页查询
    List<User> queryUserByPage(Map<String ,Integer> param);
}
