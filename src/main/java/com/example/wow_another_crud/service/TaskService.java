package com.example.wow_another_crud.service;

import com.example.wow_another_crud.exceptions.TaskNotFoundException;
import com.example.wow_another_crud.model.Task;
import com.example.wow_another_crud.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return this.taskRepository.save(task);
    }

    public List<Task> getTasks() {
        return this.taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
       return taskRepository.findById(id.intValue()).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask( Long id, Task body) {
        return taskRepository.findById(id.intValue()).map(task -> {
            task.setName(body.getName());
            task.setDescription(body.getDescription());
            task.setStatus(body.getStatus());
            return taskRepository.save(task);
        }).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void deleteTask( Long id) {
        taskRepository.deleteById(id.intValue());
    }
}
        