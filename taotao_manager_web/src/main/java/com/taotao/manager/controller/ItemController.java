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
import com.taotao.manager.utils.EsayUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public EsayUIResult<Item> queryList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                        @RequestParam(value = "rows",defaultValue = "30")Integer rows){
        EsayUIResult<Item> esayUIResult = this.itemService.queryItemList(page,rows);
        return esayUIResult;

    }
}
