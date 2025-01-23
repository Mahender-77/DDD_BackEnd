package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // Sign up endpoint to create a new user
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserEntity user) {
    	System.out.println("mahender sign up page"); 
        // Check if user with the same email already exists
        Optional<UserEntity> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>("User with this email already exists!", HttpStatus.BAD_REQUEST);
        }

        // Check if passwords match
        if (!user.getPassword().equals(user.getConPassword())) {
            return new ResponseEntity<>("Passwords do not match!", HttpStatus.BAD_REQUEST);
        }

        // Save the user
        userService.saveUser(user);
        return new ResponseEntity<>("User signed up successfully!", HttpStatus.CREATED);
    }


    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Optional<UserEntity> user = userService.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return new ResponseEntity<>("Login successful!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password!", HttpStatus.UNAUTHORIZED);
        }
    }
}

