package com.DevSync.Services;

import com.DevSync.Entities.TaskRequest;
import com.DevSync.Repositories.TaskRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TaskRequestService {
    @Inject
    private TaskRequestRepository taskRequestRepository;

    public List<TaskRequest> fetchAll() {
        return taskRequestRepository.fetchAll();
    }
}
