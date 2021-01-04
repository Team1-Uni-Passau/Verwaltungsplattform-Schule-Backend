package com.verwaltungsplatform.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	
	// Anzeigen aller betreffenden Ankündigungen als ein Schüler
	@GetMapping("/lernender/ankuendigungen")
	@ResponseBody
	public List<Notification> getNotificationsStudent(int studentId) {
		
		List<Notification> notification = notificationService.getAllNotificationsRoleAndClass(studentId);
		
		return notification;
	}
	
	// Anzeigen aller betreffenden Ankündigungen als ein Lehrer
	// Funktioniert nicht für mehrere Klassen
	@GetMapping("/lehrender/ankuendigungen")
	@ResponseBody
	public List<Notification> getNotificationsTeacher(int teacherId) {
		
		List<Notification> notification = notificationService.getAllNotificationsRoleAndClass(teacherId);
		
		return notification;
	}
	
	// Anzeigen aller betreffenden Ankündigungen als ein Elternteil
	@GetMapping("/eltern/ankuendigungen")
	@ResponseBody
	public List<Notification> getNotificationsParent(int parentId) {
		
		List<Notification> notification = notificationService.getAllNotificationsRoleAndClass(parentId);
		
		return notification;
	}
	
	// Anzeigen aller betreffenden Ankündigungen als ein Sekretariatsmitglied
	@GetMapping("/sekretariat/ankuendigungen")
	@ResponseBody
	public List<Notification> getNotificationsSecretariat(int secretariatId) {
		
		List<Notification> notification = notificationService.getAllNotRole(secretariatId);
		notification.addAll(notificationService.getAllNotifications());
		
		return notification;
	}
	
	// Anzeigen aller Ankündigungen als Sekretariatsmitglied
	@GetMapping("/sekretariat/alleankuendigungen")
	@ResponseBody
	public List<Notification> getAllNotifications() {
		
		List<Notification> notification = notificationRepo.findAll();
		
		return notification;
	}
	
	// Erstellen einer neuen allgemeinen Ankündigung als Sekretariatsmitglied
	@PostMapping("/sekretariat/neueankuendigungallgemein")
	@ResponseBody
	public Notification addNotificationGeneral(int user, Date start, Date end, String content) {
		
		Notification newNotification = new Notification(user, start, end, content);
		notificationRepo.save(newNotification);
		return newNotification;
	}
	
	// Erstellen einer neuen rollenspezifischen Ankündigung als Sekretariatsmitglied
	@PostMapping("/sekretariat/neueankuendigungrolle")
	@ResponseBody
	public Notification addNotificationRole(int user, Date start, Date end, String role, String content) {
		
		Notification newNotification = new Notification(user, start, end, role, content);
		notificationRepo.save(newNotification);
		return newNotification;
	}
	
	// Erstellen einer neuen klassenspezifischen Ankündigung als Lehrer
	@PostMapping("/lehrender/neueankuendigungklasse")
	@ResponseBody
	public Notification addNotificationClass(int user, String classId, String content, Date start, Date end) {
		
		Notification newNotification = new Notification(user, classId, content, start, end);
		notificationRepo.save(newNotification);
		return newNotification;
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
