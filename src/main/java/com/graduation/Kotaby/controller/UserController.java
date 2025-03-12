package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.DTO.UserDTO;
import com.graduation.Kotaby.entity.User;
import com.graduation.Kotaby.entity.UserDetails;
import com.graduation.Kotaby.repository.UserDetailsDAO;
import com.graduation.Kotaby.service.UserDetailsService;
import com.graduation.Kotaby.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDetailsDAO userDetailsDAO;
    private final UserDetailsService userDetailsService;

    public UserController(UserService userService, UserDetailsDAO userDetailsDAO, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsDAO = userDetailsDAO;
        this.userDetailsService = userDetailsService;
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

    @PutMapping("/{userId}/photo")
    public ResponseEntity<String> updateUserPhoto(
            @PathVariable Long userId,
            @RequestPart("file") MultipartFile file) {

        System.out.println("File name: " + file.getOriginalFilename());

        String imageUrl = userDetailsService.updateProfilePhoto(userId, file);
        return ResponseEntity.ok(imageUrl);
    }
}
