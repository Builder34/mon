package com.builder.provider.pcenter.security.authentication;

import com.builder.provider.pcenter.security.impl.JwtTokenEnhancer;
import com.builder.provider.pcenter.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * TokenStoreConfig tokenStore配置
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 11:01:07
 */
@Configuration
public class TokenStoreConfig {

    /**
     * 使用redis存储token的配置，只有在mon.security.oauth2.tokenStore=redis时生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "mon.security.oauth2", name="tokenStore", havingValue = "redis")
    public static class RedisTsConfig {
        @Autowired
        private RedisConnectionFactory redisConnectionFactory;
        @Bean
        public TokenStore redisTokenStore() {
            return new RedisTokenStore(redisConnectionFactory);
        }
    }
    /**
     * 使用jwt存储token的配置，默认生效
     * */
    @Configuration
    @ConditionalOnProperty(prefix = "mon.security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTsConfig {
        @Autowired
        private SecurityProperties securityProperties;

        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
            return converter;
        }
        @Bean
        @ConditionalOnBean(TokenEnhancer.class)
        public TokenEnhancer jwtTokenEnhancer(){
            return new JwtTokenEnhancer();
        }
    }
}
