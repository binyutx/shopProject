/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ItemCatController
 * Author:   kepad
 * Date:     2018/2/4 11:01
 * Description: 查询商品类目分页
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.controller;

import com.taotao.manager.pojo.ItemCat;
import com.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈查询商品类目分页〉
 *
 * @author kepad
 * @create 2018/2/4
 * @since 1.0.0
 */

@Controller
@RequestMapping(value = "item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "query/{page}")
    @ResponseBody
    public List<ItemCat> queryItemCatPage(
            @PathVariable(value = "page")Integer page,
            @RequestParam(value = "rows")Integer rows){
        //List<ItemCat> list = this.itemCatService.queryItemCatByPage(page, rows);
        List<ItemCat> list = this.itemCatService.queryListByPage(page, rows);
        return list;

    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ItemCat> queryItemCatByParentId(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<ItemCat> list = this.itemCatService.queryItemCatByParentId(parentId);
        return list;
    }
}
