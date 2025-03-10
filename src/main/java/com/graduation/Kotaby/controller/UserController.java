package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.DTO.UserDTO;
import com.graduation.Kotaby.entity.User;
import com.graduation.Kotaby.entity.UserDetails;
import com.graduation.Kotaby.repository.UserDetailsDAO;
import com.graduation.Kotaby.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDetailsDAO userDetailsDAO;

    public UserController(UserService userService, UserDetailsDAO userDetailsDAO) {
        this.userService = userService;
        this.userDetailsDAO = userDetailsDAO;
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody UserDTO userDTO) {
        if (userService.getUserByEmail(userDTO.getEmail()) == null) {
            User user = userDTO.toUser();
            UserDetails userDetails = userDTO.toUserDetails();
            userService.saveUser(user);
            userDetailsDAO.saveUserDetails(userDetails);
        }
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        System.out.println("id = " + id);
        return userService.getUser(id);
    }
}
