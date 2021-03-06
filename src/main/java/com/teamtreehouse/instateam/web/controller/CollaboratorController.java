package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.ProjectService;
import com.teamtreehouse.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CollaboratorController {
    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ProjectService projectService;

    // Index of all collaborators
    @SuppressWarnings("unchecked")
    @RequestMapping("/collaborators")
    public String listCollaborators(Model model) {
        // Get all collaborators from the database and store the list into the collaborator variable
        List<Collaborator> collaborators = collaboratorService.findAll();

        model.addAttribute("collaborators", collaborators);

        // Add model attributes needed for new form
        if (!model.containsAttribute("collaborator")) {
            model.addAttribute("collaborator", new Collaborator());
        }
        model.addAttribute("roles", roleService.findAll());

        return "collaborator/index";
    }

    // Single collaborator page
    @RequestMapping("/collaborators/{collaboratorId}")
    public String collaborator(@PathVariable Long collaboratorId, Model model) {
        // Get the collaborator given by collaboratorId
        Collaborator collaborator = collaboratorService.findById(collaboratorId);

        model.addAttribute("collaborator", collaborator);
        return "collaborator/details";
    }

    // Form for editing an existing collaborator
    @RequestMapping("collaborators/{collaboratorId}/edit")
    public String formEditCollaborator(@PathVariable Long collaboratorId, Model model) {
        // Add model attributes needed for edit form
        if (!model.containsAttribute("collaborator")) {
            model.addAttribute("collaborator", collaboratorService.findById(collaboratorId));
        }
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("heading","Edit Collaborator");
        model.addAttribute("submit","Update");

        return "collaborator/details";
    }

    // Update an existing collaborator
    @RequestMapping(value = "/collaborators/{collaboratorId}/edit", method = RequestMethod.POST)
    public String updateCollaborator(@Valid Collaborator collaborator) {
        // Update collaborator if valid data was received
        collaboratorService.save(collaborator);

        // Redirect browser to /collaborators
        return "redirect:/collaborators";
    }

    // Add a collaborator
    @RequestMapping(value = "/collaborators", method = RequestMethod.POST)
    public String addCollaborator(@Valid Collaborator collaborator) {
        // Add collaborator if valid data was received
        collaboratorService.save(collaborator);

        // Redirect browser to /collaborators
        return "redirect:/collaborators";
    }

    // Delete an existing collaborator
    @RequestMapping(value = "/collaborators/{collaboratorId}/delete", method = RequestMethod.POST)
    public String deleteCollaborator(@PathVariable Long collaboratorId) {
        // TODO: Delete collaborator whose id is collaboratorId

        // TODO: Redirect browser to /collaborators
        return null;
    }
}
