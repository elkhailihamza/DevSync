package com.DevSync.Services;

import com.DevSync.Entities.Task;
import com.DevSync.Entities.UserToken;
import com.DevSync.Entities.Utilisateur;
import com.DevSync.Repositories.TaskRepository;
import com.DevSync.Repositories.UserTokenRepository;
import com.DevSync.Repositories.UtilisateurRepository;
import jakarta.ejb.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Singleton
@Startup
@ApplicationScoped
public class SchedulerService {
    @Inject
    private TaskRepository taskRepository;
    @Inject
    private UtilisateurRepository utilisateurRepository;

    @Schedule(hour = "0/12", persistent = false)
    public void checkPendingRequests() {
        List<Task> pendingTasks = taskRepository.fetchPendingTasks(getLocalDateTime());

        for (Task task : pendingTasks) {
            Utilisateur user = task.getAssignee();
            UserToken userToken = user.getUserTokens();

            duplicateTokens(userToken);
            user.setUserTokens(userToken);
            utilisateurRepository.update(user);
        }
    }

    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    @Transactional
    public void checkOverDueTasks() {
        List<Task> overDueTasks = taskRepository.fetchOverDueTasks(getLocalDateTime());
        overDueTasks.forEach(t -> {
            t.setReplaceable(false);
            taskRepository.update(t);
        });
    }

    private void duplicateTokens(UserToken userToken) {
        userToken.setDailyUpdateTokens(userToken.getDailyUpdateTokens() + 1);
    }

    private LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }
}
