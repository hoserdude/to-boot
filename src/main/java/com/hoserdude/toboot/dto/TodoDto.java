package com.hoserdude.toboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoserdude.toboot.document.Todo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Why have a DTO *and* a Domain object? Because this is not a toy app!
 * Your client model will always be different than your data model. Give
 * the client what they need, and keep what you need private.
 *
 * This DTO reflects the model that the JS/API client uses, so we abide.
 */
@XmlRootElement
public class TodoDto {

    /**
     * Id for this in persistence world
     */
    private String id;

    /**
     * This is not a single user web app, todos belong to somebody right?
     * We don't send it back to the client, it's kinda private info.
     */
    @JsonIgnore
    private String userId;

    /**
     * I would have called it description, but the customer is always right.
     */
    private String title;

    /**
     * we can all agree on this one.
     */
    private boolean completed;

    /**
     * Always default constructors, no matter what your IDE says
     */
    public TodoDto() {}

    public TodoDto(String id, String userId, String title, boolean completed) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    // Getters and setters, it's the Java way...

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Prevents the XML marshaller from sending it back to the client.
     * @return
     */
    @XmlTransient
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    //Mapping to and from domain and DTO

    public static TodoDto fromTodo(Todo todo) {
        TodoDto result = new TodoDto();
        result.setCompleted(todo.isCompleted());
        result.setTitle(todo.getTitle());
        result.setUserId(todo.getUserId());
        result.setId(todo.getId());
        return result;
    }

    public Todo toTodo() {
        Todo result = new Todo();
        result.setTitle(this.getTitle());
        result.setCompleted(this.isCompleted());
        result.setUserId(this.getUserId());
        result.setId(this.getId());
        return result;
    }
}
