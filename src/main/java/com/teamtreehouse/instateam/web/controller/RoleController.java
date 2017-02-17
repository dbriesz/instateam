package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    // Form for adding a new role
    @RequestMapping("roles/add")
    public String formNewRole(Model model) {
        // TODO: Add model attributes needed for new form

        return "role/details";
    }

    // Form for editing an existing role
    @RequestMapping("roles/{roleId}/edit")
    public String formEditRole(@PathVariable Long roleId, Model model) {
        // TODO: Add model attributes needed for edit form

        return "role/details";
    }

    // Update an existing role
    @RequestMapping(value = "/roles/{roleId}", method = RequestMethod.POST)
    public String updateRole() {
        // TODO: Update role if valid data was received

        // TODO: Redirect browser to /roles
        return null;
    }

    // Add a role
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public String addRole() {
        // TODO: Add role if valid data was received

        // TODO: Redirect browser to /roles
        return null;
    }

    // Delete an existing role
    @RequestMapping(value = "/roles/{roleId}/delete", method = RequestMethod.POST)
    public String deleteRole(@PathVariable Long roleId) {
        // TODO: Delete role whose id is roleId

        // TODO: Redirect browser to /roles
        return null;
    }
}
