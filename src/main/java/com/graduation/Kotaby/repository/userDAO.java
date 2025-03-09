package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.User;

import java.util.List;

public interface userDAO {
    public void saveUser(User user);
    public User getUser(int id);
    public void deleteUser(int id);
    public void updateUser(User user);
    public List<User> getUsers();
    public User getUserByEmail(String email);
}
