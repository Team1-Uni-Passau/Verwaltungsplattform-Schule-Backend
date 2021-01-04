package com.verwaltungsplatform.service;

import java.util.List;

import com.verwaltungsplatform.dto.NotificationDto;

public interface NotificationService {
	
	void saveNotificationRole (NotificationDto notificationDto, int userId, String role);
	void saveNotification (NotificationDto notificationDto, int userId);
	void saveNotificationClass (NotificationDto notificationDto, int userId, String classId);
	List<NotificationDto> getAllNotificationsRoleAndClass(int userId);
	List<NotificationDto> getAllNotificationsRole(int userId);
	List<NotificationDto> getAllNotifications();
	List<NotificationDto> getAllNotificationsClassId(int userId);
	

}
