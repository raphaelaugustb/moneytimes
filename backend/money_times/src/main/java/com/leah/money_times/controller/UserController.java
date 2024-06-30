package com.leah.money_times.controller;

import com.leah.money_times.entity.User;
import com.leah.money_times.request.UserRequest;
import com.leah.money_times.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
   UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User>createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("{id}/user")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User sucefully deleted.");
    }
}

