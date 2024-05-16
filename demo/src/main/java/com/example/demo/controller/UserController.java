package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/role")
    public User.Role getUserRole(@PathVariable Long userId) {
        return userService.getUserRole(userId);
    }

    @PutMapping("/{userId}/role")
    public ResponseEntity<Void> setUserRole(@PathVariable Long userId, @RequestBody User.Role newRole) {
        userService.setUserRole(userId, newRole);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}