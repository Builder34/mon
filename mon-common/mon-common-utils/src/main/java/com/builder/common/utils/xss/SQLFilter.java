package com.builder.common.utils.xss;

import com.builder.common.utils.exception.MBException;
import org.apache.commons.lang3.StringUtils;

/**
 * SQL过滤
 * @author Builder34
 * @email lcbiao34@gmail.com
 * @date 2018-08-01 16:16:11
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new MBException("包含非法字符");
            }
        }

        return str;
    }
}
