package com.taotao.manager.utils; /**
 * 〈一句话功能简述〉<br>
 * 〈EasyUI返回结果集bean〉
 *
 * @author kepad
 * @create 2018/2/5
 * @since 1.0.0
 */


import java.io.Serializable;
import java.util.List;

/**
 * @创建人 kepade
 * @创建时间 2018/2/5
 * @描述 easyui中datagrid需要的数据参数
 */

public class EsayUIResult<T> implements Serializable {
    //查询到的数据总条数
    private long total;
    //查询到的数据结果集
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
