package com.example.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oauth.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
