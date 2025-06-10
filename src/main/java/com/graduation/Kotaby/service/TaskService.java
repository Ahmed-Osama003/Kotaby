package com.graduation.Kotaby.service;

import com.graduation.Kotaby.entity.primary.Task;

import java.util.List;

public interface TaskService {
    void saveTask(Task task);

    Task getTask(int id);

    List<Task> getTasksByUserId(int userId);

    List<Task> getTasks();

    void deleteTask(int id);
}
