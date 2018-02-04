/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: BaseServiceImpl
 * Author:   kepad
 * Date:     2018/2/4 15:40
 * Description: Base实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.serviceimpl;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.taotao.manager.pojo.BasePojo;
import com.taotao.manager.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Base实现类〉
 *
 * @author kepad
 * @create 2018/2/4
 * @since 1.0.0
 */

public class BaseServiceImpl<T extends BasePojo> implements BaseService<T> {
    @Autowired
    private Mapper<T> mapper;
    private Class<T> clazz;

    public BaseServiceImpl(){
        //获取父类的type（类型）
        Type type = this.getClass().getGenericSuperclass();
        //强转为ParameterizedType，可以使用获取泛型类型的方法
        ParameterizedType pType = (ParameterizedType) type;
        //获取泛型的class
        this.clazz = (Class<T>) pType.getActualTypeArguments()[0];
    }

    @Override
    public T queryById(Long id) {
        T t = this.mapper.selectByPrimaryKey(id);
        return t;
    }

    @Override
    public List<T> queryAll() {
        List<T> list = this.mapper.select(null);
        return list;
    }

    @Override
    public Integer queryCountByWhere(T t) {
        int i = this.mapper.selectCount(t);
        return i;
    }

    @Override
    public List<T> queryListByWhere(T t) {
        List<T> list = this.mapper.select(t);
        return list;
    }

    @Override
    public List<T> queryListByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<T> list = this.mapper.select(null);
        return list;
    }

    @Override
    public T queryOne(T t) {
        T result = this.mapper.selectOne(t);
        return result;
    }

    @Override
    public void save(T t) {
        //需要判断，如果调用者没有设置时间，则在这里设置，如果设置了时间，则这里就不设置了
        if (t.getCreated() == null){
            t.setCreated(new Date());
            t.setUpdated(t.getCreated());
        }else if (t.getUpdated() == null){
            t.setUpdated(t.getCreated());
        }
        this.mapper.insert(t);
    }

    @Override
    public void saveSelective(T t) {
        //需要判断，如果调用者没有设置时间，则在这里设置，如果设置了时间，则这里就不设置了
        if (t.getCreated() == null){
            t.setCreated(new Date());
            t.setUpdated(t.getCreated());
        }else if (t.getUpdated() == null){
            t.setUpdated(t.getCreated());
        }

        this.mapper.insertSelective(t);
    }

    @Override
    public void updateById(T t) {
        //更新方法直接设置时间
        t.setUpdated(new Date());
        this.mapper.updateByPrimaryKey(t);
    }

    @Override
    public void updateByIdSelective(T t) {
        //更新方法直接设置时间
        t.setUpdated(new Date());
        this.mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public void deleteById(Long id) {
        this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(List<Object> ids) {
        //声明条件
        Example example = new Example(this.clazz);
        example.createCriteria().andIn("id",ids);
        this.mapper.deleteByExample(example);
    }

    public static void main(String[] args) {
        double a = 1.2;
        System.out.println(a*3);
        DecimalFormat format = new DecimalFormat("#.00");
        System.out.println(format.format(a*3));
    }
}
