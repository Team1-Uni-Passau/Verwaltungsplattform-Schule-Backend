package com.verwaltungsplatform.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	
	
	// Jedem Nutzer, der im System angemeldet ist, werden auf der Startseite die aktuellen Ankündigungen angezeigt
	// Das  „Sekretariat“  kann Ankündigungen   verwalten   und   verfassen (Sichtbarkeit  nach  Rollen)
	// Nach Erstellung einer Ankündigung ist es möglich die Ankündigung zu löschen
	// „Lehrende“  können klassenspezifische   Ankündigungen   verfassen
	
	
	
	
	
	
	// Anzeigen vpn Ankündigungen funktioniert nicht
	@GetMapping("/lernender/ankuendigungen")
	@ResponseBody
	public List<Notification> getNotificationsStudent() {
		
		List<Notification> notification = notificationRepo.findAll();
		
		return notification;
	}
	
	
	// Erstellen einer neuen Ankündigung funktioniert
	@PostMapping("/sekretariat/neueankuendigungallgemein")
	@ResponseBody
	public Notification addNotification(int user, Date start, Date end, String content) {
		
		Notification newNotification = new Notification(user, start, end, content);
		notificationRepo.save(newNotification);
		return newNotification;
	}

}
