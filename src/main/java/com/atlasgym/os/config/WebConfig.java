package com.atlasgym.os.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AccessGuardInterceptor accessGuardInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessGuardInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/acceso", "/css/**", "/js/**", "/images/**", "/error");
    }
}
