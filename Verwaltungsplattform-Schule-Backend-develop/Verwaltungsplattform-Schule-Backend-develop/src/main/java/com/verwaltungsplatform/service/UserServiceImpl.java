package com.verwaltungsplatform.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.dto.UserRegistrationDto;
import com.verwaltungsplatform.exceptions.UserAlreadyExistException;
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

 
	// Method to persist a new registered user in the database
	@Override
	public void save(UserRegistrationDto registrationDto) throws UserAlreadyExistException {
		
        //Let's check if user already registered
        if(checkIfUserExist(registrationDto.getEmail())){
            throw new UserAlreadyExistException("A user is already saved in the database with the following e-mail address: "+registrationDto.getEmail());
        }

        //Creates a new user entity
		User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(),
								registrationDto.getEmail(), registrationDto.getPassword(), registrationDto.getRole());
		

		// Persists the user entity in the database
		userRepository.save(user);
	}
	
	
	
	//Method to verify if a user already exists in the users table
    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) !=null ? true : false;
    }


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
    
	
}
