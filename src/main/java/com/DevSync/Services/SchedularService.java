package com.DevSync.Services;

import com.DevSync.Entities.Task;
import com.DevSync.Entities.UserToken;
import com.DevSync.Entities.Utilisateur;
import com.DevSync.Repositories.TaskRepository;
import com.DevSync.Repositories.UserTokenRepository;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

@Singleton
@Startup
public class SchedularService {
    @Inject
    private TaskRepository taskRepository;
    @Inject
    private UserTokenRepository userTokenRepository;

    @Schedule(hour = "0/12", persistent = false)
    public void checkPendingRequests() {
        List<Task> pendingTasks = taskRepository.fetchPendingTasks(getLocalDateTime());

        for (Task task : pendingTasks) {
            Utilisateur user = task.getAssignee();
            UserToken userToken = userTokenRepository.fetchUserToken(user);

            duplicateTokens(userToken);
            userTokenRepository.update(userToken);
        }

    }

    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void checkOverDueTasks() {
        List<Task> overDueTasks = taskRepository.fetchOverDueTasks(getLocalDateTime());
        overDueTasks.forEach(t -> {
            t.setReplaceable(false);
            taskRepository.save(t);
        });
    }

    private void duplicateTokens(UserToken userToken) {
        userToken.setDailyUpdateTokens(userToken.getDailyUpdateTokens() + 1);
    }

    private LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }
}
