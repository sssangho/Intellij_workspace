package com.du.test0925;

import com.du.test0925.domain.Todo;
import com.du.test0925.mapper.TodoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class Test0925ApplicationTests {

    @Autowired
    private TodoMapper toDoMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void insert() {
        Todo todo = new Todo();
        todo.setContent("국취제 상담");
        todo.setDueDate(LocalDate.now());
        todo.setCategory("중요");
        todo.setCompleted(false);

        toDoMapper.insert(todo);
    }

    @Test
    void findById() {
        toDoMapper.findById(1L);
        System.out.println(toDoMapper.findById(1L));
    }

    @Test
    void findAll() {
        List<Todo> todos = toDoMapper.findAll();
        System.out.println(todos);
    }

    @Test
    void update() {
        Todo todo = new Todo();
        todo.setContent("국취제 상담");
        todo.setDueDate(LocalDate.of(2025, 9 , 25));
        todo.setCategory("중요");
        todo.setCompleted(true);
        todo.setId(1L);

        toDoMapper.update(todo);
        System.out.println(toDoMapper.findById(1L));
    }

    @Test
    void delete() {
        toDoMapper.delete(1L);
        List<Todo> todos = toDoMapper.findAll();
        System.out.println(todos);

    }

}
