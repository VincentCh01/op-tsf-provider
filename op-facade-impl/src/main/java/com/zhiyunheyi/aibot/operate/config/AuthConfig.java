package com.zhiyunheyi.aibot.operate.config;

import com.zhiyunheyi.aibot.operate.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangyong
 * @description 拦截配置
 * @create 2023-06-17 18:42
 */
@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        String[] swaggerExcludes = new String[]{"/swagger-ui.html", "/swagger-resources/**", "/webjars/**"};
        //注册运营后台拦截器2
        registry.addInterceptor(getAuthInterceptor())
                //所有路径都被拦截
                .addPathPatterns("/ucenter/**")
                //添加不拦截路径
                .excludePathPatterns(
                        "/ucenter/userInfo/selectByRoleIdWithoutAuth",
                        "/ucenter/userInfo/getByMobileWithoutAuth",
                        "/ucenter/resource/selectByRoleIdWithoutAuth",
                        "/**/*.html",
                        "/**/*.js",
                        "/**/*.css"
                ).excludePathPatterns(swaggerExcludes);
    }

    @Bean
    public AuthInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }
}
