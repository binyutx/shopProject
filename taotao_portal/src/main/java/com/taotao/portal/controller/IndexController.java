package com.taotao.portal.controller; /**
 * 〈一句话功能简述〉<br>
 * 〈门户首页controller〉
 *
 * @author kepad
 * @create 2018/2/5
 * @since 1.0.0
 */

import com.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @创建人 kepade
 * @创建时间 2018/2/5
 * @描述
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {
    @Autowired
    private ContentService contentService;
    @Value("${TAOTAO_AD_ID}")
    private Long TAOTAO_AD_ID;
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toIndex(Model model){
        //调用内容服务，查询大广告数据，大广告分类id为31
        String AD = this.contentService.queryAd(this.TAOTAO_AD_ID);
        // 把大广告轮播数据放到model中，传递到前台页面
        model.addAttribute("AD",AD);
        return "index";
    }
}
