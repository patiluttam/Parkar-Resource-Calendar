package com.resource.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.resource.calendar.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

	//@Query("FROM UserRole WHERE registrationId= ?1")
	UserRole findByRegistrationId(Long registrationId);


}
