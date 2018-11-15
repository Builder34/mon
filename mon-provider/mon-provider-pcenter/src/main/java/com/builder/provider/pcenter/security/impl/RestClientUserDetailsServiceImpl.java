package com.builder.provider.pcenter.security.impl;

import com.builder.provider.pcenter.security.properties.OAuth2ClientProperties;
import com.builder.provider.pcenter.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * RestClientUserDetailsServiceImpl
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-13 17:06:15
 */
@Slf4j
@Component("restClientDetailsService")
public class RestClientUserDetailsServiceImpl implements ClientDetailsService {

    private ClientDetailsService clientDetailsService;
    @Autowired
    private SecurityProperties securityProperties;

    @PostConstruct
    public void init() {
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
        OAuth2ClientProperties[] clientProperties = securityProperties.getOauth2().getClients();
        if(ArrayUtils.isNotEmpty(clientProperties)) {
            for (OAuth2ClientProperties prop : clientProperties) {
                builder.withClient(prop.getClientId())
                        .secret(prop.getClientSecret())
                        .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                        .accessTokenValiditySeconds(prop.getAccessTokenValidateSeconds())
                        .refreshTokenValiditySeconds(prop.getRefreshTokenValiditySeconds())
                        .scopes(prop.getScope());
            }
        }
        try {
            clientDetailsService = builder.build();
        }catch (Exception e){
            log.error("init error={}", e.getMessage(), e);
        }
    }


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(clientId);
    }
}
