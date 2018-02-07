package com.taotao.manager.controller; /**
 * 〈一句话功能简述〉<br>
 * 〈内容的controller〉
 *
 * @author kepad
 * @create 2018/2/5
 * @since 1.0.0
 */

import com.taotao.manager.pojo.Content;
import com.taotao.manager.service.ContentService;
import com.taotao.manager.utils.EsayUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @创建人 kepade
 * @创建时间 2018/2/5
 * @描述
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void SaveContent(Content content){
        //调用服务保存
        this.contentService.save(content);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public EsayUIResult<Content> queryContentByPage(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                    @RequestParam(value = "rows",defaultValue = "20")Integer rows,Long cateforyId){
                EsayUIResult<Content> result =  contentService.queryContentByPage(page,rows,cateforyId);
                return result;

    }
}
