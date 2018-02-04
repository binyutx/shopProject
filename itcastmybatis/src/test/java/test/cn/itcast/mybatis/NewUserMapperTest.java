/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: NewUserMapperTest
 * Author:   kepad
 * Date:     2018/2/3 21:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package test.cn.itcast.mybatis;

import cn.itcast.mybatis.mapper.NewUserMapper;
import cn.itcast.mybatis.model.User;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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


public class NewUserMapperTest {
    private NewUserMapper newUserMapper;
    @Before
    public void init() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        this.newUserMapper=sqlSession.getMapper(NewUserMapper.class);

    }
    @Test
    public void testSelectOne(){
        User param = new User();
        //param.setId(1l);
        param.setAge(22);
        User user = this.newUserMapper.selectOne(param);
        System.out.println(user);
    }

    @Test
    public void testSelectOnes(){
        User param = new User();
        param.setSex(1);
        List<User> list = this.newUserMapper.select(param);
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void testSelectCount(){
        User param = new User();
        param.setSex(1);
        int i = this.newUserMapper.selectCount(null);
        System.out.println(i);
    }
    @Test
    public void testSelectByPrimaryKey(){
        User user = this.newUserMapper.selectByPrimaryKey(1l);
        System.out.println(user);
    }
    @Test
    public void testInsert(){
        User user = new User();
        user.setId(null);
        user.setUser_name("caocao1");
        user.setName("曹操");
        user.setSex(1);
        this.newUserMapper.insert(user);
        System.out.println(user);

    }
    @Test
    public void testDelectUser(){
        User param = new User();
        param.setUser_name("caocao1");

        this.newUserMapper.delete(param);
    }
    @Test
   public void testUpdateUser(){
        User param = new User();
        param.setId(1l);
        param.setUser_name("caocao2");
        this.newUserMapper.updateByPrimaryKey(param);
   }
   @Test
   public void testUpdateUserd(){
        User user = new User();
        user.setId(1l);
        user.setUser_name("张三");
        user.setPassword("123456");
        user.setAge(23);

        this.newUserMapper.updateByPrimaryKeySelective(user);
   }
   @Test
   public void testSelectCountByExample(){
        //创建example ，构造器为javaBean的class
       Example example = new Example(User.class);
       //设置条件
       //获取设置条件的对象
       Example.Criteria criteria = example.createCriteria();
       //设置
       List<Object>list = new ArrayList<Object>();
       list.add(1l);
       list.add(2l);
       //就是说可以进行封装多个条件查询
       criteria.andIn("id",list);
       int i = this.newUserMapper.selectCountByExample(example);
       System.out.println(i);
   }
    @Test
    public void testSelectCountByExample1(){
        //创建example ，构造器为javaBean的class
        Example example = new Example(User.class);
        //设置条件
        //获取设置条件的对象
        Example.Criteria criteria = example.createCriteria();
        //设置
        List<Object>list = new ArrayList<Object>();
        list.add(1l);
        list.add(2l);
        //就是说可以进行封装多个条件查询
        criteria.andIn("id",list);
        List<User>list1 = this.newUserMapper.selectByExample(example);
        for (User user : list1) {
            System.out.println(user);
        }
    }
    @Test
    public void testUpdateUserExample(){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        List<Object>ids = new ArrayList<Object>();
        ids.add(3l);
        ids.add(4l);
        criteria.andIn("id",ids);
        User user = new User();
        user.setName("刘备");
        int i = this.newUserMapper.updateByExampleSelective(user, example);
        System.out.println(i);
    }
    @Test
    public void testQueryUserByPage(){//分页查询数据 即 传入需要的参数 使用mapper.xml利用LIMIT关键字查询出结果
        Map<String,Integer>param = new HashMap<>();
        param.put("page",0);
        param.put("rows",3);
        List<User> list = this.newUserMapper.queryUserByPage(param);
        for (User user : list) {
            System.out.println(user);

        }
    }
    @Test
    public void testQueryUser(){
        PageHelper.startPage(1,2);
        List<User> list = this.newUserMapper.select(null);
        for (User user : list) {
            System.out.println(user);
        }
        PageInfo<User> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getPages());//查出可以分成3次 按照每次查询2条记录的话
        System.out.println(pageInfo.getTotal());//总记录数为6条

    }

}
