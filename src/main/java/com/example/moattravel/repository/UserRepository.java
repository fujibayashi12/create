package com.example.moattravel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moattravel.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	 Optional<User> findByEmail(String email);


}
