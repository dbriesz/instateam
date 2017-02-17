package com.teamtreehouse.instateam.dao;

import com.teamtreehouse.instateam.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        // Open a session
        Session session = sessionFactory.openSession();

        // Get all project with a Hibernate criteria
        List<Project> projects = session.createCriteria(Project.class).list();

        // Close session
        session.close();

        return projects;
    }

    @Override
    public Project findById(Long id) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Find a project by id
        Project project = session.get(Project.class,id);

        // Close session
        session.close();

        return project;
    }

    @Override
    public void save(Project project) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Save the project
        session.saveOrUpdate(project);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    @Override
    public void delete(Project project) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Delete the project
        session.delete(project);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }
}
