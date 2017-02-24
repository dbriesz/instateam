package com.teamtreehouse.instateam;

import com.teamtreehouse.instateam.dao.CollaboratorDao;
import com.teamtreehouse.instateam.dao.ProjectDao;
import com.teamtreehouse.instateam.dao.RoleDao;
import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements ApplicationRunner{
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private CollaboratorDao collaboratorDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 1; i <= 5; i++) {

            Role role = new Role();
            role.setName("Role " + i);
            roleDao.save(role);

            Collaborator collaborator = new Collaborator();
            collaborator.setName("Collaborator " + i);
            collaborator.setRole(roleDao.findById((long) i));
            collaboratorDao.save(collaborator);

            Project project = new Project();
            project.setName("Project " + i);
            project.setRolesNeeded(Collections.singletonList(
                    roleDao.findById((long) i))
            );
            project.setCollaborators(Collections.singletonList(
                    collaboratorDao.findById((long) i))
            );
            project.setDescription("description");
            project.setStatus("status");

            projectDao.save(project);
        }
    }
}