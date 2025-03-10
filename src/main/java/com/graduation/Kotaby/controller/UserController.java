package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.entity.User;
import com.graduation.Kotaby.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        System.out.println("id = " + id);
        return userService.getUser(id);
    }
}
