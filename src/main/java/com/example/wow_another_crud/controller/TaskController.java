package com.example.wow_another_crud.controller;


import com.example.wow_another_crud.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {


    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(new Task());
    }

    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable Long id) {
        return ResponseEntity.ok(new Task());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
