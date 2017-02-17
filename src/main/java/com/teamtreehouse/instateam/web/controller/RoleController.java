package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    // Index of all role
    @SuppressWarnings("unchecked")
    @RequestMapping("/roles")
    public String listRoles(Model model) {
        // Get all role from the database and store the list into the role variable
        List<Role> roles = roleService.findAll();

        model.addAttribute("roles", roles);
        return "role/index";
    }

    // Single role page
    @RequestMapping("/roles/{roleId}")
    public String role(@PathVariable Long roleId, Model model) {
        // Get the role given by projectId
        Role role = null;

        model.addAttribute("role", role);
        return "role/details";
    }
}
