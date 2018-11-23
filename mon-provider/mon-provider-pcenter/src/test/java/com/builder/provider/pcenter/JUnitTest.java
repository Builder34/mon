package com.builder.provider.pcenter;

import com.builder.common.utils.JacksonUtil;
import com.builder.provider.pcenter.captcha.CaptchaBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * JUnitTest
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-15 22:44:31
 */
@Slf4j
public class JUnitTest {

    @Test
    public void encodePwd() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }
    @Test
    public void toStringTest() {
        CaptchaBean bean = new CaptchaBean("9527", 60);
        bean.setType("sms");
        log.info(bean.toString());
        log.info(ToStringBuilder.reflectionToString(bean));
        log.info("yyyy-MM-dd HH:mm:ss ==> {}", bean.getExpireTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        log.info("StandardCharsets.UTF_8.displayName(): {}", StandardCharsets.UTF_8.displayName());
        log.info("StandardCharsets.UTF_8.toString(): {}", StandardCharsets.UTF_8.toString());
    }

    @Test
    public void localDataTimeToJson() {
        CaptchaBean bean = new CaptchaBean("9527", 60);
        String json = JacksonUtil.encode2(bean);
        log.info("==> toJson: {}", json);
        CaptchaBean copyBean = JacksonUtil.decode2(json, CaptchaBean.class);
        log.info(ToStringBuilder.reflectionToString(copyBean));
    }
}
