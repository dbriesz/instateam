package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.ProjectService;
import com.teamtreehouse.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    // Home page - index of all projects
    @RequestMapping("/")
    public String listProjects(Model model) {
        // Get all projects from the database and store the list into the project variable
        List<Project> projects = projectService.findAll();

        model.addAttribute("projects", projects);

        return "index";
    }

    // Single project page
    @RequestMapping("/projects/{projectId}")
    public String project(@PathVariable Long projectId, Model model) {
        // Get the project given by projectId
        Project project = projectService.findById(projectId);

        Map<Role, Collaborator> rolesAssignment = getRoleCollaboratorMap(project);

        model.addAttribute("project", project);
        model.addAttribute("rolesAssignment", rolesAssignment);

        return "project/details";
    }

    // Form for adding a new project
    @RequestMapping("projects/add")
    public String formNewProject(Model model) {
        // Add model attributes needed for new form
        model.addAttribute("project", new Project());

        model.addAttribute("role", new Role());
        model.addAttribute("roles", roleService.findAll());

        return "project/index";
    }

    // Edit an existing project
    @RequestMapping("projects/{projectId}/edit")
    public String editProject(@PathVariable Long projectId, Model model) {
        // TODO: Add model attributes needed for edit form
        model.addAttribute("project", projectService.findById(projectId));
        model.addAttribute("roles", roleService.findAll());

        return "project/edit_project";
    }

    // Edit project collaborators
    @RequestMapping("projects/{projectId}/project_collaborators")
    public String editProjectCollaborators(@PathVariable Long projectId, Model model) {
        // TODO: Add model attributes needed for edit form

        return "project/project_collaborators";
    }

    // Update an existing project
    @RequestMapping(value = "/projects/{projectId}/edit", method = RequestMethod.POST)
    public String updateProject(@Valid Project project) {
        // TODO: Update project if valid data was received
        projectService.save(project);

        // TODO: Redirect browser to home page
        return String.format("redirect:/projects/%s", project.getId());
    }

    // Add a project
    @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
    public String addProject(@Valid Project project) {
        // TODO: Add project if valid data was received
        projectService.save(project);

        // TODO: Redirect browser to home page
        return "redirect:/";
    }

    // Delete an existing project
    @RequestMapping(value = "/projects/{projectId}/delete", method = RequestMethod.POST)
    public String deleteProject(@PathVariable Long projectId) {
        // TODO: Delete project whose id is projectId

        // TODO: Redirect browser to home page
        return null;
    }

    public Map<Role,Collaborator> getRoleCollaboratorMap(Project project) {
        Map<Role, Collaborator> roleCollaboratorMap = new HashMap<>();
        List<Role> rolesNeeded = project.getRolesNeeded();
        List<Collaborator> collaborators = project.getCollaborators();

        for (Role role : rolesNeeded) {
            for (Collaborator collaborator : collaborators) {
                if (collaborators.contains(collaborator)) {
                    roleCollaboratorMap.put(role, collaborator);
                }
            }
        }

        return roleCollaboratorMap;
    }
}
