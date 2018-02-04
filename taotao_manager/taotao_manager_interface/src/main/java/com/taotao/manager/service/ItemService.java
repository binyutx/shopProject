/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ItemService
 * Author:   kepad
 * Date:     2018/2/4 17:48
 * Description: 商品的service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.service;
/**
 * 〈一句话功能简述〉<br> 
 * 〈商品的service〉
 *
 * @author kepad
 * @create 2018/2/4
 * @since 1.0.0
 */

import com.taotao.manager.pojo.Item;

/**
 * @创建人 kepade
 * @创建时间 2018/2/4
 * @描述
 */

public interface ItemService extends BaseService<Item> {
    /**
     *  保存商品
     * @param item
     */
    void saveItem(Item item,String desc);
}
