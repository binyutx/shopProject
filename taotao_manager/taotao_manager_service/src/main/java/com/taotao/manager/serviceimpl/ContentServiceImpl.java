package com.taotao.manager.serviceimpl; /**
 * 〈一句话功能简述〉<br>
 * 〈内容的service实现〉
 *
 * @author kepad
 * @create 2018/2/5
 * @since 1.0.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.pojo.Content;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.redis.RedisUtils;
import com.taotao.manager.service.ContentService;
import com.taotao.manager.utils.EsayUIResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 kepade
 * @创建时间 2018/2/5
 * @描述
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {
    @Override
    public EsayUIResult<Content> queryContentByPage(Integer page, Integer rows, Long cateforyId) {
        //设置分页
        PageHelper.startPage(page,rows);
        //结果集
        List<Content> list = super.queryListByWhere(null);
        //获取分页数据
        PageInfo<Content> pageInfo = new PageInfo<Content>(list);
        //保证返回对象
        EsayUIResult<Content> esayUIResult = new EsayUIResult<>();
        esayUIResult.setRows(list);
        esayUIResult.setTotal(pageInfo.getTotal());
        return esayUIResult;


    }
    @Autowired
    private RedisUtils redisUtils;
    //使用jackson工具类把对象转换为json数据
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${TAOTAO_PORTAL_BIG_AD_KEY}")
    private String TAOTAO_PORTAL_BIG_AD_KEY;
    @Override
    public String queryAd(Long categoryId) {
        //从缓存中命中
        //为了更好的管理和维护redis，需要redis的key有意义
        try {
            String redisJson = this.redisUtils.get(this.TAOTAO_PORTAL_BIG_AD_KEY);
            //判断是否为空，如果不为空表示命中了，直接返回
            if (StringUtils.isNotBlank(redisJson)){
                return redisJson;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //如果没有命中，执行原有的逻辑，查mysql
        //根据分类id查询内容
        Content param = new Content();
        param.setCategoryId(categoryId);
        List<Content> list = super.queryListByWhere(param);
        //遍历内容，把内容封装到List<Map>中，根据前端数据格式进行封装
        //声明容器存放内容
        List<Map<String ,Object>> results = new ArrayList<Map<String ,Object>>();
        for (Content content : list) {
            Map<String , Object> map = new HashMap<String , Object>();
            map.put("srcB",content.getPic());
            map.put("height",240);
            map.put("alt","");
            map.put("width",670);
            map.put("src",content.getPic());
            map.put("widthB",550);
            map.put("href",content.getUrl());
            map.put("heightB",240);
            //把封装好的map放到list容器中
            results.add(map);
        }
        String json = "";
        try {
            json = MAPPER.writeValueAsString(results);
            this.redisUtils.set(this.TAOTAO_PORTAL_BIG_AD_KEY,json);
        }catch (Exception e){
            e.printStackTrace();
        }

        return json;
    }
}
