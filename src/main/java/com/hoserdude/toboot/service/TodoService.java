package com.hoserdude.toboot.service;

import com.hoserdude.toboot.document.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos(String userId);
    Todo saveTodo(Todo todo);
    Todo getTodoById(String userId, String id);
    void deleteTodo(String id);
}
