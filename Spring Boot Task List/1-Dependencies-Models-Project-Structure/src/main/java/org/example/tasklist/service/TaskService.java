package org.example.tasklist.service;

import org.example.tasklist.domain.task.Task;

import java.util.List;

public interface TaskService {
    Task getById(Long id);

    List<Task> getAllByUserId(Long userId);

    Task create(Task task, Long userId);

    Task update(Long id, Task task);

    void delete(Long id);
}
