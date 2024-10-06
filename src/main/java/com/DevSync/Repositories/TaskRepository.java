package com.DevSync.Repositories;

import com.DevSync.Entities.Tasks;

import java.util.List;

public interface TaskRepository extends GenericRepository<Tasks, Long>{
    List<Tasks> fetchUserCreatedTasks(long id);
}
