package org.example.naco.mapper;

import org.example.naco.domain.Task;

import java.util.List;

public interface TaskMapper {
    List<Task> findAll();
    Task findById(Long id);
    int insert(Task task);
    int update(Task task);
    int toggleDone(Long id);
    int delete(Long id);
}
