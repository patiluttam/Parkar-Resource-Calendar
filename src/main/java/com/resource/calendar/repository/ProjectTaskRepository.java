package com.resource.calendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resource.calendar.model.ProjectTask;

public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {

	List<ProjectTask> findByProjectId(Long projectId);

}
