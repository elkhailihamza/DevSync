package com.DevSync.Controllers;

import com.DevSync.Entities.TaskRequest;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class TaskRequestController extends Controller {
    public List<TaskRequest> getAllRequests() {
        return taskRequestService.fetchAll();
    }
}
