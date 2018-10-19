package com.builder.provider.pcenter.dao.generator;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @CreateTime 2018-09-13 11:52:48
 * @Description CodeGeneratorDao
 * @Contactemail lcbiao34@gmail.com
 * @Author builder34
 */
@Mapper
public interface CodeGeneratorDao {
    /**
     * 查询数据库中表
     * @param params 查询参数
     * @return 表集合
     * */
    List<Map<String, Object>> queryList(Map<String, Object> params);
    /**
     * 查询总数
     * @param params 查询参数
     * @return 总数
     * */
    int queryTotal(Map<String, Object> params);
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

}
