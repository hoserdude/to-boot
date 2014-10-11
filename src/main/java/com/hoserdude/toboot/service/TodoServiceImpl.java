package com.hoserdude.toboot.service;

import com.hoserdude.toboot.aop.Instrumentable;
import com.hoserdude.toboot.document.Todo;
import com.hoserdude.toboot.documentRepository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    @Instrumentable
    public List<Todo> getTodos(String userId) {
        List<Todo> todos = todoRepository.findByUserId(userId);
        return todos;
    }

    @Override
    @Instrumentable
    public Todo saveTodo(Todo todo) {
        todo = todoRepository.save(todo);
        return todo;
    }

    @Override
    @Instrumentable
    public Todo getTodoById(String userId, String id) {
        return todoRepository.findByUserIdAndId(userId, id);
    }

    @Override
    @Instrumentable
    public void deleteTodo(String id) {
        todoRepository.delete(id);
    }
}
