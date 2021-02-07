package com.verwaltungsplatform.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.verwaltungsplatform.dto.IllnessConfirmationDto;
import com.verwaltungsplatform.dto.PresenceDto;
import com.verwaltungsplatform.model.Presence;
import com.verwaltungsplatform.model.SchoolClass;
import com.verwaltungsplatform.repositories.PresenceRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.service.PresenceService;
import com.verwaltungsplatform.service.SchoolClassService;

@Controller
public class SchoolClassController {
	
	@Autowired
	SchoolClassService schoolClassService;
	
	@Autowired
	SchoolClassRepository schoolClassRepo;
	
	@Autowired
	PresenceService presenceService;
	
	@Autowired
	PresenceRepository presenceRepo;
	
	// Fügt einen Schüler einer bestimmten Klasse hinzu. Der Schüler war zuvor keiner Klasse zugeordnet.
	@PostMapping("/sekretariat/klassenliste/keineklasse/klassehinzufuegen")
	@ResponseBody
	public String addStudentToClass(@RequestBody Map<String,String> addId) {
		
		SchoolClass allocation = new SchoolClass(addId.get("classId"), Integer.valueOf(addId.get("studentId")));
		schoolClassRepo.save(allocation);
		
		String response = "Der Schüler wurde der Klasse " + addId.get("classId") + " zugeordnet.";
		return response;
	}
	
	// Ändert die Klasse eines Schülers. Der Schüler war zuvor bereits einer Klasse zugeordnet.
	@PutMapping("/sekretariat/klassenliste/klasseaendern")
	@ResponseBody
	public String changeClass(int studentId, String classId) {
		
		SchoolClass allocation = schoolClassRepo.getOne(studentId);
		allocation.setName(classId);
		schoolClassRepo.save(allocation);
		
		String response = "Der Schüler wurde der Klasse " + classId + " zugeordnet.";
		return response;
	}
	
	// Gibt einem Lehrenden die Klassenliste einer bestimmten Klasse aus
	@GetMapping("/lehrender/klassenliste/{classId}")
	@ResponseBody
	public List<IllnessConfirmationDto> getClassList(@PathVariable("classId") String classId) {
		
		List<IllnessConfirmationDto> classList = schoolClassService.getAllIllnessConfirmation(classId);
		
		return classList;
	}
	
	// Erstellt einen neuen Anwesenheitseintrag für einen bestimmten Schüler
	@PostMapping("/lehrender/klassenliste/abwesenheiteintragen")
	@ResponseBody
	public PresenceDto addAbsence(@RequestBody Map<String,String> presenceData) throws java.text.ParseException {
		
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 java.util.Date date= null;
		 
		date = format.parse(presenceData.get("date"));
		    
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		Presence newAbsence = new Presence(Integer.valueOf(presenceData.get("userId")), sqlDate, Boolean.valueOf(presenceData.get("presence")), Boolean.valueOf(presenceData.get("confirmation")), Integer.valueOf(presenceData.get("lesson")));
		presenceRepo.save(newAbsence);
		
		PresenceDto response = presenceService.convertToPresenceDto(newAbsence);
		
		return response;
	}

}
