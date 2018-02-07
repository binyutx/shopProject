/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ContentCategoryService
 * Author:   kepad
 * Date:     2018/2/5 22:15
 * Description: 内容分类service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.service;
/**
 * 〈一句话功能简述〉<br> 
 * 〈内容分类service〉
 *
 * @author kepad
 * @create 2018/2/5
 * @since 1.0.0
 */

import com.taotao.manager.pojo.ContentCategory;

import java.util.List;

/**
 * @创建人 kepade
 * @创建时间 2018/2/5
 * @描述
 */

public interface ContentCategoryService extends BaseService<ContentCategory> {
    /**
     * 根据parentId查询内容分类
     * @param parentId
     * @return
     */
    List<ContentCategory> queryContentCategoryByParentId(Long parentId);

    /**
     * 新增
     * @param contentCategory
     * @return
     */
    ContentCategory saveContentCategory(ContentCategory contentCategory);

    /**
     * 删除
     * @param parentId
     * @param id
     */
    void deleteContextCategory(Long parentId, Long id);
}
