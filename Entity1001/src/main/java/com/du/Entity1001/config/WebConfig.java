package com.du.Entity1001.config;

import com.du.Entity1001.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final DeptRepository deptRepository;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDeptConverter(deptRepository));
    }

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // "/upload/**"로 오는 요청은 실제 파일 시스템 경로에서 제공
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:///" + uploadPath + "/");
    }
}
