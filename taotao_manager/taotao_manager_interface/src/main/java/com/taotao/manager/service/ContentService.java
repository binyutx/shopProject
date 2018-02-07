/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ContentService
 * Author:   kepad
 * Date:     2018/2/5 22:16
 * Description: 内容的service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.service;
/**
 * 〈一句话功能简述〉<br> 
 * 〈内容的service〉
 *
 * @author kepad
 * @create 2018/2/5
 * @since 1.0.0
 */

import com.taotao.manager.pojo.Content;
import com.taotao.manager.utils.EsayUIResult;

/**
 * @创建人 kepade
 * @创建时间 2018/2/5
 * @描述
 */

public interface ContentService extends BaseService<Content> {
    /**
     * 根据分类id分页查询内容
     * @param page
     * @param rows
     * @param cateforyId
     * @return
     */
    EsayUIResult<Content> queryContentByPage(Integer page, Integer rows, Long cateforyId);

    /**
     * 根据内容分类查询内容
     * @param categoryId
     * @return
     */
    String queryAd(Long categoryId);
}
