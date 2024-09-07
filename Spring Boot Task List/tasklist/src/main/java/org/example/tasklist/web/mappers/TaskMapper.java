package org.example.tasklist.web.mappers;

import org.example.tasklist.domain.task.Task;
import org.example.tasklist.web.models.task.TaskRequestModel;
import org.example.tasklist.web.models.task.TaskResponseModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseModel toResponseModel(Task task);

    List<TaskResponseModel> toResponseModel(List<Task> tasks);

    Task toEntity(TaskRequestModel model);
}
