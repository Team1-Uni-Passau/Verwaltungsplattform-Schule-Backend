package com.verwaltungsplatform.service;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	

	
	//saves new presence entry with presenceDto
		public void savePresenceEntry (PresenceDto presenceDto) {
			
	        //Creates a new presence entity
			boolean confirmation = false;
			if(illnessNotificationRepository.existsById(presenceDto.getAffectedUserId())) {
				confirmation = illnessNotificationRepository.findByAffectedUser(presenceDto.getAffectedUserId()).getConfirmation();
			}
			Date date = java.sql.Date.valueOf(presenceDto.getDate());
			Presence presence = new Presence(presenceDto.getAffectedUserId(), date, confirmation, presenceDto.isPresence(), presenceDto.getLesson());
			

			// Saves the presence entity in the database
			presenceRepository.save(presence);
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
	
}





