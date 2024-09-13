package org.example.tasklist.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasklist.domain.task.Task;
import org.example.tasklist.service.TaskService;
import org.example.tasklist.web.mappers.TaskMapper;
import org.example.tasklist.web.models.task.TaskRequestModel;
import org.example.tasklist.web.models.task.TaskResponseModel;
import org.example.tasklist.web.models.validation.OnUpdate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    public TaskResponseModel getById(@PathVariable Long id){
        Task task = taskService.getById(id);
        return taskMapper.toResponseModel(task);
    }

    @PutMapping("/{id}")
    public TaskResponseModel update(@PathVariable Long id, @Validated(OnUpdate.class) @RequestBody TaskRequestModel model){
        Task taskToUpdate = taskMapper.toEntity(model);
        Task updatedTask = taskService.update(id, taskToUpdate);
        return  taskMapper.toResponseModel(updatedTask);
    }

    @DeleteMapping("/{id}")
    public void  deleteById(@PathVariable Long id){
        taskService.delete(id);
    }
}
