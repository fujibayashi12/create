package com.example.moattravel.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moattravel.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	 Optional<User> findByEmail(String email);

	 Page<User> findByNameContainingOrEmailContainingOrPhoneNumberContaining(String name, String email, String phoneNumber, Pageable pageable);


}
