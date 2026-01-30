package org.example.naco.service;

import org.example.naco.domain.Task;
import org.example.naco.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskMapper.findById(id);
    }

    @Override
    public void create(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setDone(false);
        taskMapper.insert(task);
    }

    @Override
    public void update(Long id, String title, boolean done) {
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDone(done);
        taskMapper.update(task);
    }

    @Override
    public void toggleDone(Long id) {
        taskMapper.toggleDone(id);
    }

    @Override
    public void delete(Long id) {
        taskMapper.delete(id);
    }
}
