package com.example.demo.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String eamil);
	Optional<UserEntity> findByPhoneNumber(String phoneNumber);
	Optional<UserEntity> findByUsernameAndPhoneNumber(String username, String phoneNumber);

}
