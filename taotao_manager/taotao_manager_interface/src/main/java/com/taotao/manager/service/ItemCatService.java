/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ItemCatService
 * Author:   kepad
 * Date:     2018/2/4 10:53
 * Description: 商品类目service接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.service;

import com.taotao.manager.pojo.ItemCat;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品类目service接口〉
 *
 * @author kepad
 * @create 2018/2/4
 * @since 1.0.0
 */


public interface ItemCatService extends BaseService<ItemCat>{

    /**
     * 根据商品类目父id查询数据
     * @param parentId
     * @return
     */
    List<ItemCat> queryItemCatByParentId(Long parentId);

    /**
     * 功能描述: <br>
     * 〈〉
     *
     No such property: _1 for class: Script1
     * @param page 当前页
     * @param rows 每页显示数量
     * @return:
     * @since: 1.0.0
     * @Author:kepad
     * @Date: kepad 10:54

    List<ItemCat> queryItemCatByPage(Integer page,Integer rows);*/
}
