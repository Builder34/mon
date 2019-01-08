package com.builder.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.builder.common.utils.xss.SQLFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数(适配mybatis-plus分页)
 *
 * @author Builder
 * @email lcbiao34@gmail.com
 * @since 2018-07-14 10:21:45
 */
public class Query<T> extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页
     */
    private int current = 1;
    /**
     * 每页大小
     */
    private int size = 10;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        if(params.get("current") != null){
            current = Integer.parseInt(params.get("current").toString());
            params.remove("current");
        }
        if(params.get("size") != null){
            size = Integer.parseInt(params.get("size").toString());
            params.remove("size");
        }

        this.put("offset", (current - 1) * size);
        this.put("current", current);
        this.put("size", size);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String sidx = SQLFilter.sqlInject((String)params.get("sidx"));
        String order = SQLFilter.sqlInject((String)params.get("order"));
        this.put("sidx", sidx);
        this.put("order", order);

        //mybatis-plus分页
        this.page = new Page<>(current, size);

        //排序
        if(StringUtils.isNotBlank(sidx) && StringUtils.isNotBlank(order)){
            this.page.setOrderByField(sidx);
            this.page.setAsc("ASC".equalsIgnoreCase(order));
        }

    }

    public Page<T> getPage() {
        return page;
    }

    public int getCurrent() {
        return current;
    }

    public int getSize() {
        return size;
    }
}
