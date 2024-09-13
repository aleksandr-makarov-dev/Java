package org.example.tasklist.web.models.task;

import lombok.Data;
import org.example.tasklist.domain.task.Status;

import java.time.LocalDateTime;

@Data
public class TaskResponseModel {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime expiresAt;
}
