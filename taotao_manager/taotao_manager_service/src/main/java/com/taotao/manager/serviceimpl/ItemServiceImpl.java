package com.taotao.manager.serviceimpl; /**
 * 〈一句话功能简述〉<br>
 * 〈商品的service实现〉
 *
 * @author kepad
 * @create 2018/2/4
 * @since 1.0.0
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;
import com.taotao.manager.utils.EsayUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public EsayUIResult<Item> queryItemList(Integer page, Integer rows) {
        //设置分页数据
        PageHelper.startPage(page,rows);
        List<Item> list = super.queryListByWhere(null);
        //获取分页详情数据
        PageInfo<Item> pageInfo = new PageInfo<>(list);
        //封装返回对象
        EsayUIResult<Item> esayUIResult = new EsayUIResult<>();
        esayUIResult.setTotal(pageInfo.getTotal());
        esayUIResult.setRows(list);
        return esayUIResult;
    }
}
