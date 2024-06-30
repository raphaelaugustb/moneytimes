package com.leah.money_times.controller;

import com.leah.money_times.entity.User;
import com.leah.money_times.request.UserRequest;
import com.leah.money_times.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
   UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserRequest>createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok(userRequest);
    }
}

