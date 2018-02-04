/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: BaseService
 * Author:   kepad
 * Date:     2018/2/4 15:27
 * Description: 通用service接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈通用service接口〉
 *
 * @author kepad
 * @create 2018/2/4
 * @since 1.0.0
 */


public interface BaseService<T> {
    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    T queryById(Long id);

    /**
     * 查询所有数据
     * @return
     */
    List<T> queryAll();

    /**
     * 根据条件查询条数
     * @param t
     * @return
     */
    Integer queryCountByWhere(T t);

    /**
     * 根据条件查询数据
     * @param t
     * @return
     */
    List<T> queryListByWhere(T t);

    /**
     * 分页查询数据
     * @param page
     * @param rows
     * @return
     */
    List<T> queryListByPage(Integer page,Integer rows);

    /**
     * 根据条件查询一条数据
     * @param t
     * @return
     */
    T queryOne(T t);

    /**
     * 新增
     * @param t
     */
    void save(T t);

    /**
     * 新增忽略空参数
     * @param t
     */
    void saveSelective(T t);

    /**
     * 根据主键更新
     * @param t
     */
    void updateById(T t);

    /**
     * 根据主键更新，忽略空参数
     * @param t
     */
    void updateByIdSelective(T t);

    /**
     * 根据id删除数据
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据ids批量删除数据
     * @param ids
     */
    void deleteByIds(List<Object> ids);
}
