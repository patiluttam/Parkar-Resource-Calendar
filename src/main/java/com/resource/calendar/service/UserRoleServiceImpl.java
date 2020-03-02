package com.resource.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resource.calendar.model.UserRole;
import com.resource.calendar.repository.UserRoleRepository;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public UserRole getUserRoleById(Long id) {
		// TODO Auto-generated method stub
		return userRoleRepository.findByRegistrationId(id);
	}

}
