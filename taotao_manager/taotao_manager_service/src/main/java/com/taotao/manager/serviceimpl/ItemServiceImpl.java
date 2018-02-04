package com.taotao.manager.serviceimpl; /**
 * 〈一句话功能简述〉<br>
 * 〈商品的service实现〉
 *
 * @author kepad
 * @create 2018/2/4
 * @since 1.0.0
 */

import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @创建人 kepade
 * @创建时间 2018/2/4
 * @描述
 */
@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {
    @Autowired
    private ItemDescService itemDescService;
    @Override
    public void saveItem(Item item,String desc) {
        //保存商品
        item.setStatus(1);
        super.save(item);
        //保存商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        this.itemDescService.save(itemDesc);
    }
}
