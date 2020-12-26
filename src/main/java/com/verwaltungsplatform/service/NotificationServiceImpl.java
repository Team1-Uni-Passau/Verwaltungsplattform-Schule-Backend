package com.verwaltungsplatform.service;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.verwaltungsplatform.repositories.NotificationRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.repositories.UserRepository;
import com.verwaltungsplatform.dto.FamilyDto;
import com.verwaltungsplatform.dto.NotificationDto;
import com.verwaltungsplatform.model.User;
import com.verwaltungsplatform.model.Notification;


@Service
public class NotificationServiceImpl implements NotificationService {

	
	
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	@Autowired
	private FamilyServiceImpl familyServiceImpl;
	@Autowired
	private UserRepository userRepository;
	
	
	//saves new notification for role
	public void saveNotificationRole (NotificationDto notificationDto, int userId, String role) {
	
        //Creates a new notification entity
		Notification notification = new Notification(userId, notificationDto.getStartdate(), notificationDto.getEnddate(),
								role, notificationDto.getContent());
		

		// Saves the notification entity in the database
		notificationRepository.save(notification);
	}
	
	//saves new notification for class Id
	public void saveNotificationClass (NotificationDto notificationDto, int userId, String classId) {
		
        //Creates a new notification entity
		Notification notification = new Notification(userId, classId, notificationDto.getContent(), 
				notificationDto.getStartdate(), notificationDto.getEnddate());
		

		// Saves the notification entity in the database
		notificationRepository.save(notification);
	}
	
	public List<NotificationDto> getAllNotificationsRoleAndClass(int userId) {
		List<NotificationDto> roleNotifications = getAllNotificationsRole(userId);
		List<NotificationDto> classNotifications = getAllNotificationsClassId(userId);
		roleNotifications.addAll(classNotifications);
		return roleNotifications;
	}
	/*@param userId
	 * @return list of all current notifications for the role 
	 */
	public List<NotificationDto> getAllNotificationsRole(int userId) {
		User user = userRepository.getOne(userId);
		String rolle = user.getRoleRegisterCodeMapper().getRole();
		return ((List<Notification>) notificationRepository
				.findByRole(rolle))
				.stream()
				.map(this::convertToNotificationDto).collect(Collectors.toList());
		
	}
	
	/* @param a userId of a student or parent
	 * @return list of all notifications for a class
	 */
	public List<NotificationDto> getAllNotificationsClassId(int userId) {
		
		String klassenId;
		if (schoolClassRepository.existsById(userId)) {
			klassenId = schoolClassRepository.getName(userId);
		}
		else {
			FamilyDto familyDto = new FamilyDto();
			familyDto = familyServiceImpl.getFamilyDto(userId);
			klassenId = familyDto.getClassId();
		}
		
		return ((List<Notification>) notificationRepository
				.findByKlassenId(klassenId))
				.stream()
				.map(this::convertToNotificationDto).collect(Collectors.toList());
	}
	
	
	//method supports getAllNotification
	
	private NotificationDto convertToNotificationDto(Notification notification) {
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setEnddate(notification.getEnd());
		notificationDto.setStartdate(notification.getStart());
		notificationDto.setContent(notification.getContent());
		return notificationDto;
	}
 	
    
	
}
