package org.example.tasklist.web.models.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.tasklist.domain.task.Status;
import org.example.tasklist.web.models.validation.OnCreate;
import org.example.tasklist.web.models.validation.OnUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TaskRequestModel {

    @NotNull(message = "title must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "title must be no longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Length(max = 255, message = "description must be no longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    private Status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expiresAt;
}
