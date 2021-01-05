package com.verwaltungsplatform.service;


import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.verwaltungsplatform.repositories.NotificationRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.repositories.UserRepository;
import com.verwaltungsplatform.dto.FamilyDto;
import com.verwaltungsplatform.dto.IllnessDto;
import com.verwaltungsplatform.dto.NotificationDto;
import com.verwaltungsplatform.model.User;
import com.verwaltungsplatform.model.IllnessNotification;
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
//	public void saveNotificationRole (NotificationDto notificationDto, int userId, String role) {
//	
//        //Creates a new notification entity
//		Notification notification = new Notification(userId, notificationDto.getStartdate(), notificationDto.getEnddate(),
//								role, notificationDto.getContent());
//		
//
//		// Saves the notification entity in the database
//		notificationRepository.save(notification);
//	}
//
//
//	//saves new notification for all roles
//	public void saveNotification (NotificationDto notificationDto, int userId) {
//	
//        //Creates a new notification entity
//		Notification notification = new Notification(userId, notificationDto.getStartdate(), notificationDto.getEnddate(), notificationDto.getContent());
//		
//
//		// Saves the notification entity in the database
//		notificationRepository.save(notification);
//	}
//		
//	//saves new notification for class Id
//	public void saveNotificationClass (NotificationDto notificationDto, int userId, String classId) {
//		
//        //Creates a new notification entity
//		Notification notification = new Notification(userId, classId, notificationDto.getContent(), 
//				notificationDto.getStartdate(), notificationDto.getEnddate());
//		
//
//		// Saves the notification entity in the database
//		notificationRepository.save(notification);
//	}
//	
	/*@param userId
	 * @return list of all current notifications for the role, all roles and the class (of user or child of user)
	 */
	public List<Notification> getAllNotificationsRoleAndClass(int userId) {
		List<Notification> roleNotifications = getAllNotRole(userId);
		List<Notification> classNotifications = getAllNotClassId(userId);
		List<Notification> allNotifications = getAllNotifications();
		roleNotifications.addAll(classNotifications);
		roleNotifications.addAll(allNotifications);

		return roleNotifications;
	}

		
	//@return list of all Notifications with no defined class or role
	public List<Notification> getAllNotifications() {
		List<Notification> notification = notificationRepository.findByRoleIsNullAndClassIdIsNull();
		return notification;
		
	}
	
	//@return list of all Notifications with no defined class or role
		public List<NotificationDto> getSecretaryNotifications() {
			return ((List<Notification>) notificationRepository
					.findByClassIdIsNull())			
					.stream()
					.map(this::convertToNotificationDto).collect(Collectors.toList());
			
		}
		
		private NotificationDto convertToNotificationDto(Notification notification) {
			NotificationDto notificationDto = new NotificationDto();
			notificationDto.setContent(notification.getContent());
			notificationDto.setRolle(notification.getRole());
			notificationDto.setClassId(notification.getClassId());
			notificationDto.setIdNotification(notification.getId());
			String startDate = notification.getStart().toString();
			notificationDto.setStartdate(startDate);
			String endDate = notification.getEnd().toString();
			notificationDto.setEnddate(endDate);
			
			return notificationDto;
		}
	 	
			
	
	//@return all Notifications for a role
	public List<Notification> getAllNotRole(int userId) {
		User user = userRepository.getOne(userId);
		String rolle = user.getRoleRegisterCodeMapper().getRole();
		List<Notification> notification = notificationRepository.findByRole(rolle);
		
		return notification;
		
	}


	//@return List of Notification for a class
	public List<Notification> getAllNotClassId(int userId) {
		
		String klassenId;
		if (schoolClassRepository.existsById(userId)) {
			klassenId = schoolClassRepository.getName(userId);
		}
		else {
			FamilyDto familyDto = new FamilyDto();
			familyDto = familyServiceImpl.getFamilyDto(userId);
			klassenId = familyDto.getClassId();
		}
		
		List<Notification> notification = notificationRepository.findByClassId(klassenId);
		return notification;
}
	
 	
    
	
}
