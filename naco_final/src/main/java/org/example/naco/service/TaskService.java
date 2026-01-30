package org.example.naco.service;

import org.example.naco.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
    Task findById(Long id);
    void create(String title);
    void update(Long id, String title, boolean done);
    void toggleDone(Long id);
    void delete(Long id);
}
