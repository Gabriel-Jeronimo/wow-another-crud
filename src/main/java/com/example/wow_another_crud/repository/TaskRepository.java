package com.example.wow_another_crud.repository;

import com.example.wow_another_crud.model.Task;
import org.springframework.data.repository.ListCrudRepository;

public interface TaskRepository extends ListCrudRepository<Task, Integer> {}