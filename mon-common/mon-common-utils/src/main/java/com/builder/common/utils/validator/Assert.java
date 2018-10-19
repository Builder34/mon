package com.builder.common.utils.validator;

import com.builder.common.utils.exception.MBException;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据校验
 * @author builder34
 * @email lcbiao34@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new MBException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new MBException(message);
        }
    }
}
