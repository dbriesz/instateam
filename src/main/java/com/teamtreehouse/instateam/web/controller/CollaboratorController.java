package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Collaborator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class CollaboratorController {
    @Autowired
    private SessionFactory sessionFactory;

    // Index of all collaborators
    @SuppressWarnings("unchecked")
    @RequestMapping("/collaborators")
    public String listCollaborators(Model model) {

        // Open a session
        Session session = sessionFactory.openSession();

        // Get all collaborators from the database and store the list into the collaborators variable
        List<Collaborator> collaborators = session.createCriteria(Collaborator.class).list();
        model.addAttribute("collaborators", collaborators);
        return "/collaborators";
    }
}
