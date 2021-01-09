package com.verwaltungsplatform.service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.repositories.AppointmentRepository;
import com.verwaltungsplatform.repositories.IllnessNotificationRepository;
import com.verwaltungsplatform.repositories.PresenceRepository;
import com.verwaltungsplatform.dto.FamilyDto;
import com.verwaltungsplatform.dto.PresenceDto;
import com.verwaltungsplatform.model.Presence;




@Service
public class PresenceServiceImpl implements PresenceService {

	
	
	@Autowired
	private IllnessNotificationRepository illnessNotificationRepository;
	@Autowired
	private PresenceRepository presenceRepository;
	@Autowired
	private FamilyServiceImpl familyServiceImpl;
	@Autowired
	private AppointmentRepository appointmentRepository;

	

	
	//saves new presence entry with presenceDto
		public Presence savePresenceEntry (PresenceDto presenceDto) {
			Date date = new Date();
			int lesson = transformToLesson(date);
	        //Creates a new presence entity
			boolean confirmation = false;
			if(illnessNotificationRepository.existsById(presenceDto.getAffectedUserId())) {
				confirmation = illnessNotificationRepository.findByAffectedUser(presenceDto.getAffectedUserId()).getConfirmation();}
			
			Presence presence = new Presence(presenceDto.getAffectedUserId(), date, confirmation, presenceDto.isPresence(), lesson);
			

			// Saves the presence entity in the database
			presenceRepository.save(presence);
			return presence;
		}
	
		
	
	/*@param user-id of parent
	*@return List of all precence entries of last seven days with id, 
	*date, hour, presence, illness confirmation and colour
	*not present and no confirmation=red, not present and confirmation =green, present = white
	*default = blue should be set in Frontend
	**/
	public List<PresenceDto> getPresenceEntriesLastWeek(int userId) {
		FamilyDto familyDto = new FamilyDto();
		familyDto = familyServiceImpl.getFamilyDto(userId);
		userId = familyDto.getStudentId();
		
		return ((List<Presence>) presenceRepository
				.findByUserId(userId))
				.stream()
				.map(this::convertToPresenceDto).collect(Collectors.toList());
		
	}
	
	//supports Method getPresenceEntriesLastWeek
	private PresenceDto convertToPresenceDto(Presence presence) {
		PresenceDto presenceDto = new PresenceDto();
		presenceDto.setAffectedUserId(presence.getUserId());
		String date = presence.getDate().toString();
		presenceDto.setDate(date);
		presenceDto.setLesson(presence.getLesson());
		int unterrichtsstunde = appointmentRepository.findHourById(presence.getLesson());
		presenceDto.setUnterrichtsstunde(unterrichtsstunde);
		presenceDto.setPresence(presence.isPresence());
		presenceDto.setConfirmation(presence.isConfirmation());
		presenceDto.setColour(colourPresence(presence.isConfirmation(), presence.isPresence()));
		
		return presenceDto;
	}
 	
	
	private String colourPresence(boolean confirmation, boolean presence) {
	
	if(confirmation && !presence) {
		return "green";}
	if(!confirmation && !presence) {
		return "red";}
	else {
		return "white";
	}
	}

	private int transformToLesson(Date date) {
		Calendar now = Calendar.getInstance();
		int hour = (now.get(Calendar.HOUR_OF_DAY));
		int minute = (now.get(Calendar.MINUTE));
		int us = 1;
		
		switch(hour) {
		case 8:
			if(minute>=0 && minute<45) {
				us=1;
			}
			else {us=2;}
            break;
        case 9:
        	if(minute<30) {
				us=2;
			}
			else {us=3;}
            break;
        case 10:
        	if(minute<30) {
				us=3;
			}
			else {us=4;}
            break;
        case 11:
        	if(minute<15) {
				us=4;
			}
			else {us=5;}
            break;
        case 12:
        	if(minute<15) {
				us=5;
			}
			else {us=6;}
            break;
        case 13:
			us=7;
            break;
        case 14:
        	if(minute<45) {
				us=8;
			}
			else {us=9;}
            break;
        case 15:
        	if(minute<45) {
				us=9;
			}
			else {us=10;}
            break;
        case 16:
        	if(minute<30) {
				us=10;
			}
			else {us=11;}
            break;
        case 17:
        	us=11;
			break;
		}
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		String weekday="";
		
		switch(dayOfWeek) {
		case 2:
            weekday = "Montag";
            break;
        case 3:
        	weekday = "Dienstag";
            break;
        case 4:
        	weekday = "Mittwoch";
            break;
        case 5:
        	weekday = "Donnerstag";
            break;
        case 6:
        	weekday = "Freitag";
            break;
        default:
        	break;
		}
		System.out.println(weekday);
		
		int lessonId = appointmentRepository.findId(weekday, us);
		return lessonId;
	}
    
	
}














