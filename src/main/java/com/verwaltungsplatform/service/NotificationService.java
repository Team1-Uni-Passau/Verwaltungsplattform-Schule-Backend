package com.verwaltungsplatform.service;

import java.util.List;

import com.verwaltungsplatform.dto.NotificationDto;
import com.verwaltungsplatform.model.Notification;

public interface NotificationService {
	
//	void saveNotificationRole (NotificationDto notificationDto, int userId, String role);
//	void saveNotification (NotificationDto notificationDto, int userId);
//	void saveNotificationClass (NotificationDto notificationDto, int userId, String classId);
	List<NotificationDto> getAllNotifications();
	List<NotificationDto> getAllNotClassId(int userId);
	List<NotificationDto> getAllNotRole(int userId);
	List<NotificationDto> getAllNotificationsRoleAndClass(int userId);
	List<NotificationDto> getSecretaryNotifications();
	NotificationDto convertToNotificationDto(Notification notification);
	List<NotificationDto> getAllNotificationsTeacher(int teacherId);

}
