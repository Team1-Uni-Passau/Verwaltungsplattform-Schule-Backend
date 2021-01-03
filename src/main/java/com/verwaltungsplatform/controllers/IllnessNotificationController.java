package com.verwaltungsplatform.controllers;

import java.sql.Date;
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
import com.verwaltungsplatform.repositories.IllnessNotificationRepository;
import com.verwaltungsplatform.service.IllnessNotificationService;

@Controller
public class IllnessNotificationController {
	
	@Autowired
	private IllnessNotificationService illnessNotificationService;
	
	@Autowired
	private IllnessNotificationRepository illnessRepo;
	
	
	// Wenn eine Krankmeldung  eines  „Lernenden“ durch das Sekretariat bestätigt ist, ist dies ersichtlich für Lehrende (Methode zur Ausgabe aller bestätigten Krankmeldungen einer Klasse in der ServiceImpl)
	// Automatischer Email Versand an Schüler eines krankgeschriebenen Lehrers
	
	// Es wird nicht gezeigt, ob die Krankmeldung bereits bestätigt ist (IllnessDto und IllnessConfirmationDto zusammenführen?)
	// Gibt dem Sekretariat alle Krankmeldungen eines bestimmten Tages aus
	@GetMapping("/sekretariat/krankmeldungen")
	@ResponseBody
	public List<IllnessDto> getIllnessNotifications(Date date) {
		
		List<IllnessDto> notifications = illnessNotificationService.getAllIllnessDay(date);
		
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
	@PostMapping("/lehrender/krankmeldungen/neuekrankmeldung")
	@ResponseBody
	public IllnessDto addIllnessNotificationTeacher(int teacherId, Date date) {
		
		IllnessDto notificationDto = illnessNotificationService.createIllnessNotification(teacherId);
		
		illnessNotificationService.saveIllnessNotification(notificationDto, date);
		
		return notificationDto;
	}
	
	
	// Ein Elternteil erstellt eine Krankmeldung für ein Kind
	@PostMapping("/eltern/krankmeldungen/neuekrankmeldung")
	@ResponseBody
	public IllnessDto addIllnessNotificationChild(int parentId, Date date) {
		
		// Die Methode gibt gerade eine Fehlermeldung aus (Eingabe der Id des Kindes möglicherweise besser)
		IllnessDto notificationDto = illnessNotificationService.createIllnessNotification(parentId);
		
		illnessNotificationService.saveIllnessNotification(notificationDto, date);
		
		return notificationDto;
	}
	

}
