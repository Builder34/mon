package com.builder.provider.pcenter.service.impl;

import com.builder.common.utils.CodeGeneratorUtil;
import com.builder.common.utils.PageUtils;
import com.builder.common.utils.Query;
import com.builder.provider.pcenter.dao.generator.CodeGeneratorDao;
import com.builder.provider.pcenter.service.CodeGeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @CreateTime 2018-09-13 11:51:44
 * @Description CodeGeneratorServiceImpl
 * @Contactemail lcbiao34@gmail.com
 * @Author builder34
 */
@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    @Autowired
    private CodeGeneratorDao codeGeneratorDao;

    @Override
    public PageUtils queryTable(Map<String, Object> params) {
        Query query = new Query(params);
        List<Map<String, Object>> list = codeGeneratorDao.queryList(query);
        int total = codeGeneratorDao.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getSize(), query.getCurrent());
        return pageUtil;
    }

    @Override
    public Map<String, String> queryTableByName(String tableName) {
        return codeGeneratorDao.queryTableByName(tableName);
    }

    @Override
    public List<Map<String, String>> queryColumns(String tableName) {
        return codeGeneratorDao.queryColumns(tableName);
    }

    @Override
    public byte[] generatorCode(String moduleName, String[] tableNames) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(out);
        for(String tableName : tableNames){
            Map<String,String> table = queryTableByName(tableName);
            List<Map<String, String>> columns = queryColumns(tableName);
            CodeGeneratorUtil.generator(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return out.toByteArray();
    }
}
