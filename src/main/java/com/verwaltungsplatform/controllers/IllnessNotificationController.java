package com.verwaltungsplatform.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	String eMailPassword = "xxxx";
	
	// Gibt dem Sekretariat alle Krankmeldungen eines bestimmten Tages aus
	@GetMapping("/sekretariat/krankmeldungen")
	@ResponseBody
	public List<IllnessDto> getIllnessNotifications() {
		
		List<IllnessDto> notifications = illnessNotificationService.getAllIllnessDay();
		
		return notifications;
	}
	
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
	public IllnessDto addIllnessNotificationTeacher(@RequestBody Map<String,String> teacherData) {
		
		int teacherIdToInteger = Integer.valueOf(teacherData.get("teacherId"));
		
		IllnessDto notificationDto = illnessNotificationService.createIllnessNotification(teacherIdToInteger);
		illnessNotificationService.saveIllnessNotification(notificationDto);

//		String teacherFirstName = userRepo.getFirstName(teacherIdToInteger);
//		String teacherLastName = userRepo.getLastName(teacherIdToInteger);
//		MailSender sender = new MailSender();
//		sender.login("smtp.gmail.com", "465", eMailUsername, eMailPassword);
//		
//		List<String> email = illnessNotificationService.getEmailsByTeacher(teacherIdToInteger);
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
		
		System.out.println(notificationDto.getAffectedUserId());
		return notificationDto;
	}
	
	
	// Ein Elternteil erstellt eine Krankmeldung für ein Kind
	@PostMapping("/eltern/krankmeldungen/neuekrankmeldung")
	@ResponseBody
	public IllnessDto addIllnessNotificationChild(@RequestBody Map<String,String> newSicknoteData) throws ParseException {
		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//	    java.util.Date date= null;
//
//	    date = format.parse(newSicknoteData.get("date"));
//	    
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		IllnessDto notificationDto = illnessNotificationService.createIllnessNotificationParent(Integer.valueOf(newSicknoteData.get("parentId")));
		
		illnessNotificationService.saveIllnessNotification(notificationDto);
		
		return notificationDto;
	}
	

}
