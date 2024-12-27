package com.example.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private Map<Long, Todo> todos = new HashMap<>();
    private long currentId = 1;

    @GetMapping
    public List<Todo> getAllTodos() {
        System.out.println("Fetching all todos...");
        return new ArrayList<>(todos.values());
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        System.out.println("Creating todo: " + todo.getDescription());
        todo.setId(currentId++);
        todos.put(todo.getId(), todo);
        return todo;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Todo existingTodo = todos.get(id);
        if (existingTodo == null) {
            System.out.println("Todo not found: ID=" + id);
            return ResponseEntity.status(404).body(null);
        }
        existingTodo.setDescription(todo.getDescription());
        existingTodo.setDone(todo.isDone());
        System.out.println("Updated todo: " + existingTodo);
        return ResponseEntity.ok(existingTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        Todo existingTodo = todos.remove(id);
        if (existingTodo == null) {
            System.out.println("Todo not found for deletion: ID=" + id);
            return ResponseEntity.status(404).body(null);
        }
        System.out.println("Deleted todo: ID=" + id);
        return ResponseEntity.ok().build();
    }
}





