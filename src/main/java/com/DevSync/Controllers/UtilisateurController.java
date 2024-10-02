package com.DevSync.Controllers;

import com.DevSync.Services.UtilisateurService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UtilisateurController extends Controller {
    private final UtilisateurService utilisateurService;

    @Inject
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
}
