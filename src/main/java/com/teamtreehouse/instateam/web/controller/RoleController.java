package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private SessionFactory sessionFactory;

    // Index of all roles
    @SuppressWarnings("unchecked")
    @RequestMapping("/roles")
    public String listRoles(Model model) {

        // Open a session
        Session session = sessionFactory.openSession();

        // Get all roles from the database and store the list into the roles variable
        List<Role> roles = session.createCriteria(Role.class).list();
        model.addAttribute("roles", roles);
        return "/roles";
    }
}
