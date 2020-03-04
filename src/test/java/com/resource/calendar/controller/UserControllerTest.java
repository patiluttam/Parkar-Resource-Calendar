package com.resource.calendar.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.resource.calendar.model.User;
import com.resource.calendar.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService mockUserService;
	
	private static final String LOGIN_PAYLOAD = "{\r\n" + 
			"  \"username\":\"admin\",\r\n" + 
			"  \"password\":\"admin\"\r\n" + 
			"  }";
	
	private static final String REGISTRATION_PAYLOAD = "{\r\n" + 
			"  \"username\":\"admin\",\r\n" + 
			"  \"password\":\"admin\",\r\n" + 
			"  \"email\":\"test@gmail.com\",\r\n" + 
			"  \"confirmPassword\":\"admin\"\r\n" + 
			"  }";
	
	
	@org.junit.jupiter.api.Test
	public void testLogin() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/login").accept(MediaType.APPLICATION_JSON))
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("login.html")))
		    .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void postLogin() throws Exception {
		User user = new User();
		user.setId(1L);
		user.setUsername("admin");
		user.setPassword("admin");
		user.setConfirmPassword("");
		user.setEmail("test@gmail.com");
		BDDMockito.given(mockUserService.getUserByUserName(Mockito.anyString())).willReturn(user);
		mvc.perform(MockMvcRequestBuilders.post("/login")
				                          .contentType(MediaType.APPLICATION_JSON)
				                          .content(LOGIN_PAYLOAD)
				                          .accept(MediaType.APPLICATION_JSON))
		   .andExpect(MockMvcResultMatchers.status().isOk())
		   .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("User_DashBoard.html")))
		   .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void registration() throws Exception {
		User user = new User();
		user.setId(1L);
		user.setUsername("admin");
		user.setPassword("admin");
		user.setConfirmPassword("");
		user.setEmail("test@gmail.com");
		
		BDDMockito.given(mockUserService.getUserByUserName(Mockito.anyString())).willReturn(null);
		
		BDDMockito.given(mockUserService.saveUser(Mockito.any())).willReturn(user);
		
		mvc.perform(MockMvcRequestBuilders.post("/registration")
				                          .contentType(MediaType.APPLICATION_JSON)
				                          .content(REGISTRATION_PAYLOAD)
				                          .accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.jsonPath("$.username").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.password").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.email").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.confirmPassword").exists())
		.andDo(MockMvcResultHandlers.print());
		
	}
}
