package com.resource.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resource.calendar.model.UserProjectMapping;

public interface UserProjectMappingRepository extends JpaRepository<UserProjectMapping, Long> {

}
