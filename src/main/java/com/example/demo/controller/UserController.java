package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.entity.User;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public Boolean create(@RequestBody User user){
        return userService.checkPW(user);
    }
}
