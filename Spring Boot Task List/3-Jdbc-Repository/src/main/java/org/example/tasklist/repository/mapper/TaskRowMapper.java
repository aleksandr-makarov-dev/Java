package org.example.tasklist.repository.mapper;

import org.example.tasklist.domain.task.Status;
import org.example.tasklist.domain.task.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRowMapper {
    public static Task mapRow(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Task.builder()
                    .id(rs.getLong("task_id"))
                    .title(rs.getString("task_title"))
                    .description(rs.getString("task_description"))
                    .status(Status.valueOf(rs.getString("task_status")))
                    .expiresAt(rs.getTimestamp("task_expires_at").toLocalDateTime())
                    .build();
        }

        return null;
    }

    public static List<Task> mapRowList(ResultSet rs) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        while (rs.next()) {
            tasks.add(mapRow(rs));
        }

        return tasks;
    }
}
