package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class CollaboratorController {
    @Autowired
    private CollaboratorService collaboratorService;

    // Index of all collaborator
    @SuppressWarnings("unchecked")
    @RequestMapping("/collaborators")
    public String listCollaborators(Model model) {
        // Get all collaborator from the database and store the list into the collaborator variable
        List<Collaborator> collaborators = collaboratorService.findAll();

        model.addAttribute("collaborators", collaborators);
        return "collaborator/index";
    }

    // Single collaborator page
    @RequestMapping("/collaborators/{collaboratorId}")
    public String collaborator(@PathVariable Long collaboratorId, Model model) {
        // Get the collaborator given by collaboratorId
        Collaborator collaborator = null;

        model.addAttribute("collaborator", collaborator);
        return "collaborator/details";
    }
}
