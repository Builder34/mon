package com.builder.common.utils;

import com.builder.common.utils.constant.Constant;
import com.builder.common.utils.constant.DateConstant;
import com.builder.common.utils.constant.TemplateConstant;
import com.builder.common.utils.exception.MBException;
import com.builder.common.utils.generator.ColumnEntity;
import com.builder.common.utils.generator.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @CreateTime 2018-09-13 14:40:08
 * @Description 代码生成器工具类
 * @Contactemail lcbiao34@gmail.com
 * @Author builder34
 */
public class CodeGeneratorUtil {


    public static void generator(Map<String, String> table, List<Map<String, String>> columns, ZipOutputStream zip) {
        generator(null, table, columns, zip);
    }
    /**
     * 生成代码
     * @param moduleName 模块名称
     * @param table 表
     * @param columns 列集合
     * @param zip zip输出流
     * */
    public static void generator(String moduleName, Map<String, String> table, List<Map<String, String>> columns, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        BeanUtils.copyProperties(table, tableEntity);
        //表名转换成Java类名
        String className = tableToJavaName(tableEntity.getTableName(), config.getString("tablePrefix")) ;
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        //列信息
        List<ColumnEntity> columnEntityList = new ArrayList<>();
        for (Map<String, String> column : columns){
            ColumnEntity entity = new ColumnEntity();
            BeanUtils.copyProperties(column, entity);
            //列名转换成Java类属性名
            String attrName = columnToJava(entity.getColumnName());
            entity.setAttrName(attrName);
            entity.setAttrname(StringUtils.uncapitalize(attrName));
            //讲列数据类型转换成Java数据类型
            String attrType = config.getString(entity.getAttrType(), "unkonwType");
            entity.setAttrType(attrType);
            if (!hasBigDecimal && attrType.equals("BigDecimal" )) {
                hasBigDecimal = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey" )) && tableEntity.getPk() == null) {
                tableEntity.setPk(entity);
            }
            columnEntityList.add(entity);
        }
        //设置列
        tableEntity.setColumns(columnEntityList);
        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
        Velocity.init(prop);
        String mainPath = config.getString("mainPath" );
        mainPath = org.apache.commons.lang.StringUtils.isBlank(mainPath) ? "com.builder" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getTableComment());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package" ));
        if (StringUtils.isNotBlank(moduleName)){
            map.put("moduleName", config.getString("moduleName" ));
        }
        map.put("author", config.getString("author" ));
        map.put("email", config.getString("email" ));
        map.put("datetime", DateFormatUtils.format(new Date(), DateConstant.DATE_TIME_PATTERN));
        //velocity上下文
        VelocityContext context = new VelocityContext(map);
        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constant.UTF_8);
            tpl.merge(context, sw);
            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName())));
                IOUtils.write(sw.toString(), zip, Constant.UTF_8);
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            }catch (IOException e){
                throw new MBException("渲染模板失败，表名："+tableEntity.getTableName(), e);
            }
        }
    }

    /**
     * 获取代码模板集
     * */
    public static List<String> getTemplates(){
        //后端java文件
        List<String> templates = new ArrayList<>();
        templates.add(TemplateConstant.ENTITY);
        templates.add(TemplateConstant.DAO);
        templates.add(TemplateConstant.SERVICE);
        templates.add(TemplateConstant.SERVICE_IMPL);
        templates.add(TemplateConstant.CONTROLLER);
        //前端vue文件
        templates.add(TemplateConstant.INDEX_VUE);
        templates.add(TemplateConstant.INDEX_JS);
        templates.add(TemplateConstant.API_JS);

        return templates;
    }
    /**
     * 读取代码生成配置
     * */
    public static Configuration getConfig(){
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new MBException("获取配置文件generator.properties出错");
        }
    }
    /**
     * 表名转换成Java类名
     * @param tableName 表名
     * @param tablePrefix 表名前缀
     * @return Java类名
     */
    public static String tableToJavaName(String tableName, String tablePrefix) {
        if(StringUtils.isNotBlank(tablePrefix)){
            tableName = tableName.replace(tableName, "");
        }
        return WordUtils.capitalizeFully(tableName, new char[]{'_'}).replace("_", "" );
    }
    /**
     * 列名转换成Java属性名
     * @param columnName 列名
     * @return Java类属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "" );
    }

    /**
     * 获取文件名路径
     * @param template 模板路径
     * @param className 类名
     * @return 文件名路径
     * */
    public static String getFileName(String template, String className) {
        String packagePath = "code" + File.separator;

        if (template.contains(TemplateConstant.ENTITY)) {
            return packagePath + className + "Entity.java";
        }

        if (template.contains(TemplateConstant.DAO)) {
            return packagePath + className + "Dao.java";
        }

        if (template.contains(TemplateConstant.SERVICE)) {
            return packagePath + className + "Service.java";
        }

        if (template.contains(TemplateConstant.SERVICE_IMPL)) {
            return packagePath + className + "ServiceImpl.java";
        }

        if (template.contains(TemplateConstant.CONTROLLER)) {
            return packagePath + className + "Controller.java";
        }
        if (template.contains(TemplateConstant.INDEX_VUE)) {
            return packagePath + StringUtils.uncapitalize(className) + ".vue";
        }
        if (template.contains(TemplateConstant.API_JS)) {
            return packagePath + StringUtils.uncapitalize(className) + ".js";
        }
        if (template.contains(TemplateConstant.INDEX_JS)) {
            return packagePath + "index.js";
        }
        return null;
    }

}
