package com.example.wow_another_crud.service;


import com.example.wow_another_crud.model.Task;
import com.example.wow_another_crud.model.TaskStatus;
import com.example.wow_another_crud.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    TaskRepository taskRepository;

    @InjectMocks TaskService taskService;

    @Test
    public void shouldReturnAllTasks_whenGetTasksIsCalled() {
        Task task = new Task();
        task.setName("My task");
        task.setStatus(TaskStatus.PENDING);
        task.setDescription("Stove");

        List<Task> expectedList = List.of(task);
        when(taskRepository.findAll()).thenReturn(expectedList);

        List<Task> actualList = taskService.getTasks();

        assertEquals(expectedList, actualList);
    }

    @Test
    public void shouldReturnASingleTask_whenGetTasksByIdIsCalled() {
        int TASK_ID = 1;
        Task expectedTask = new Task();
        expectedTask.setName("My task");
        expectedTask.setStatus(TaskStatus.PENDING);
        expectedTask.setDescription("Stove");

        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(expectedTask));

        Task actualTask = taskService.getTaskById((long) TASK_ID);

        assertEquals(expectedTask, actualTask);
    }


}
