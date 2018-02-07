package com.taotao.manager.serviceimpl; /**
 * 〈一句话功能简述〉<br>
 * 〈内容的service实现〉
 *
 * @author kepad
 * @create 2018/2/5
 * @since 1.0.0
 */

import com.taotao.manager.pojo.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 kepade
 * @创建时间 2018/2/5
 * @描述
 */
@Service
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements ContentCategoryService {
    @Override
    public List<ContentCategory> queryContentCategoryByParentId(Long parentId) {
        //设置查询条件
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(parentId);
        //查询
        List<ContentCategory> list = super.queryListByWhere(contentCategory);
        return list;
    }

    @Override
    public ContentCategory saveContentCategory(ContentCategory contentCategory) {
        //保存新增节点
        contentCategory.setStatus(1);
        contentCategory.setIsParent(false);
        super.save(contentCategory);
        //查询当前新增节点的父节点
        ContentCategory parent = super.queryById(contentCategory.getParentId());
        //如果父节点为叶节点时，更新父节点的IsParent属性为true
        if (!parent.getIsParent()){
            parent.setIsParent(true);
            super.updateByIdSelective(parent);
        }
        return contentCategory;
    }

    @Override
    public void deleteContextCategory(Long parentId, Long id) {
        //计算出要删除的id列表
        List<Object> ids = new ArrayList<Object>();
        //本身节点加入删除列表
        ids.add(id);
        //查询当前要删除节点下的所有子节点
        getDeleteIds(id,ids);
        //删除当前节点下所有的子节点，包括本身
        super.deleteByIds(ids);
        //根据父节点ID查询子节点
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(parentId);
        //查询当前要删除的节点的父节点下还有没有子节点
        Integer countByWhere = super.queryCountByWhere(contentCategory);
        //如果查询结果为0，说明没有子节点
        if (countByWhere == 0){
            //更新当前父节点为叶子节点
            ContentCategory parent = new ContentCategory();
            parent.setId(parentId);
            parent.setIsParent(false);
            super.updateByIdSelective(parent);
        }
    }

    /**
     * 根据节点id查询所有子节点的id
     * @param id
     *      当前要删除的节点ID
     * @param ids
     *       子节点列表
     */
    private void getDeleteIds(Long id, List<Object> ids) {
        //根据父ID查询子节点
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(id);
        List<ContentCategory> list = super.queryListByWhere(contentCategory);
        //找到子节点
        if (list!=null && list.size() > 0 ){
            for (ContentCategory category : list) {
                //把子节点id返回
                ids.add(category.getId());
                //如果当前循环节点是个父节点
                if (category.getIsParent()){
                    //递归查询所有子节点
                    getDeleteIds(category.getId(),ids);
                }
            }
        }

    }
}
