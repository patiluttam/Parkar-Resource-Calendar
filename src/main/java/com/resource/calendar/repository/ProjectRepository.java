package com.resource.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resource.calendar.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	Project findByProjectName(String projectName);

}
