package com.example.wow_another_crud.repository;

import com.example.wow_another_crud.model.Task;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ListCrudRepository<Task, Integer> {}