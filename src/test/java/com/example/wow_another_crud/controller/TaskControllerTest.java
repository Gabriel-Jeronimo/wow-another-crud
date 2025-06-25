package com.example.wow_another_crud.controller;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;


import com.example.wow_another_crud.model.Task;
import com.example.wow_another_crud.model.TaskStatus;
import com.example.wow_another_crud.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.BDDMockito.given;
import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    public void getAllTasks() throws Exception {
        Task task = new Task();
        task.setName("My task");
        task.setStatus(TaskStatus.PENDING);
        task.setDescription("Stove");

        given(taskService.getTasks()).willReturn(singletonList(task));
        assertEquals(ResponseEntity.ok(singletonList(task)), taskController.getTasks());
        mockMvc.perform(get("/api/tasks/"))
                .andExpect(status().isOk());
    }
}

//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setup() {
//        userRepository.save(new User(1L, "Jane"));
//    }
//
//    @Test
//    public void testGetUserById_ReturnsUser() throws Exception {
//        mockMvc.perform(get("/users/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Jane"));
//    }
//}


