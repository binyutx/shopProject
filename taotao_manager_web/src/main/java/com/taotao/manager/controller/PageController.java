/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PageController
 * Author:   kepad
 * Date:     2018/2/4 14:33
 * Description: 实现通用页面跳转的Controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈实现通用页面跳转的Controller〉
 *
 * @author kepad
 * @create 2018/2/4
 * @since 1.0.0
 */

@Controller
@RequestMapping(value = "/page")
public class PageController {

    @RequestMapping(value = "{pageName}")
    public String toPage(@PathVariable(value = "pageName")String pageName){
        return pageName;
    }
}
