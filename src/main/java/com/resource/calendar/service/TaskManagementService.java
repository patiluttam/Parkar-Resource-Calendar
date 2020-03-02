package com.resource.calendar.service;

import com.resource.calendar.model.Task;
import com.resource.calendar.model.TaskManagement;

public interface TaskManagementService {

	TaskManagement saveTasks(TaskManagement taskManagement);

	TaskManagement getTaskByProjectName(String projectName);
	
}
