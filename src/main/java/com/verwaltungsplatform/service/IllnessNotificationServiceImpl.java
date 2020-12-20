package com.verwaltungsplatform.service;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.repositories.IllnessNotificationRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.repositories.UserRepository;
import com.verwaltungsplatform.dto.IllnessConfirmationDto;
import com.verwaltungsplatform.dto.IllnessDto;
import com.verwaltungsplatform.dto.NotificationDto;
import com.verwaltungsplatform.model.IllnessNotification;
import com.verwaltungsplatform.model.Notification;
import com.verwaltungsplatform.model.SchoolClass;
import com.verwaltungsplatform.model.User;



@Service
public class IllnessNotificationServiceImpl implements IllnessNotificationService {

	
	
	@Autowired
	private IllnessNotificationRepository illnessNotificationRepository;
	@Autowired
	private UserRepository userRepository;
	
	//saves new illness notification with illnessDto and date
		public void saveIllnessNotification (IllnessDto illnessDto, Date date) {
		
	        //Creates a new notification entity
			IllnessNotification illnessNotification = new IllnessNotification(illnessDto.getAffectedUserId(), 
					date);
			

			// Saves the notification entity in the database
			illnessNotificationRepository.save(illnessNotification);
		}
	
	//create a new illnessDto with userId of teacher or parent
		public IllnessDto createIllnessNotification(int userId) {
			IllnessDto illnessDto = new IllnessDto(userId);
			User user = userRepository.getOne(userId);
			illnessDto.setFirstName(user.getFirstName());
			illnessDto.setLastName(user.getLastName());
			illnessDto.setRolle(user.getRole());
			return illnessDto;
		}
		
	
	//@param klassen-id
	//@return List of all IllnessNotifications today with id, firstName, lastName and role
	public List<IllnessDto> getAllIllnessToday() {
		return ((List<IllnessNotification>) illnessNotificationRepository
				.getIllnessNotificationToday())
				.stream()
				.map(this::convertToIllnessDto).collect(Collectors.toList());
		
	}
	
	private IllnessDto convertToIllnessDto(IllnessNotification illnessNotification) {
		IllnessDto illnessDto = new IllnessDto();
		illnessDto.setAffectedUserId(illnessNotification.getId());
		int userId = illnessNotification.getId();
		User user = userRepository.getOne(userId);
		illnessDto.setFirstName(user.getFirstName());
		illnessDto.setLastName(user.getLastName());
		illnessDto.setRolle(user.getRole());
		
		return illnessDto;
	}
 	
    
	
}
