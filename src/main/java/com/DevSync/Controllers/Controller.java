package com.DevSync.Controllers;

import com.DevSync.Services.TagService;
import com.DevSync.Services.TaskRequestService;
import com.DevSync.Services.TaskService;
import com.DevSync.Services.UtilisateurService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;

public class Controller {
    @Inject
    protected HttpServletRequest request;

    @Inject
    protected UtilisateurService utilisateurService;

    @Inject
    protected TaskService taskService;

    @Inject
    protected TagService tagService;

    @Inject
    protected TaskRequestService taskRequestService;
}
