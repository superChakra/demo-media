package com.superchakra.train.config;


import com.superchakra.train.interceptor.ImageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {


/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ImageInterceptor())
                .addPathPatterns("/file/avatar/**")
                .addPathPatterns("/file/image/**");
    }*/
}
