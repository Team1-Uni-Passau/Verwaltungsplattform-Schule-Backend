package com.verwaltungsplatform.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.repositories.IllnessNotificationRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.repositories.UserRepository;
import com.verwaltungsplatform.dto.IllnessConfirmationDto;
import com.verwaltungsplatform.model.SchoolClass;
import com.verwaltungsplatform.model.User;
import com.verwaltungsplatform.model.IllnessNotification;


@Service
public class SchoolClassServiceImpl implements SchoolClassService {

	
	
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IllnessNotificationRepository illnessNotificationRepository;
	
	//@param klassen-id
	//@return List of all students in class with firstName, lastName
	//and yes or no, if there is a IllnessNotification for today
	public List<IllnessConfirmationDto> getAllIllnessConfirmation(String klassenid) {
		return ((List<SchoolClass>) schoolClassRepository
				.findByName(klassenid))
				.stream()
				.map(this::convertToIllnessConfirmationDto).collect(Collectors.toList());
		
	}
	
	private IllnessConfirmationDto convertToIllnessConfirmationDto(SchoolClass schoolclass) {
		IllnessConfirmationDto illnessConfirmationDto = new IllnessConfirmationDto();
		illnessConfirmationDto.setAffectedUserId(schoolclass.getStudent());
		int userId = schoolclass.getStudent();
		User user = userRepository.getOne(userId);
		illnessConfirmationDto.setFirstName(user.getFirstName());
		illnessConfirmationDto.setLastName(user.getLastName());
		boolean confirmation = false;
			if(illnessNotificationRepository.existsById(userId)) {
				confirmation = illnessNotificationRepository.findByAffectedUser(userId).getConfirmation();}
		illnessConfirmationDto.setConfirmation(confirmation);
		illnessConfirmationDto.setPresence(true);
		return illnessConfirmationDto;
	}
 	
    
	
}
