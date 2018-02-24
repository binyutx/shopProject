/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ItemCatServiceImpl
 * Author:   kepad
 * Date:     2018/2/4 10:56
 * Description: 商品类类目实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.service.impl;

import com.taotao.manager.pojo.ItemCat;
import com.taotao.manager.service.ItemCatService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品类类目实现类〉
 *
 * @author kepad
 * @create 2018/2/4
 * @since 1.0.0
 */

@Service
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {
    @Override
    public List<ItemCat> queryItemCatByParentId(Long parentId) {
        //设置查询条件
        ItemCat param = new ItemCat();
        param.setParentId(parentId);
        List<ItemCat> list = super.queryListByWhere(param);
        return list;
    }
   /* @Autowired
    private ItemCatMapper itemCatMapper;
    @Override
    public List<ItemCat> queryItemCatByPage(Integer page, Integer rows) {

        //设置分页条件
        PageHelper.startPage(page,rows);
        //查询
        List<ItemCat> list = this.itemCatMapper.select(null);
        return list;
    }*/
}
