package com.verwaltungsplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.dto.UserRegistrationDto;
import com.verwaltungsplatform.model.User;
import com.verwaltungsplatform.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	
	
	@Autowired
	private UserRepository userRepository;
	
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public User save(UserRegistrationDto registrationDto) {
		
		User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(),
								registrationDto.getEmail(), registrationDto.getPassword(), registrationDto.getRole());
								
		return userRepository.save(user);
	}

	
}
