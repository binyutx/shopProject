package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉通用跳转页面的方法
 *
 * @author kepad
 * @description com.taotao.portal.controller
 * @create 2018/2/11
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "page")
public class PageController {
    @RequestMapping(value = "{pageName}")
    public String toPage(@PathVariable(value = "pageName") String pageName){
        return pageName;
    }
}
