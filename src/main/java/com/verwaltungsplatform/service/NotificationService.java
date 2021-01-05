package com.verwaltungsplatform.service;

import java.util.List;

import com.verwaltungsplatform.dto.NotificationDto;
import com.verwaltungsplatform.model.Notification;

public interface NotificationService {
	
//	void saveNotificationRole (NotificationDto notificationDto, int userId, String role);
//	void saveNotification (NotificationDto notificationDto, int userId);
//	void saveNotificationClass (NotificationDto notificationDto, int userId, String classId);
	List<Notification> getAllNotifications();
	List<Notification> getAllNotClassId(int userId);
	List<Notification> getAllNotRole(int userId);
	List<Notification> getAllNotificationsRoleAndClass(int userId);
	List<NotificationDto> getSecretaryNotifications();

}
