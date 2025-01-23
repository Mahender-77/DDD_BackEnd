package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.UserRepo;
import com.example.demo.entity.UserEntity;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    // Method to find user by phone number
    public Optional<UserEntity> findByPhoneNumber(String phoneNumber) {
        return repo.findByPhoneNumber(phoneNumber);
    }

    // Method to find user by email
    public Optional<UserEntity> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    // Method to save a new user
    public UserEntity saveUser(UserEntity user) {
        return repo.save(user);
    }
}
