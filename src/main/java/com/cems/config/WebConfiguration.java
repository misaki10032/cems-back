package com.cems.config;


import com.cems.filter.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        System.out.println("开始拦截");
        registry.addInterceptor(new TokenInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns(
                        "/web/userlogin"
                        , "/web/appeal"
                        , "/web/register"
                        , "/web/forgetPsw"
                        , "/web/forgetPswOk"
                        , "/uniApp/*"
                        , "/uniApp/getEnts"
                );
    }
}

