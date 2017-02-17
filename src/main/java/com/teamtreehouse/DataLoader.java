package com.teamtreehouse;

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

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private CollaboratorDao collaboratorDao;

    @Autowired
    private ProjectDao projectDao;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 1; i <= 5; i++) {

            // save role
            Role role = new Role();
            role.setName("role " + i);
            roleDao.save(role);

            // save collaborator with one role each
            Collaborator collaborator = new Collaborator();
            collaborator.setName("collaborator " + i);
            collaborator.setRole(
                    roleDao.findById((long) i)
            );
            collaboratorDao.save(collaborator);

            // save project with one role one collaborator each
            Project project = new Project();
            project.setName("project " + i);
            project.setDescription("description " + i);
            project.setStatus("status " + i);

            /*project.addRole(
                    roleDao.findById((long) i)
            );

            project.addCollaborator(
                    collaboratorDao.findById((long) i)
            );*/

            projectDao.save(project);
        }
    }
}
