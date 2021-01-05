package com.verwaltungsplatform.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verwaltungsplatform.dto.NotificationDto;
import com.verwaltungsplatform.model.Notification;
import com.verwaltungsplatform.repositories.NotificationRepository;
import com.verwaltungsplatform.service.NotificationService;

@Controller
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private NotificationRepository notificationRepo;

	// Benötigte Ausgabe: List<NotificationDto>
	// Anzeigen aller betreffenden Ankündigungen als ein Schüler
	@GetMapping("/lernender/ankuendigungen")
	@ResponseBody
	public List<NotificationDto> getNotificationsStudent(int studentId) {
		
		List<NotificationDto> notification = notificationService.getAllNotificationsRoleAndClass(studentId);
		
		return notification;
	}
	
	// Benötigte Ausgabe: List<NotificationDto>
	// Anzeigen aller betreffenden Ankündigungen als ein Lehrer
	// Funktioniert nicht für mehrere Klassen
	@GetMapping("/lehrender/ankuendigungen")
	@ResponseBody
	public List<NotificationDto> getNotificationsTeacher(int teacherId) {
		
		List<NotificationDto> notification = notificationService.getAllNotificationsRoleAndClass(teacherId);
		
		return notification;
	}
	
	// Benötigte Ausgabe: List<NotificationDto>
	// Anzeigen aller betreffenden Ankündigungen als ein Elternteil
	@GetMapping("/eltern/ankuendigungen")
	@ResponseBody
	public List<NotificationDto> getNotificationsParent(int parentId) {
		
		List<NotificationDto> notification = notificationService.getAllNotificationsRoleAndClass(parentId);
		
		return notification;
	}
	
//	// Anzeigen aller betreffenden Ankündigungen als ein Sekretariatsmitglied
//	@GetMapping("/sekretariat/ankuendigungen")
//	@ResponseBody
//	public List<Notification> getNotificationsSecretariat(int secretariatId) {
//		
//		List<Notification> notification = notificationService.getAllNotRole(secretariatId);
//		notification.addAll(notificationService.getAllNotifications());
//		
//		return notification;
//	}
	
	// Anzeigen aller Ankündigungen als Sekretariatsmitglied
	@GetMapping("/sekretariat/alleankuendigungen")
	@ResponseBody
	public List<NotificationDto> getAllNotifications() {
		
		List<NotificationDto> notification = notificationService.getSecretaryNotifications();

		
		return notification;
	}
	
	// Erstellen einer neuen allgemeinen Ankündigung als Sekretariatsmitglied
	@PostMapping("/sekretariat/neueankuendigungallgemein")
	@ResponseBody
	public NotificationDto addNotificationGeneral(int user, Date start, Date end, String content) {
		
		Notification newNotification = new Notification(user, start, end, content);
		notificationRepo.save(newNotification);
		
		NotificationDto response = notificationService.convertToNotificationDto(newNotification);
		
		return response;
	}
	
	// Erstellen einer neuen rollenspezifischen Ankündigung als Sekretariatsmitglied
	@PostMapping("/sekretariat/neueankuendigungrolle")
	@ResponseBody
	public NotificationDto addNotificationRole(int user, Date start, Date end, String role, String content) {
		
		Notification newNotification = new Notification(user, start, end, role, content);
		notificationRepo.save(newNotification);
		
		NotificationDto response = notificationService.convertToNotificationDto(newNotification);
		
		return response;
	}
	
	// Erstellen einer neuen klassenspezifischen Ankündigung als Lehrer
	@PostMapping("/lehrender/neueankuendigungklasse")
	@ResponseBody
	public NotificationDto addNotificationClass(int user, String classId, String content, Date start, Date end) {
		
		Notification newNotification = new Notification(user, classId, content, start, end);
		notificationRepo.save(newNotification);
		
		NotificationDto response = notificationService.convertToNotificationDto(newNotification);
		
		return response;
	}
	
	// Ändern einer bestehenden Ankündigung als Sekretariat
	@PutMapping("/sekretariat/ankuendigungen/edit/{notificationId}")
	@ResponseBody
	public NotificationDto editNotification(@PathVariable("notificationId") int notificationId, Date start, Date end, String role, String content) {
		
		Notification notification = notificationRepo.getOne(notificationId);
		notification.setStart(start);
		notification.setEnd(end);
		notification.setRole(role);
		notification.setContent(content);
		notificationRepo.save(notification);
		
		NotificationDto response = notificationService.convertToNotificationDto(notification);
		
		return response;
	}
	
	// Löschen einer Ankündigung als Sekretariatsmitglied
	@DeleteMapping("/sekretariat/ankuendigungloeschen/{notificationId}")
	@ResponseBody
	public String deleteNotification(@PathVariable("notificationId") int notificationId) {
		
		Notification notification = notificationRepo.getOne(notificationId);
		notificationRepo.delete(notification);
		String response = new String("Die Ankündigung wurde gelöscht");
		return response;
	}

}
