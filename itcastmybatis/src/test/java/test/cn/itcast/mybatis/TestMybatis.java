/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestMybatis
 * Author:   kepad
 * Date:     2018/2/3 17:00
 * Description: 连接测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package test.cn.itcast.mybatis;

import cn.itcast.mybatis.mapper.UserMapper;
import cn.itcast.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * 〈一句话功能简述〉<br> 
 * 〈连接测试〉
 *
 * @author kepad
 * @create 2018/2/3
 * @since 1.0.0
 */


public class TestMybatis {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    /**
     * 功能描述: <br>
     * 〈〉
     *根据id查询用户信息
     No such property: _1 for class: Script1
     * @return:
     * @since: 1.0.0
     * @Author:kepad
     * @Date: kepad 17:58
     */
    @Test
    public void testQueryUserById(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById((long) 1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void testSaveUser(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //String user_name, String password, String name, Integer age, Integer sex, Date birthday, Date created, Date updated
        User user = new User();
        user.setId(null);
        user.setName("小黑");
        user.setSex(2);
        user.setUser_name("xiaohei");
        mapper.saveUser(user);
        System.out.println(user);
        sqlSession.close();
    }
    @Test
    public void testUpdateUserById(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(7l);
        user.setName("小白");
        user.setSex(1);
        user.setUser_name("xiaobai");
        mapper.updateUserByid(user);
        System.out.println(user);
        sqlSession.close();
    }
    @Test
    public void testDeleteUserById(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUserById(7l);
        sqlSession.close();
    }
}
