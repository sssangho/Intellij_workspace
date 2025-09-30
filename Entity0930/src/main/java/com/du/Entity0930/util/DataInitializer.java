package com.du.Entity0930.util;

import com.du.Entity0930.entity.Dept;
import com.du.Entity0930.entity.Emp;
import com.du.Entity0930.repository.DeptRepository;
import com.du.Entity0930.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final DeptRepository deptRepository;
    private final EmpRepository empRepository;

    @Override
    @Transactional
    public void run(org.springframework.boot.ApplicationArguments args) throws Exception {
        Dept d1 = Dept.builder().name("개발팀").build();
        Dept d2 = Dept.builder().name("인사팀").build();
        deptRepository.save(d1);
        deptRepository.save(d2);

        Emp e1 = Emp.builder().name("홍길동").email("hong@korea.com").dept(d1).build();
        Emp e2 = Emp.builder().name("이순신").email("lee@korea.com").dept(d2).build();
        empRepository.save(e1);
        empRepository.save(e2);
    }
}

