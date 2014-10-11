package com.hoserdude.toboot.documentRepository;

import com.hoserdude.toboot.document.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Magic interface to communicate with MongoDB.
 */
@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
    Todo findByUserIdAndId(String userId, String id);
    List<Todo> findByUserId(String userId);
}
