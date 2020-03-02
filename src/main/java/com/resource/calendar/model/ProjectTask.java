package com.resource.calendar.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.resource.calendar.util.LocalDateTimeConvertor;

@Entity
@Table(name = "PROJECT_TASK")
public class ProjectTask {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long projectTaskId;
	private Long projectId;
	private String taskName;
	private String taskDescription;
	@Column(name = "start_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime startDate;
	@Column(name = "end_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime endDate;
	@Column(name = "start_work_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime startWorkDate;
	@Column(name = "end_work_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime endWorkDate;
	private String taskStatus;
	public Long getProjectTaskId() {
		return projectTaskId;
	}
	public void setProjectTaskId(Long projectTaskId) {
		this.projectTaskId = projectTaskId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public LocalDateTime getStartWorkDate() {
		return startWorkDate;
	}
	public void setStartWorkDate(LocalDateTime startWorkDate) {
		this.startWorkDate = startWorkDate;
	}
	public LocalDateTime getEndWorkDate() {
		return endWorkDate;
	}
	public void setEndWorkDate(LocalDateTime endWorkDate) {
		this.endWorkDate = endWorkDate;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	

}
