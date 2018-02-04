/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestServiceImpl
 * Author:   kepad
 * Date:     2018/2/3 13:41
 * Description: 接口实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.serviceimpl;

import com.taotao.manager.mapper.TestMapper;
import com.taotao.manager.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈接口实现类〉
 *
 * @author kepad
 * @create 2018/2/3
 * @since 1.0.0
 */

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;
    @Override
    public String queryDate() {
        return testMapper.queryDate();
    }
}
