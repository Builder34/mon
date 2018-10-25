package com.builder.provider.pcenter;

import com.builder.common.utils.JacksonUtil;
import com.builder.common.utils.RedisClientUtils;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JUnitTest
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-19 10:16:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JUnitTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JUnitTest.class);
    @Autowired
    StringEncryptor encryptor;
    @Autowired
    RedisClientUtils redisClient;

    @Test
    public void jasyptTest(){
        String str = encryptor.encrypt("lcbiao34@gmail.com");
        LOGGER.debug("==========encrypt str: {}", str);
    }

    @Test
    public void redisTest() throws Exception{
        //redisClient.set("luochengbiao_keys", "test");
        LOGGER.debug("==========keys : {}", JacksonUtil.encode(redisClient.keys("*")));
    }
}
