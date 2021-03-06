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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CollaboratorService collaboratorService;

    // Home page - index of all projects
    @SuppressWarnings("unchecked")
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
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", new Project());
        }
        model.addAttribute("action", "/projects/add");

//        model.addAttribute("role", new Role());
        model.addAttribute("roles", roleService.findAll());

        return "project/edit_project";
    }

    // Edit an existing project
    @RequestMapping("projects/{projectId}/edit")
    public String editProject(@PathVariable Long projectId, Model model) {
        // Add model attributes needed for edit form
        Project project = projectService.findById(projectId);
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", project);
        }
        List<Role> allRoles = roleService.findAll();
        model.addAttribute("roles", allRoles);
        model.addAttribute("action", String.format("/projects/%s/edit", projectId));

        return "project/edit_project";
    }

    // Edit project collaborators
    @RequestMapping("projects/{projectId}/collaborators")
    public String editProjectCollaborators(@PathVariable Long projectId, Model model) {
        // Add model attributes needed for edit form
        Project project = projectService.findById(projectId);
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", project);
        }

        List<Role> allRoles = roleService.findAll();
        List<Collaborator> allCollaborators = collaboratorService.findAll();

        model.addAttribute("roles", allRoles);
        model.addAttribute("collaborators", allCollaborators);
        model.addAttribute("heading", String.format("Edit Collaborators: %s", project.getName()));
        model.addAttribute("action", String.format("/projects/%s/collaborators", projectId));

        return "project/project_collaborators";
    }

    // Update an existing project
    @RequestMapping(value = "/projects/{projectId}/edit", method = RequestMethod.POST)
    public String updateProject(@Valid Project project) {
        // Update project if valid data was received
        Project originalProject = projectService.findById(project.getId());
        originalProject.setRolesNeeded(project.getRolesNeeded());
        originalProject.setCollaborators(project.getCollaborators());
        projectService.save(project);

        // Redirect browser to home page
        return String.format("redirect:/projects/%s", project.getId());
    }

    // Add a project
    @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
    public String addProject(@Valid Project project, BindingResult result) {
        // Add project if valid data was received
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
        } else {
            projectService.save(project);
        }

        // Redirect browser to home page
        return "redirect:/";
    }

    // Update an existing project's collaborators
    @RequestMapping(value = "/projects/{projectId}/collaborators", method = RequestMethod.POST)
    public String updateProjectCollaborators(@Valid Project project, BindingResult result) {
        // Update project if valid data was received
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
        } else {
            List<Role> roles = project.getRolesNeeded();
            List<Role> newRoles = new ArrayList<>();
            for (Role role : roles) {
                newRoles.add(roleService.findById(role.getId()));
            }
            project.setRolesNeeded(newRoles);

            Project originalProject = projectService.findById(project.getId());
            originalProject.setRolesNeeded(project.getRolesNeeded());
            originalProject.setCollaborators(project.getCollaborators());
            projectService.save(project);
        }

        // Redirect browser to project details page
        return String.format("redirect:/projects/%s", project.getId());
    }

    // Delete an existing project
    @RequestMapping(value = "/projects/{projectId}/delete", method = RequestMethod.POST)
    public String deleteProject(@PathVariable Long projectId) {
        // TODO: Delete project whose id is projectId

        // TODO: Redirect browser to home page
        return null;
    }

    private Map<Role, Collaborator> getRoleCollaboratorMap(Project project) {
        List<Role> rolesNeeded = project.getRolesNeeded();
        List<Collaborator> collaborators = project.getCollaborators();
        Map<Role, Collaborator> roleCollaborator = new LinkedHashMap<>();

        for (Role roleNeeded : rolesNeeded) {
            roleCollaborator.put(roleNeeded,
                    collaborators.stream()
                            .filter((col) -> col.getRole().getId().equals(roleNeeded.getId()))
                            .findFirst()
                            .orElseGet(() -> {
                                Collaborator unassigned = new Collaborator();
                                unassigned.setName("[Unassigned]");
                                return unassigned;
                            }));
        }
        return roleCollaborator;
    }
}
