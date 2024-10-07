package org.example.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasklist.domain.exception.ResourceNotFoundException;
import org.example.tasklist.domain.task.Status;
import org.example.tasklist.domain.task.Task;
import org.example.tasklist.repository.TaskRepository;
import org.example.tasklist.repository.UserRepository;
import org.example.tasklist.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Task create(Task task, Long userId) {
        task.setStatus(Status.TODO);
        taskRepository.create(task);
        taskRepository.assignToUserById(userId, task.getId());

        return task;
    }

    @Override
    @Transactional
    public Task update(Long id, Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO);
        }

        taskRepository.update(task);

        return task;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.delete(id);
    }
}
