package com.hoserdude.toboot.dto;

import com.hoserdude.toboot.document.Todo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class TodoDtoList {

    private List<TodoDto> todoDto;

    /**
     * Always default constructors, no matter what your IDE says
     */
    public TodoDtoList() {
        this.todoDto = new ArrayList<TodoDto>();
    }

    public List<TodoDto> getTodoDto() {
        return todoDto;
    }

    public void setTodoDto(List<TodoDto> todoDto) {
        this.todoDto = todoDto;
    }

    public static TodoDtoList fromTodos(List<Todo> todos) {
        TodoDtoList result = new TodoDtoList();
        for(Todo todo : todos) {
            result.getTodoDto().add(TodoDto.fromTodo(todo));
        }
        return result;
    }
}
