package com.builder.provider.pcenter.service;


import com.builder.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author builder34
 * @description 代码生成Service
 * @createdate 2018-09-13 11:49:02
 * @contactemail lcbiao34@gmail.com
 */
public interface CodeGeneratorService {

    /**
     * 查询数据库中表
     * @param params 查询参数
     * @return 表集合分页类
     * */
    PageUtils queryTable(Map<String, Object> params);
    /**
     * 查询表信息
     * @param tableName 表名
     * @return 表信息
     * */
    Map<String, String> queryTableByName(String tableName);
    /**
     * 查询列信息
     * @param tableName 表名
     * @return 列信息
     * */
    List<Map<String, String>> queryColumns(String tableName);

    /**
     * 生成代码
     * @param moduleName 模块名称
     * @param tableNames 需要生成代码的表
     * @return 代码二进制数据
     * */
    byte[] generatorCode(String moduleName, String[] tableNames);
}
