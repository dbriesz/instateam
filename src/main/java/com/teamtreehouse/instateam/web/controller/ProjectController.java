package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private SessionFactory sessionFactory;

    // Index of all projects
    @SuppressWarnings("unchecked")
    @RequestMapping("/projects")
    public String listProjects(Model model) {

        // Open a session
        Session session = sessionFactory.openSession();

        // Get all projects from the database and store the list into the projects variable
        List<Project> projects = session.createCriteria(Project.class).list();
        model.addAttribute("projects", projects);
        return "/index";
    }
}
