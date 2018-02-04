/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestController
 * Author:   kepad
 * Date:     2018/2/3 13:43
 * Description: web层实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.controller;

import com.taotao.manager.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 〈一句话功能简述〉<br> 
 * 〈web层实现〉
 *
 * @author kepad
 * @create 2018/2/3
 * @since 1.0.0
 */

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping("/date")
    public String queryDate(){
        return testService.queryDate();
    }
}
