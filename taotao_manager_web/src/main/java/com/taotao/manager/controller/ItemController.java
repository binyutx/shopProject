package com.taotao.manager.controller; /**
 * 〈一句话功能简述〉<br>
 * 〈商品的controller〉
 *
 * @author kepad
 * @create 2018/2/4
 * @since 1.0.0
 */

import com.taotao.manager.pojo.Item;
import com.taotao.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @创建人 kepade
 * @创建时间 2018/2/4
 * @描述
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping
    @ResponseBody
    public String saveItem(Item item,String desc){
        String msg="0";
       try {
           itemService.saveItem(item,desc);
       }catch (Exception e){
            msg="1";
            e.printStackTrace();
       }
       return msg;
    }
}
