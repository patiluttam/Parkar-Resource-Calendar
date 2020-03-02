package com.resource.calendar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PROJECT_MAPPING")
public class UserProjectMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long projectMappingId;
	private Long projectId;
	private Long userId;
	public Long getProjectMappingId() {
		return projectMappingId;
	}
	public void setProjectMappingId(Long projectMappingId) {
		this.projectMappingId = projectMappingId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
