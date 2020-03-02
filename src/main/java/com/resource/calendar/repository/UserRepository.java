package com.resource.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resource.calendar.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

}
