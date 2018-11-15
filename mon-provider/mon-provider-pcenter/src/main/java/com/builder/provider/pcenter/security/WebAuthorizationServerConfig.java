package com.builder.provider.pcenter.security;

import com.builder.provider.pcenter.security.impl.RestClientUserDetailsServiceImpl;
import com.builder.provider.pcenter.security.handler.WebLogoutSuccessHandler;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import java.util.List;

/**
 * MonAuthorizationServerConfig web端授权服务配置
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-02 10:49:45
 */
@Configuration
@EnableAuthorizationServer
public class WebAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private RestClientUserDetailsServiceImpl restClientUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired(required = false)
    private  TokenEnhancer jwtTokenEnhancer;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(restClientUserDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .userDetailsService(userDetailsService);

        if(jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain chain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = Lists.newArrayList();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            chain.setTokenEnhancers(enhancers);
            endpoints.tokenEnhancer(chain).accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * web端退出登录处理逻辑
     * */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new WebLogoutSuccessHandler();
    }
}
