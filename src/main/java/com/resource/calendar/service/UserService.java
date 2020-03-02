package com.resource.calendar.service;

import com.resource.calendar.model.User;

public interface UserService {

	User getUserByUserName(String username);

	User saveUser(User userdata);

}
