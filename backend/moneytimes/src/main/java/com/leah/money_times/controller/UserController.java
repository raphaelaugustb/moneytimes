package com.leah.money_times.controller;

import com.leah.money_times.entity.User;
import com.leah.money_times.request.UserRequest;
import com.leah.money_times.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRequest> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok(userRequest);
    }

    @DeleteMapping("{id}/user")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User sucefully deleted.");
    }

    @GetMapping("{id}/user")
    ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserInfo(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}/user")
    ResponseEntity<User> updateUserInfo(@PathVariable String id, @RequestBody UserRequest userRequest) {
        userService.updateUserInfo(id, userRequest);
        User user = userService.getUserInfo(id);
        return ResponseEntity.ok(user);
    }
}

