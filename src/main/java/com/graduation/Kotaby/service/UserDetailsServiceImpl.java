package com.graduation.Kotaby.service;

import com.graduation.Kotaby.entity.UserDetails;
import com.graduation.Kotaby.repository.UserDetailsDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsDAO userDetailsDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsDAO userDetailsDAO) {
        this.userDetailsDAO = userDetailsDAO;
    }

    @Override
    @Transactional
    public void saveUserDetails(UserDetails userDetails) {
        userDetailsDAO.saveUserDetails(userDetails);
    }

    @Override
    @Transactional
    public UserDetails getUserDetails(int id) {
        return userDetailsDAO.getUserDetails(id);
    }

    @Override
    @Transactional
    public void deleteUserDetails(int id) {
        userDetailsDAO.deleteUserDetails(id);
    }

    @Override
    @Transactional
    public void updateUserDetails(UserDetails userDetails) {
        userDetailsDAO.updateUserDetails(userDetails);
    }

    @Override
    @Transactional
    public List<UserDetails> getAllUsersDetails() {
        return userDetailsDAO.getAllUsersDetails();
    }
}
