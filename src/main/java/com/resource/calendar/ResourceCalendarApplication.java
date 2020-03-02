package com.resource.calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ResourceCalendarApplication {

	public static void main(String[] args) {

		SpringApplication.run(ResourceCalendarApplication.class, args);
	}

}
