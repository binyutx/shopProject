package com.taotao.manager.controller; /**
 * 〈一句话功能简述〉<br>
 * 〈〉商品查询接口 RESTful风格
 *
 * @author kepad
 * @create 2018/2/7
 * @since 1.0.0
 */

import com.taotao.manager.pojo.Item;
import com.taotao.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @创建人 kepade
 * @创建时间 2018/2/7
 * @描述
 */
// http://manager.taotao.com/rest/item/interface/{id}
@Controller
@RequestMapping(value = "/item/interface")
public class ItemInterfaceController {
    @Autowired
    private ItemService itemservice;
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    //返回的是ResponseEntity或者加上@ResponseBody注解效果是一样的，都可以设置
    public ResponseEntity<Item> queryItem(@PathVariable("id") Long id){
        try {
            Item item = this.itemservice.queryById(id);
            //查询成功，响应的状态码应该为200
            //可以设置HttpStatus枚举的ok
            //return ResonseEntity.status(HttpStatus.ok).body(Item);
            // 也可以使用ok（）方法，效果和上面一样
            return ResponseEntity.ok().body(item);
        }catch (Exception e){
            e.printStackTrace();
        }
        //如果有异常，设置状态码为500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    }
    // http://manager.taotao.com/rest/item/interface
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item){
        try {
            this.itemservice.save(item);
            //添加成功，设置响应码为200
            //新增成功不需要有返回内容,可以使用build()方法
            //return ResponseEntity.status(HttpStatus.CREATED).build();
            //也可以设置body，参数为null即可
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        //如果有异常，设置状态码为500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    // http://manager.taotao.com/rest/item/interface
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateItemById(Item item){
        try {
            this.itemservice.updateByIdSelective(item);
            //204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        //如果有异常，设置状态码为500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    // http://manager.taotao.com/rest/item/interface/{id}
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteItemById(@PathVariable(value = "id") Long id){
        try {
            this.itemservice.deleteById(id);
            //返回204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        //如果有异常，设置状态码为500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
