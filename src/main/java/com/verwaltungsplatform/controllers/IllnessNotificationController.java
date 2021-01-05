package com.verwaltungsplatform.controllers;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verwaltungsplatform.dto.IllnessDto;
import com.verwaltungsplatform.model.IllnessNotification;
import com.verwaltungsplatform.model.MailSender;
import com.verwaltungsplatform.repositories.IllnessNotificationRepository;
import com.verwaltungsplatform.repositories.UserRepository;
import com.verwaltungsplatform.service.IllnessNotificationService;

@Controller
public class IllnessNotificationController {
	
	@Autowired
	private IllnessNotificationService illnessNotificationService;
	
	@Autowired
	private IllnessNotificationRepository illnessRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	String eMailUsername = "team1.verwaltungsplattform@gmail.com";
	String eMailPassword = "ToSEWS20/21T1";
	
	// Wenn eine Krankmeldung  eines  „Lernenden“ durch das Sekretariat bestätigt ist, ist dies ersichtlich für Lehrende (Methode zur Ausgabe aller bestätigten Krankmeldungen einer Klasse in der ServiceImpl)
	// Automatischer Email Versand an Schüler eines krankgeschriebenen Lehrers
	
	// Es wird nicht gezeigt, ob die Krankmeldung bereits bestätigt ist (IllnessDto und IllnessConfirmationDto zusammenführen?)
	// Gibt dem Sekretariat alle Krankmeldungen eines bestimmten Tages aus
	@GetMapping("/sekretariat/krankmeldungen")
	@ResponseBody
	public List<IllnessDto> getIllnessNotifications() {
		
		List<IllnessDto> notifications = illnessNotificationService.getAllIllnessDay();
		
		return notifications;
	}
	
	// Es fehlt noch ein E-Mail Versand beim aktuellen Datum (automatische Benachrichtigung der Eltern über die Abwesenheit eines „Lehrenden“)
	// Bestätigt eine bestimmte Krankmeldung
	@PutMapping("/sekretariat/krankmeldungen/{illnessNotificationId}")
	@ResponseBody
	public IllnessNotification confirmIllnessNotification(@PathVariable("illnessNotificationId") int illnessNotificationId) {
		
		IllnessNotification notification = illnessRepo.findById(illnessNotificationId);
		notification.setConfirmation(true);
		illnessRepo.save(notification);
		return notification;
	}
	
	// Ein Lehrer erstellt eine Krankmeldung für sich selbst
	//E-Mail-Funktion kriegt Fehlermeldung: Handler dispatch failed; nested exception is java.lang.NoClassDefFoundError: javax/mail/Authenticator
	@PostMapping("/lehrender/krankmeldungen/neuekrankmeldung")
	@ResponseBody
	public IllnessDto addIllnessNotificationTeacher(int teacherId, Date date) {
		
		IllnessDto notificationDto = illnessNotificationService.createIllnessNotification(teacherId);
		
		illnessNotificationService.saveIllnessNotification(notificationDto, date);
		String teacherFirstName = userRepo.getFirstName(teacherId);
		String teacherLastName = userRepo.getLastName(teacherId);
			
//		MailSender sender = new MailSender();
//		sender.login("smtp.gmail.com", "465", eMailUsername, eMailPassword);
//		
//		List<String> email = illnessNotificationService.getEmailsByTeacher(teacherId);
//		for (String parentEmail : email) {
//			try {
//		
//				sender.send("team1.verwaltungsplattform@gmail.com", "Schule Verwaltungsplattform", parentEmail, "Information: Lehrkraft krank", 
//						"Guten Morgen, \rWir möchten Sie darauf hinweisen, dass "+teacherFirstName+" "+teacherLastName+" heute krank geschrieben ist.");
//				
//			} catch(Exception e) {
//				e.printStackTrace();
//		}
//		}

		
		return notificationDto;
	}
	
	
	// Ein Elternteil erstellt eine Krankmeldung für ein Kind
	@PostMapping("/eltern/krankmeldungen/neuekrankmeldung")
	@ResponseBody
	public IllnessDto addIllnessNotificationChild(int parentId, Date date) {
		
		// Die Methode gibt gerade eine Fehlermeldung aus (Eingabe der Id des Kindes möglicherweise besser)
		IllnessDto notificationDto = illnessNotificationService.createIllnessNotificationParent(parentId);
		
		illnessNotificationService.saveIllnessNotification(notificationDto, date);
		
		return notificationDto;
	}
	

}
