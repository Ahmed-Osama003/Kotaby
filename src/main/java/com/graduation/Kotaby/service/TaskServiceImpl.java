package com.graduation.Kotaby.service;

import com.graduation.Kotaby.entity.primary.Task;
import com.graduation.Kotaby.repository.TaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional("primaryTransactionManager")
@Service
public class TaskServiceImpl implements TaskService {

    private TaskDAO taskDAO;

    @Autowired
    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public void saveTask(Task task) {
        taskDAO.saveTask(task);
    }

    @Override
    public Task getTask(int id) {
        return taskDAO.getTask(id);
    }

    @Override
    public List<Task> getTasksByUserId(int userId) {
        return taskDAO.getTasksByUserId(userId);
    }

    @Override
    public List<Task> getTasks() {
        return taskDAO.getTasks();
    }

    @Override
    public void deleteTask(int id) {
        taskDAO.deleteTask(id);
    }
}
