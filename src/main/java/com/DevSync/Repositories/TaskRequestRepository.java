package com.DevSync.Repositories;

import com.DevSync.Entities.TaskRequest;

import java.util.List;

public interface TaskRequestRepository {
    List<TaskRequest> fetchAll();
}
