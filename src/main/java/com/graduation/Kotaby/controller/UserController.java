package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.DTO.UserDTO;
import com.graduation.Kotaby.entity.User;
import com.graduation.Kotaby.entity.UserDetails;
import com.graduation.Kotaby.service.UserDetailsService;
import com.graduation.Kotaby.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public UserController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody UserDTO userDTO) {
        userDTO.setId(null);
        User user = userDTO.toUser();
        userService.saveUser(user);
        userDTO.setId(user.getId());
        UserDetails userDetails = userDTO.toUserDetails();
        userDetails.setUserId(user.getId());
        userDetailsService.saveUserDetails(userDetails);
    }

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getUsers();
        List<UserDetails> userDetails = userDetailsService.getAllUsersDetails();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            userDTOs.add(new UserDTO(users.get(i), userDetails.get(i)));
        }
        return userDTOs;
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        System.out.println("id = " + id);
        return userService.getUser(id);
    }

    @PutMapping("/{userId}/photo")
    public ResponseEntity<String> updateUserPhoto(@PathVariable Long userId, @RequestPart("file") MultipartFile file) {
        String imageUrl = userDetailsService.updateProfilePhoto(userId, file);
        return ResponseEntity.ok(imageUrl);
    }

}
