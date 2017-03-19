package com.leftjs.lfc;

import com.leftjs.lfc.auth.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jason on 2017/3/13.
 */
@Configuration
public class MyConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    private AuthorizationInterceptor interceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(interceptor).addPathPatterns("/api/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*", "http://localhost:3000").allowedMethods("PUT", "DELETE", "POST", "GET");
    }
}
