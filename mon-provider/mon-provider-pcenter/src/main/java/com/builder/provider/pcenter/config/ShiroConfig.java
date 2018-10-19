package com.builder.provider.pcenter.config;

import com.builder.provider.pcenter.security.oauth2.OAuth2Filter;
import com.builder.provider.pcenter.security.oauth2.OAuth2Realm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置
 *
 * @author builder34
 * @email lcbiao34@gmail.com
 * @date 2017-04-20 18:33
 */
@Configuration
public class ShiroConfig {

    @Bean("sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        //sessionManager.setSessionIdCookieEnabled(false);
        return sessionManager;
    }

    /**
     * 权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类
     * */
    @Bean("securityManager")
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }

    /**
     * 是个factorybean，为了生成ShiroFilter。它主要保持了三项数据，
     * securityManager，filters，filterChainDefinitionManager
     * */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new OAuth2Filter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/api/**", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/**/*.css", "anon");
        filterMap.put("/**/*.js", "anon");
        filterMap.put("/img/*.png", "anon");
        filterMap.put("/img/*.jpg", "anon");
        filterMap.put("/**/*.html", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/plugins/**", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/", "anon");
        filterMap.put("/**", "oauth2");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 这个类是为了对密码进行编码的，防止密码在数据库里明码保存，
     * 当然在登陆认证的生活，这个类也负责对form里输入的密码进行编码
     * */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }
    /**
     * 缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，
     * 放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库
     * */
//    @Bean(name = "ehCacheManager")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public EhCacheManager ehCacheManager(){
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        return ehCacheManager;
//    }
    /**
     * Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理
     * DefaultAdvisorAutoProxyCreator这个类功能更为强大，这个类的奇妙之处是他实现了BeanProcessor接口,
     * 当ApplicationContext读如所有的Bean配置信息后，这个类将扫描上下文，寻找所有的Advistor
     * (一个Advisor是一个切入点和一个通知的组成)，将这些Advisor应用到所有符合切入点的Bean中
     * */
    @Bean
    //@DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * shiro里实现的Advisor类，内部使用
     * */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
