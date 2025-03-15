package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.User;

import java.util.List;

public interface UserDAO {
    public void saveUser(User user);

    public User getUser(int id);

    public User getUserByEmail(String email);

    public List<User> getUsers();

    public void updateUser(User user);

    public void deleteUser(int id);
}
