package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.DTO.UserDTO;
import com.graduation.Kotaby.entity.User;
import com.graduation.Kotaby.entity.UserDetails;
import com.graduation.Kotaby.service.UserDetailsService;
import com.graduation.Kotaby.service.UserService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {

        userDTO.setId(null);
        System.out.println(userDTO.getEmail());
        User user = userService.getUserByEmail(userDTO.getEmail());
        if (user != null) {
            System.out.println("User already exists");
            // User already exists with this email address tell the user to login in the response
             return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        user = userDTO.toUser();
        userService.saveUser(user);
        userDTO.setId(user.getId());
        UserDetails userDetails = userDTO.toUserDetails();
        userDetails.setUserId(user.getId());
        userDetailsService.saveUserDetails(userDetails);
        return ResponseEntity.ok(new UserDTO(user, userDetails));
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
    public UserDTO getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        UserDetails userDetails = userDetailsService.getUserDetails(id);
        return new UserDTO(user, userDetails);
    }

    @PutMapping("/{userId}/photo")
    public ResponseEntity<String> updateUserPhoto(@PathVariable Long userId, @RequestPart("file") MultipartFile file) {
        String imageUrl = userDetailsService.updateProfilePhoto(userId, file);
        return ResponseEntity.ok(imageUrl);
    }

}
