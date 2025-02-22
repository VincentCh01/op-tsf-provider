package com.zhiyunheyi.aibot.operate.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangyong
 * @description 跨域配置
 * @create 2023-07-05 01:13
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 使用setAllowedOrigin会出现IllegalArgumentException
//            config.addAllowedOriginPattern("*");
        config.addAllowedOrigin("https://beta.salebotai.com");
        config.addAllowedOrigin("https://alpha.salebotai.com");
        config.addAllowedOrigin("http://b.salebotai.com");
        config.addAllowedOrigin("https://b.salebotai.com");
        config.addAllowedOrigin("https://btest.salebotai.com");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
