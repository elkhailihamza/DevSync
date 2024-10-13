package com.DevSync.Repositories;

import com.DevSync.Entities.Task;

import java.util.List;

public interface TaskRepository extends GenericRepository<Task, Long>{
    List<Task> fetchUserCreatedTasks(long id);
}
