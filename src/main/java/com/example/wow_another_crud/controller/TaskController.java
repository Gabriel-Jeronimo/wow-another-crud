package com.example.wow_another_crud.controller;


import com.example.wow_another_crud.exceptions.TaskNotFoundException;
import com.example.wow_another_crud.model.Task;
import com.example.wow_another_crud.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody Task task) {
        this.taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(this.taskRepository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id.intValue());
        return ResponseEntity.ok(task.orElseThrow(() -> new TaskNotFoundException(id)));
    }

    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task body) {
        return taskRepository.findById(id.intValue()).map(task -> {
            task.setName(body.getName());
            task.setDescription(body.getDescription());
            task.setStatus(body.getStatus());
            return ResponseEntity.ok(taskRepository.save(task));
        }).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id.intValue());
        return ResponseEntity.noContent().build();
    }
}
