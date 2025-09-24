package com.du.jpa0924;

import com.du.jpa0924.entity.MyData;
import com.du.jpa0924.repository.MyDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Jpa0924ApplicationTests {
    @Autowired
    private MyDataRepository myDataRepository;

    @Test
    void contextLoads() {  // insert
        MyData myData = MyData.builder()
                .name("이길동")
                .age(25)
                .email("hong3@korea.com")
                .memo("테스트입니다.")
                .build();
        myDataRepository.save(myData);
    }

    @Test
    void contextLoads_findById() {
        Optional<MyData> myData = myDataRepository.findById(1L);
        System.out.println(myData.get().getName());
    }

    @Test
    void contextLoads_findAll() {
        List<MyData> myData = myDataRepository.findAll();
        System.out.println(myData);
    }

}
