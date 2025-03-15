package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.Task;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {
    private EntityManager entityManager;

    @Autowired
    public TaskDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveTask(Task task) {
        entityManager.persist(task);
    }

    @Override
    public Task getTask(int id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> getTasks() {
        return entityManager.createQuery("from Task", Task.class).getResultList();
    }

    @Override
    public List<Task> getTasksByUserId(int userId) {
        return entityManager.createQuery("from Task t where t.user.id = :userId", Task.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public void deleteTask(int id) {
        entityManager.remove(getTask(id));
    }
}
