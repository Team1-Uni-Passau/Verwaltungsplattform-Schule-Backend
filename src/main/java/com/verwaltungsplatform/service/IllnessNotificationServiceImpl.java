package com.verwaltungsplatform.service;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.repositories.IllnessNotificationRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.repositories.UserRepository;
import com.verwaltungsplatform.dto.FamilyDto;
import com.verwaltungsplatform.dto.IllnessDto;
import com.verwaltungsplatform.model.IllnessNotification;
import com.verwaltungsplatform.model.User;



@Service
public class IllnessNotificationServiceImpl implements IllnessNotificationService {

	
	
	@Autowired
	private IllnessNotificationRepository illnessNotificationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	@Autowired
	private FamilyServiceImpl familyServiceImpl;
	
	
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
			
			if (schoolClassRepository.existsById(userId)){
				FamilyDto familyDto = new FamilyDto();
				familyDto = familyServiceImpl.getFamilyDto(userId);
				userId = familyDto.getStudentId();
			}
			
			IllnessDto illnessDto = new IllnessDto(userId);
			User user = userRepository.getOne(userId);
			illnessDto.setFirstName(user.getFirstName());
			illnessDto.setLastName(user.getLastName());
			illnessDto.setRolle(user.getRoleRegisterCodeMapper().getRole());
			return illnessDto;
		}
		
	
	//@param klassen-id
	//@return List of all IllnessNotifications today with id, affectedUserId, firstName, lastName and role
	public List<IllnessDto> getAllIllnessDay(Date date) {
		return ((List<IllnessNotification>) illnessNotificationRepository
				.findByDate(date))
				.stream()
				.map(this::convertToIllnessDto).collect(Collectors.toList());
		
	}
	
	private IllnessDto convertToIllnessDto(IllnessNotification illnessNotification) {
		IllnessDto illnessDto = new IllnessDto();
		illnessDto.setAffectedUserId(illnessNotification.getAffectedUser());
		User user = userRepository.getOne(illnessNotification.getAffectedUser());
		illnessDto.setFirstName(user.getFirstName());
		illnessDto.setLastName(user.getLastName());
		illnessDto.setRolle(user.getRoleRegisterCodeMapper().getRole());
		
		return illnessDto;
	}
 	
    
	
}
