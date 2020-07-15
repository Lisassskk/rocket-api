package com.github.alenfive.rocketapi.config;

import com.github.alenfive.rocketapi.controller.ApiController;
import com.github.alenfive.rocketapi.controller.ViewController;
import com.github.alenfive.rocketapi.datasource.DataSourceManager;
import com.github.alenfive.rocketapi.extend.*;
import com.github.alenfive.rocketapi.function.*;
import com.github.alenfive.rocketapi.script.GroovyScriptParse;
import com.github.alenfive.rocketapi.script.IScriptParse;
import com.github.alenfive.rocketapi.service.ScriptParseService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 自动配置类
 */
@Configuration
@EnableConfigurationProperties({RocketApiProperties.class})
@ConditionalOnBean(DataSourceManager.class)
public class RocketApiAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1允许任何域名使用
        corsConfiguration.addAllowedOrigin("*");
        // 2允许任何头
        corsConfiguration.addAllowedHeader("*");
        // 3允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean
    public ApiController getApiController(){
        return new ApiController();
    }

    @Bean
    @ConditionalOnMissingBean
    public ViewController getViewController(){
        return new ViewController();
    }

    @Bean
    @ConditionalOnMissingBean
    public QLRequestMappingFactory getQLRequestMappingFactory(){
        return new QLRequestMappingFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultApiPager getApiPager(){
        return new DefaultApiPager();
    }

    @Bean
    @ConditionalOnMissingBean
    public ScriptParseService getScriptParseService(){
        return new ScriptParseService();
    }

    @Bean
    @ConditionalOnMissingBean
    public IResultWrapper getIResultWrapper(){
        return new DefaultResultWrapper();
    }
    @Bean
    @ConditionalOnMissingBean
    public DefaultAssertException getDefaultAssertException(){
        return new DefaultAssertException();
    }

    @Bean
    @ConditionalOnMissingBean
    public IScriptParse getIScriptParse(){
        return new GroovyScriptParse();
    }

    @Bean
    @ConditionalOnMissingBean
    public IUserAuthorization getIUserAuthorization(){
        return new DefaultUserAuthorization();
    }

    @Bean
    @ConditionalOnMissingBean
    public ApiInfoContent getApiInfoContent(){
        return new ApiInfoContent();
    }

    @Bean
    @ConditionalOnMissingBean
    public AssertFunction getAssertFunction(){
        return new AssertFunction();
    }


    @Bean
    @ConditionalOnMissingBean
    public DbFunction getDbFunction(){
        return new DbFunction();
    }

    @Bean
    @ConditionalOnMissingBean
    public EnvFunction getEnvFunction(){
        return new EnvFunction();
    }

    @Bean
    @ConditionalOnMissingBean
    public ExportFunction getExportFunction(){
        return new ExportFunction();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogFunction getLogFunction(){
        return new LogFunction();
    }

    @Bean
    @ConditionalOnMissingBean
    public PagerFunction getPagerFunction(){
        return new PagerFunction();
    }

    @Bean
    @ConditionalOnMissingBean
    public UtilsFunction getUtilsFunction(){
        return new UtilsFunction();
    }

}