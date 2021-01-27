package com.verwaltungsplatform.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@GetMapping("/lernender/ankuendigungen/{studentId}")
	@ResponseBody
	public List<NotificationDto> getNotificationsStudent(@PathVariable("studentId") int studentId) {
		
		List<NotificationDto> notification = notificationService.getAllNotificationsRoleAndClass(studentId);
		
		return notification;
	}
	
	// Benötigte Ausgabe: List<NotificationDto>
	// Anzeigen aller betreffenden Ankündigungen als ein Lehrer
	@GetMapping("/lehrender/ankuendigungen/{teacherId}")
	@ResponseBody
	public List<NotificationDto> getNotificationsTeacher(@PathVariable("teacherId")int teacherId) {
		
		List<NotificationDto> notification = notificationService.getAllNotificationsTeacher(teacherId);
		
		return notification;
	}
	
	// Benötigte Ausgabe: List<NotificationDto>
	// Anzeigen aller betreffenden Ankündigungen als ein Elternteil
	@GetMapping("/eltern/ankuendigungen/{parentId}")
	@ResponseBody
	public List<NotificationDto> getNotificationsParent(@PathVariable("parentId") int parentId) {
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
	public NotificationDto addNotificationGeneral(@RequestBody Map<String,String> eventData) throws java.text.ParseException {			
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date startDate= null;
	    java.util.Date endDate= null;

	    startDate = format.parse(eventData.get("startDate"));
		endDate = format.parse(eventData.get("endDate"));
	    
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

		Notification newNotification = new Notification(Integer.valueOf(eventData.get("userId")),sqlStartDate, sqlEndDate, eventData.get("content"));
		notificationRepo.save(newNotification);
		
		NotificationDto response = notificationService.convertToNotificationDto(newNotification);
		
		return response;
	}
	
	// Erstellen einer neuen rollenspezifischen Ankündigung als Sekretariatsmitglied
	@PostMapping("/sekretariat/neueankuendigungrolle")
	@ResponseBody
	public NotificationDto addNotificationRole(@RequestBody Map<String,String> eventData) throws java.text.ParseException {
	   
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date startDate= null;
	    java.util.Date endDate= null;

	    startDate = format.parse(eventData.get("startDate"));
		endDate = format.parse(eventData.get("endDate"));
	    
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

		Notification newNotification = new Notification(Integer.valueOf(eventData.get("userId")),sqlStartDate, sqlEndDate, eventData.get("role"),eventData.get("content"));
		notificationRepo.save(newNotification);
		
		NotificationDto response = notificationService.convertToNotificationDto(newNotification);
		
		return response;
	}
	
	// Erstellen einer neuen klassenspezifischen Ankündigung als Lehrer
	@PostMapping("/lehrender/neueankuendigungklasse")
	@ResponseBody
	public NotificationDto addNotificationClass(@RequestBody Map<String,String> eventData) throws java.text.ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date startDate = null;
		java.util.Date endDate = null;
		
		startDate = format.parse(eventData.get("startDate"));
		endDate = format.parse(eventData.get("endDate"));
		
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		
		Notification newNotification = new Notification(Integer.valueOf(eventData.get("userId")), sqlStartDate, sqlEndDate, eventData.get("classId"), eventData.get("content"));
		notificationRepo.save(newNotification);
		
		NotificationDto response = notificationService.convertToNotificationDto(newNotification);
		
		return response;
	}
	
	// Ändern einer bestehenden Ankündigung als Sekretariat
	@PutMapping("/sekretariat/ankuendigungen/edit/{notificationId}")
	@ResponseBody
	public NotificationDto editNotification(@PathVariable("notificationId") int notificationId, @RequestBody Map<String,String> modifiedEventData ) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date startDate= null;
	    java.util.Date endDate= null;

	    startDate = format.parse(modifiedEventData.get("startDate"));
		endDate = format.parse(modifiedEventData.get("endDate"));
	    
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

		Notification notification = notificationRepo.getOne(notificationId);
		notification.setStart(sqlStartDate);
		notification.setEnd(sqlEndDate);
		notification.setRole(modifiedEventData.get("role"));
		notification.setContent(modifiedEventData.get("content"));
		
		System.out.println(notification.getContent());
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
