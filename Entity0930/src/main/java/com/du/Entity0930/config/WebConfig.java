package com.du.Entity0930.config;

import com.du.Entity0930.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final DeptRepository deptRepository;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDeptConverter(deptRepository));
    }
}
