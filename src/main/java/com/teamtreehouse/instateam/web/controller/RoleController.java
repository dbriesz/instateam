package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Role;
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
public class RoleController {
    @Autowired
    private RoleService roleService;

    // Index of all roles
    @SuppressWarnings("unchecked")
    @RequestMapping("/roles")
    public String listRoles(Model model) {
        // Get all roles from the database and store the list into the role variable
        List<Role> roles = roleService.findAll();

        model.addAttribute("roles", roles);

        // Add model attributes needed for new form
        model.addAttribute("role", new Role());

        return "role/index";
    }

    // Single role page
    @RequestMapping("/roles/{roleId}")
    public String role(@PathVariable Long roleId, Model model) {
        // Get the role given by projectId
        Role role = roleService.findById(roleId);

        model.addAttribute("role", role);
        return "role/index";
    }

    // Form for editing an existing role
    @RequestMapping("roles/{roleId}/edit")
    public String formEditRole(@PathVariable Long roleId, Model model) {
        // TODO: Add model attributes needed for edit form
        model.addAttribute("role", roleService.findById(roleId));
        model.addAttribute("heading","Edit Role");
        model.addAttribute("submit","Update");

        return "role/details";
    }

    // Update an existing role
    @RequestMapping(value = "/roles/{roleId}/edit", method = RequestMethod.POST)
    public String updateRole(@Valid Role role) {
        // TODO: Update role if valid data was received
        roleService.save(role);

        // TODO: Redirect browser to /roles
        return "redirect:/roles";
    }

    // Add a role
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public String addRole(@Valid Role role) {
        // TODO: Add role if valid data was received
        roleService.save(role);

        // TODO: Redirect browser to /roles
        return "redirect:/roles";
    }

    // Delete an existing role
    @RequestMapping(value = "/roles/{roleId}/delete", method = RequestMethod.POST)
    public String deleteRole(@PathVariable Long roleId) {
        // TODO: Delete role whose id is roleId

        // TODO: Redirect browser to /roles
        return null;
    }
}
