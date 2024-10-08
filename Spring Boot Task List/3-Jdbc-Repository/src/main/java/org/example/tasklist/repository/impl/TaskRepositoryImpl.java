package org.example.tasklist.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasklist.domain.exception.ResourceMappingException;
import org.example.tasklist.domain.task.Task;
import org.example.tasklist.repository.DataSourceConfiguration;
import org.example.tasklist.repository.TaskRepository;
import org.example.tasklist.repository.mapper.TaskRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final DataSourceConfiguration dataSourceConfiguration;
    private final String FIND_BY_ID = """
            select t.id          as task_id,
                   t.title       as task_title,
                   t.description as task_description,
                   t.status      as task_status,
                   t.expires_at  as task_expires_at
            from tasks t
            where id = ?
            """;

    private final String FIND_ALL_BY_USER_ID = """
            select t.id          as task_id,
                   t.title       as task_title,
                   t.description as task_description,
                   t.status      as task_status,
                   t.expires_at  as task_expires_at
            from tasks t
                     join users_tasks as ut on t.id = ut.task_id
            where ut.user_id = ?
            """;

    private final String ASSIGN = """
            insert into users_tasks (task_id, user_id)
            values (?, ?)
            """;

    private final String UPDATE = """
            update tasks
            set title       = ?,
                description = ?,
                status      = ?,
                expires_at  = ?
            where id = ?
            """;

    private final String CREATE = """
            insert into tasks (title, description, status, expires_at)
            values (?, ?, ?, ?)
            """;

    private final String DELETE = """
            delete from tasks where id = ?
            """;

    @Override
    public Optional<Task> findById(Long id) {
        try (Connection connection = dataSourceConfiguration.getConnection();
             var stmt = connection.prepareStatement(FIND_BY_ID)) {

            stmt.setLong(1, id);
            var result = stmt.executeQuery();

            return Optional.ofNullable(TaskRowMapper.mapRow(result));
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while search task");
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        try (Connection connection = dataSourceConfiguration.getConnection();
             var stmt = connection.prepareStatement(FIND_ALL_BY_USER_ID)) {

            stmt.setLong(1, userId);
            var result = stmt.executeQuery();

            return TaskRowMapper.mapRowList(result);
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while search task");
        }
    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {

    }

    @Override
    public void create(Task task) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(Long id) {

    }
}
