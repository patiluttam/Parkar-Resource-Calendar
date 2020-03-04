package com.resource.calendar.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.resource.calendar.model.Task;
import com.resource.calendar.model.TaskManagement;
import com.resource.calendar.service.TaskManagementService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {
	
		@Autowired
		private MockMvc mvc;
		
		@MockBean
		private TaskManagementService taskManagementService;
		
		private TaskManagement taskManagement;
		
		private Task task;
		
		private static final String PROJECT_DETAILS_PAYLOAD = "{\r\n" + 
				"	\"userName\":\"admin\",\r\n" + 
				"	\"projectName\":\"ResourceCalendar\",\r\n" + 
				"	\"task\":[{\r\n" + 
				"		\"taskName\":\"Create Project Skeleton for Resource Calendar\",\r\n" + 
				"		\"taskDescription\":\"Add all dependencies in project skeleton\",\r\n" + 
				"		\"startDate\":\"2020-02-28T18:25:43\",\r\n" + 
				"		\"endDate\":\"2020-03-05T18:25:43\",\r\n" + 
				"		\"startWorkDate\":\"\",\r\n" + 
				"		\"endWorkDate\":\"\",\r\n" + 
				"		\"taskStatus\":\"Not Started\"\r\n" + 
				"	}]\r\n" + 
				"}";

		public void setUp() {
			task = new Task();
			task.setTaskName("Create Project Skeleton for Resource Calendar");
			task.setTaskStatus("Not Started");
			
			List<Task> tlist = new ArrayList<>();
			tlist.add(task);
			
			taskManagement = new TaskManagement();
			taskManagement.setProjectName("ResourceCalendar");
			taskManagement.setUserName("admin");
			taskManagement.setTask(tlist);
		}
		
		
	
		
		@Test
		public void projectDetailsTest() throws Exception {
			
			this.setUp();
			
			BDDMockito.given(taskManagementService.saveTasks(Mockito.any())).willReturn(taskManagement);
			
			mvc.perform(MockMvcRequestBuilders.post("/project")
					                          .contentType(MediaType.APPLICATION_JSON)
					                          .content(PROJECT_DETAILS_PAYLOAD)
					                          .accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.userName").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.projectName").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.task[0].taskName").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.task[0].taskStatus").exists())
			.andDo(MockMvcResultHandlers.print());			
		}
		
		@Test
		public void getProjectTaskDetailsTest() throws Exception {
			this.setUp();
			
			BDDMockito.given(taskManagementService.getTaskByProjectName(Mockito.anyString())).willReturn(taskManagement);
			
			mvc.perform(MockMvcRequestBuilders.get("/project/task/ResourceCalendar")
					                          .accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.userName").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.projectName").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.task[0].taskName").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.task[0].taskStatus").exists())
			.andDo(MockMvcResultHandlers.print());
		}
}
