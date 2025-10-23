package com.example.common.util;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommonUtil {

    public String getPortalName() {
        return "스프링 부트 포털";
    }
    
    public String getVersion() {
        return "1.0.0";
    }
    
    /**
     * 네비게이션 바 항목을 반환합니다.
     * @return 네비게이션 바 항목 목록
     */
    public List<Map<String, String>> getNavigationItems() {
        List<Map<String, String>> navItems = new ArrayList<>();
        
        // 포털 홈
        Map<String, String> home = new HashMap<>();
        home.put("name", "홈");
        home.put("url", "http://localhost:8087");
        home.put("icon", "fa-home");
        navItems.add(home);
        
        // 모듈 1
        Map<String, String> module1 = new HashMap<>();
        module1.put("name", "모듈 1");
        module1.put("url", "http://localhost:8081/module1/");
        module1.put("icon", "fa-cube");
        navItems.add(module1);
        
        // 모듈 2
        Map<String, String> module2 = new HashMap<>();
        module2.put("name", "모듈 2");
        module2.put("url", "http://localhost:8082/module2/");
        module2.put("icon", "fa-cogs");
        navItems.add(module2);

        // 모듈 3
        Map<String, String> module3 = new HashMap<>();
        module3.put("name", "모듈 3");
        module3.put("url", "http://localhost:8083/module3/");
        module3.put("icon", "fa-cogs");
        navItems.add(module3);

        // 모듈 4
        Map<String, String> module4 = new HashMap<>();
        module4.put("name", "모듈 4");
        module4.put("url", "http://localhost:8084/module4/");
        module4.put("icon", "fa-cogs");
        navItems.add(module4);
        
        return navItems;
    }
} 