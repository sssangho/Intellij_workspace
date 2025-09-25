package com.du.test0925.service;

import com.du.test0925.domain.Todo;
import com.du.test0925.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoMapper todoMapper;

    public List<Todo> findAll() {
        return todoMapper.findAll();
    }

    public Todo findById(Long id) {
        return todoMapper.findById(id);
    }

    public void insert(Todo todo) {
        todoMapper.insert(todo);
    }

    public void update(Todo todo) {
        todoMapper.update(todo);
    }

    public void delete(Long id) {
        todoMapper.delete(id);
    }

    @Transactional
    public void toggleCompleted(Long id) {
        Todo todo = todoMapper.findById(id);
        boolean newStatus = !todo.isCompleted();
        todoMapper.updateCompleted(id, newStatus); // DB 업데이트
    }

}
