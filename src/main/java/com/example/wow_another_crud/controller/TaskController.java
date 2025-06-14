package com.example.wow_another_crud.controller;


import com.example.wow_another_crud.exceptions.TaskNotFoundException;
import com.example.wow_another_crud.model.Task;
import com.example.wow_another_crud.repository.TaskRepository;
import com.example.wow_another_crud.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(this.taskService.createTask(task), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(this.taskService.getTasks());
    }

    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task body) {
        return ResponseEntity.ok(this.taskService.updateTask(id, body));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
