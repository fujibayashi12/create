package com.example.moattravel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moattravel.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{

	Optional<Role> findByName(String string);

}
