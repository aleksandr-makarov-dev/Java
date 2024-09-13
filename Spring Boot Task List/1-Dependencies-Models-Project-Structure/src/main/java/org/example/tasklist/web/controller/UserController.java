package org.example.tasklist.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasklist.domain.task.Task;
import org.example.tasklist.domain.user.User;
import org.example.tasklist.service.TaskService;
import org.example.tasklist.service.UserService;
import org.example.tasklist.web.mappers.TaskMapper;
import org.example.tasklist.web.mappers.UserMapper;
import org.example.tasklist.web.models.task.TaskRequestModel;
import org.example.tasklist.web.models.task.TaskResponseModel;
import org.example.tasklist.web.models.user.UserRequestModel;
import org.example.tasklist.web.models.user.UserResponseModel;
import org.example.tasklist.web.models.validation.OnCreate;
import org.example.tasklist.web.models.validation.OnUpdate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    public UserResponseModel getById(@PathVariable Long id){
        User user = userService.getById(id);
        return  userMapper.toResponseModel(user);
    }

    @PutMapping("/{id}")
    public UserResponseModel update(@PathVariable Long id, @Validated(OnUpdate.class) @RequestBody UserRequestModel model){
        User userToUpdate = userMapper.toEntity(model);
        User updatedUser = userService.update(id, userToUpdate);

        return  userMapper.toResponseModel(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskResponseModel> getTasksById(@PathVariable Long id){
        List<Task> foundTasks = taskService.getAllByUserId(id);

        return  taskMapper.toResponseModel(foundTasks);
    }

    @PostMapping("/{id}/tasks")
    public TaskResponseModel createTask(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody TaskRequestModel model){
        Task taskToCreate = taskMapper.toEntity(model);
        Task createdTask = taskService.create(taskToCreate,id);

        return  taskMapper.toResponseModel(createdTask);
    }
}
