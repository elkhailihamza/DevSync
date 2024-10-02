package com.DevSync.Controllers;

import com.DevSync.Services.TagService;
import com.DevSync.Services.TaskService;
import com.DevSync.Services.UtilisateurService;
import jakarta.inject.Inject    ;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Controller {
    @Inject
    protected UtilisateurService utilisateurService;

    @Inject
    protected TaskService taskService;

    @Inject
    protected TagService tagService;
}
