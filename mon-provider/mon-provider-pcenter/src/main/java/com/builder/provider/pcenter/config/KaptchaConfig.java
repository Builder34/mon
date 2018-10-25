package com.builder.provider.pcenter.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * 生成验证码配置
 *
 * @author builder34
 * @email lcbiao34@gmail.com
 * @date 2017-04-20 19:22
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "blue");
        properties.put("kaptcha.textproducer.char.space", "5");
        //默认是Arial, Courier.有些服务器没安装此字体的话，生成的验证码图片会模糊
        properties.put("kaptcha.textproducer.font.names","宋体,楷体");
        //定制化
        properties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
        properties.put("kaptcha.textproducer.font.size","24");
        properties.put("kaptcha.image.width","110");
        properties.put("kaptcha.image.height","32");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
