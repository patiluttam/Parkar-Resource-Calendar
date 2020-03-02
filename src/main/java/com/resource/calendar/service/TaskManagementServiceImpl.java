package com.resource.calendar.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.resource.calendar.model.Project;
import com.resource.calendar.model.ProjectTask;
import com.resource.calendar.model.Task;
import com.resource.calendar.model.TaskManagement;
import com.resource.calendar.model.User;
import com.resource.calendar.model.UserProjectMapping;
import com.resource.calendar.repository.ProjectRepository;
import com.resource.calendar.repository.ProjectTaskRepository;
import com.resource.calendar.repository.UserProjectMappingRepository;
import com.resource.calendar.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskManagementServiceImpl implements TaskManagementService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserProjectMappingRepository userProjectMappingRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public TaskManagement saveTasks(TaskManagement taskManagement) {
		// TODO Auto-generated method stub
		try {
		Project project = new Project();
		project.setProjectName(taskManagement.getProjectName());
		
		project = projectRepository.save(project);
		
		User user = userRepository.findByUsername(taskManagement.getUserName());
		
		UserProjectMapping userProjectMapping = new UserProjectMapping();
		userProjectMapping.setProjectId(project.getProjectId());
		userProjectMapping.setUserId(user.getId());
		
		userProjectMapping = userProjectMappingRepository.save(userProjectMapping);
		
		List<Task> task = taskManagement.getTask();
		
		for(Task t : task) {
			ProjectTask projectTask = new ProjectTask();
			projectTask.setProjectId(project.getProjectId());
			projectTask.setTaskName(t.getTaskName());
			projectTask.setTaskDescription(t.getTaskDescription());
			projectTask.setTaskStatus(t.getTaskStatus());
			projectTask.setStartDate(LocalDateTime.parse(t.getStartDate()));
			projectTask.setEndDate(LocalDateTime.parse(t.getEndDate()));
			if(!Strings.isNullOrEmpty(t.getStartWorkDate())) {
				projectTask.setStartWorkDate(LocalDateTime.parse(t.getStartWorkDate()));
			}
			
			if(!Strings.isNullOrEmpty(t.getEndWorkDate())) {
				projectTask.setEndWorkDate(LocalDateTime.parse(t.getEndWorkDate()));
			}
			
			projectTaskRepository.save(projectTask);
		}
		} catch (Exception e) {
			log.error("Exception occured while inserting data: {}", e);
			return null;
		}
		return taskManagement;
	}

	@Override
	public TaskManagement getTaskByProjectName(String projectName) {
		try {
			
			TaskManagement taskManagement = new TaskManagement();
			
			Project project = projectRepository.findByProjectName(projectName);
			
			List<Task> taskList = new ArrayList<Task>();
			
			if(project != null) {
				
				List<ProjectTask> projectTask = projectTaskRepository.findByProjectId(project.getProjectId());
				
				if(projectTask != null && !projectTask.isEmpty()) {
					for(ProjectTask pt : projectTask) {
						Task t = new  Task();
						t.setTaskName(pt.getTaskName());
						t.setTaskDescription(pt.getTaskDescription());
						t.setTaskStatus(pt.getTaskStatus());
						t.setStartDate(pt.getStartDate().toString());
						t.setEndDate(pt.getEndDate().toString());
						t.setStartWorkDate(pt.getStartWorkDate() != null ? pt.getStartWorkDate().toString() : "");
						t.setEndWorkDate(pt.getEndWorkDate() != null ? pt.getEndWorkDate().toString() : "");
						taskList.add(t);
					}
				}
			}
			
			taskManagement.setProjectName(project.getProjectName());
			taskManagement.setTask(taskList);
			
			//taskManagement.setTask(task);
			
			return taskManagement;
		}catch (Exception e) {
			log.error("Exception occured while fetching task details: {}", e);
		}
		
		
		
		return null;
	}

}
