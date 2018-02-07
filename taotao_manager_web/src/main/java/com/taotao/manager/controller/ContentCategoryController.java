package com.taotao.manager.controller;
/**
 * 〈一句话功能简述〉<br>
 * 〈内容的controller〉
 *
 * @author kepad
 * @create 2018/2/5
 * @since 1.0.0
 */

import com.taotao.manager.pojo.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;
import com.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @创建人 kepade
 * @创建时间 2018/2/5
 * @描述
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;
    /*
    * url : '/rest/content/category',
		animate: true,
		method : "GET",
    * */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ContentCategory> queryContentCategoryByParentId(
            @RequestParam(value = "id",defaultValue = "0") Long parentId){
        //调用服务查询
        List<ContentCategory> list = this.contentCategoryService.queryContentCategoryByParentId(parentId);
        return list;
    }

    /**
     * 新增分类
     * @param contentCategory
     * @return
     */
    // /rest/content/category/add",{parentId:node.parentId,name:node.text}
    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public ContentCategory saveContentCategory(ContentCategory contentCategory){
        ContentCategory result = null;
        try {
            result = contentCategoryService.saveContentCategory(contentCategory);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新
     * @param contentCategory
     * @return
     */
    // url: "/rest/content/category/update",
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(ContentCategory contentCategory){
        String msg = "0";
        try {
            contentCategoryService.updateByIdSelective(contentCategory);
        }catch (Exception e){
            e.printStackTrace();
            msg = "1";
        }
        return msg;
    }

    /**
     * 删除
     * @param parentId
     * @param id
     * @return
     */
    //parentId=${节点的父id}&id=${选中节点的id}
    //	   url: "/rest/content/category/delete",
    //	   data : {parentId:node.parentId,id:node.id},
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteContentCategory(Long parentId,Long id){
        String msg="0";
        try {
            contentCategoryService.deleteContextCategory(parentId,id);
        }catch (Exception e){
            e.printStackTrace();
            msg="1";
        }
        return msg;

    }
    
}
