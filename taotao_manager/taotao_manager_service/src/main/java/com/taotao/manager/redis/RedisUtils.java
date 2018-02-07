/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RedisUtils
 * Author:   kepad
 * Date:     2018/2/6 17:36
 * Description: redis整合
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.taotao.manager.redis;
/**
 * 〈一句话功能简述〉<br> 
 * 〈redis整合〉
 *
 * @author kepad
 * @create 2018/2/6
 * @since 1.0.0
 */

/**
 * @创建人 kepade
 * @创建时间 2018/2/6
 * @描述
 */

public interface RedisUtils {
    /**
     * 保存
     * @param key
     * @param value
     */
    void set(String key ,String value);

    /**
     * 根据key查询
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 删除
     * @param key
     */
    void del(String key);

    /**
     * 根据key设置生存时间
     * @param key
     * @param seconds
     */
    void expire(String key,Integer seconds);

    /**
     * 保存并设置生存时间
     * @param key
     * @param value
     * @param seconds
     */
    void set(String key,String value ,Integer seconds);

    /**
     *  value 加一
     * @param key
     * @return
     */
    Long incr(String key);
}
