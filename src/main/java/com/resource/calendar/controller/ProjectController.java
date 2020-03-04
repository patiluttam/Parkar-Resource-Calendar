package com.resource.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.resource.calendar.model.Task;
import com.resource.calendar.model.TaskManagement;
import com.resource.calendar.service.TaskManagementService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class ProjectController {
	
	@Autowired
	private TaskManagementService taskManagementService;
	
	@PostMapping("project")
	public ResponseEntity<?> projectDetails(@RequestBody TaskManagement taskManagement) {
		
		if(Strings.isNullOrEmpty(taskManagement.getProjectName()) || Strings.isNullOrEmpty(taskManagement.getUserName())) {
			return new ResponseEntity<String>("Invalid data.", HttpStatus.BAD_REQUEST);
		}
		
		TaskManagement tasks = taskManagementService.saveTasks(taskManagement);
		
		if(tasks != null) {
			return new ResponseEntity<TaskManagement>(taskManagement, HttpStatus.ACCEPTED);
		}

		return null;
		
	}
	
	@GetMapping("project/task/{projectName}")
	public ResponseEntity<?> getProjectTaskDetails(@PathVariable String projectName) {
		
		if(Strings.isNullOrEmpty(projectName)) {
			return new ResponseEntity<String>("Project Name is compulsary", HttpStatus.BAD_REQUEST);
		}
		
		TaskManagement task = taskManagementService.getTaskByProjectName(projectName);
		
		if(task != null) {
			return new ResponseEntity<TaskManagement>(task, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Bad Request.", HttpStatus.BAD_REQUEST);
		
	}
	
	@PatchMapping("project/task")
	public ResponseEntity<?> updateTaskDetails(@RequestBody TaskManagement taskManagement) {
		TaskManagement task = taskManagementService.updateTaskDetails(taskManagement);
		if(task != null) {
			return new ResponseEntity<TaskManagement>(task, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Bad Request.", HttpStatus.BAD_REQUEST);
		
	}

}
