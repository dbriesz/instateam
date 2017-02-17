package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    // Index of all project
    @SuppressWarnings("unchecked")
    @RequestMapping("/projects")
    public String listProjects(Model model) {
        // Get all project from the database and store the list into the project variable
        List<Project> projects = projectService.findAll();

        model.addAttribute("projects", projects);
        return "project/index";
    }

    // Single project page
    @RequestMapping("/projects/{projectId}")
    public String project(@PathVariable Long projectId, Model model) {
        // Get the project given by projectId
        Project project = null;

        model.addAttribute("project", project);
        return "project/details";
    }


}
