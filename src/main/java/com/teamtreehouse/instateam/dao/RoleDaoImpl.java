package com.teamtreehouse.instateam.dao;

import com.teamtreehouse.instateam.model.Role;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findAll() {
        // Open a session
        Session session = sessionFactory.openSession();

        // Get all role with a Hibernate criteria
        List<Role> roles = session.createCriteria(Role.class).list();

        // Close session
        session.close();

        return roles;
    }

    @Override
    public Role findById(Long id) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Find a role by id
        Role role = session.get(Role.class, id);

        // Close session
        session.close();
        return role;
    }

    @Override
    public void save(Role role) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Save the role
        session.saveOrUpdate(role);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    @Override
    public void delete(Role role) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Delete the role
        session.delete(role);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }
}
