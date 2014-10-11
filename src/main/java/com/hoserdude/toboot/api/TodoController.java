package com.hoserdude.toboot.api;

import com.hoserdude.toboot.document.Todo;
import com.hoserdude.toboot.domain.User;
import com.hoserdude.toboot.dto.TodoDto;
import com.hoserdude.toboot.dto.TodoDtoList;
import com.hoserdude.toboot.service.TodoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Get free stuff related to REST with this
@Controller
//All sub-mappings in this class will inherit this
//Our SecurityConfig forces any calls to /api/** to be authenticated
@RequestMapping("api/v1/todo")
//Documentation annotation for Swagger
@Api(value = "todo", description = "Todo API")
public class TodoController {

    public static final String APPLICATION_JSON_APPLICATION_XML = "application/json, application/xml";
    private static Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @Autowired
    private CounterService counterService;

    /**
     * Returns all the todos.  Hold on, how does it know what to send back to the client?
     * Serialized Binary? XML? JSON? HTML?  Well, like a good restaurant, you get what you
     * asked for - it's called content negotiation - Spring MVC will honor what the client
     * declares in the Accept HTTP header (eg: application/json, application/xml) and marshall
     * accordingly.  For free!
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get All Todos For User", notes = "Gets a lot of todos", response = TodoDtoList.class, produces = APPLICATION_JSON_APPLICATION_XML)
    public @ResponseBody
    ResponseEntity<TodoDtoList> get(Authentication authentication) {
        counterService.increment("api.todos.get");
        logger.info("Retrieving all Todos!");
        User user = (User) authentication.getPrincipal();
        final List<Todo> todos = todoService.getTodos(user.getUsername());
        return new ResponseEntity<TodoDtoList>(TodoDtoList.fromTodos(todos), HttpStatus.OK);
    }

    @ApiOperation(value = "Get a Todo by ID", notes = "Gets one todo", response = TodoDto.class, produces = APPLICATION_JSON_APPLICATION_XML)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TodoDto> getById(@PathVariable("id") @ApiParam(name = "id") String id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Todo todo = todoService.getTodoById(user.getUsername(), id);
        return new ResponseEntity<TodoDto>(TodoDto.fromTodo(todo), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a Todo", notes = "Updates a todo", response = TodoDto.class, produces = APPLICATION_JSON_APPLICATION_XML)
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<TodoDto> update(@RequestBody @ApiParam(name = "todo") TodoDto todoDto, Authentication authentication) {
        Todo todo = todoService.saveTodo(todoDto.toTodo());
        return new ResponseEntity<TodoDto>(TodoDto.fromTodo(todo), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Todo", notes = "Deletes a todo", response = TodoDto.class, produces = APPLICATION_JSON_APPLICATION_XML)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<String> delete(@PathVariable("id") @ApiParam(name = "id") String id, Authentication authentication) {
        todoService.deleteTodo(id);
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

    @ApiOperation(value = "Add a Todo", notes = "Adds a Todo to the system", response = TodoDto.class, produces = APPLICATION_JSON_APPLICATION_XML)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TodoDto> add(@RequestBody @ApiParam(name = "todo") TodoDto todoDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        //New entities need to be associated with the user.
        todoDto.setUserId(user.getUsername());
        //We get a new guy back because a new ID is generated
        Todo todo = todoService.saveTodo(todoDto.toTodo());
        return new ResponseEntity<TodoDto>(TodoDto.fromTodo(todo), HttpStatus.CREATED);
    }
}
