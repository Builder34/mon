package com.builder.common.core.util;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * RedisKeyGenerator redis key生成器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-24 22:48:52
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisKeyGenerator {

    private static final String ACCESS_TOKEN = "mon:token:accessToken:";

    public static String getAccessTokenKey(String token){
        Preconditions.checkArgument(StringUtils.isNotBlank(token), "token参数不可以为空");
        return ACCESS_TOKEN+token;
    }

}
