package com.verwaltungsplatform.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verwaltungsplatform.dto.GettingGradesDto;
import com.verwaltungsplatform.dto.GivingGradesDto;
import com.verwaltungsplatform.dto.GradingSchemeDto;
import com.verwaltungsplatform.repositories.GradingSchemeRepository;
import com.verwaltungsplatform.service.GradesService;

@Controller
public class GradeController {
	
	@Autowired
	GradesService gradesService;
	
	@Autowired
	GradingSchemeRepository GradingSchemeRepo;
	
	// Ein Lehrer trägt einem Schüler eine neue Note ein
	@PostMapping("/lehrender/noten/eintragen")
	@ResponseBody
	public String addGrade(@RequestBody Map<String,String> gradeData) throws java.text.ParseException {
		
		GivingGradesDto newGrade = new GivingGradesDto(Integer.valueOf(gradeData.get("userId")), Integer.valueOf(gradeData.get("examId")), Integer.valueOf(gradeData.get("grade")));
		gradesService.saveNewGrade(newGrade);
		
		String response = "Die Note wurde eingetragen.";
		return response;
	}
	
	// Gibt einem Schüler die eigenen Noten aus
	@GetMapping("/lernender/noten/{studentId}")
	@ResponseBody
	public List<GettingGradesDto> getGradesStudent(@PathVariable("studentId") int studentId) {
		
		return gradesService.getAllGradesDto(studentId);
	}
	
	// Gibt einem Elternteil die Noten ihres Kindes aus
	@GetMapping("/eltern/noten/{parentId}")
	@ResponseBody
	public List<GettingGradesDto> getGradesChild(@PathVariable("parentId") int parentId) {
		
		return gradesService.getAllGradesDto(parentId);
	}
	
	// Ein Lehrer legt ein klassenspezifisches Notenschema fest
	@PostMapping("/lehrender/noten/neuesnotenschema")
	@ResponseBody
	public String addGradingScheme(@RequestBody Map<String,String> gradingSchemeData) throws java.text.ParseException {
		
		String response;
		
		if (GradingSchemeRepo.getClassGradingScheme(gradingSchemeData.get("classId"),  Integer.valueOf(gradingSchemeData.get("teacherId"))) == null) {
			GradingSchemeDto scheme = new GradingSchemeDto(gradingSchemeData.get("classId"), Integer.valueOf(gradingSchemeData.get("teacherId")), 
				Double.valueOf(gradingSchemeData.get("writtenEvaluation")), Double.valueOf(gradingSchemeData.get("oralEvaluation")), 
				Integer.valueOf(gradingSchemeData.get("writtenNumber")), Integer.valueOf(gradingSchemeData.get("oralNumber")));
			gradesService.saveNewGradingScheme(scheme);
			
			response = "Das Notenschema wurde angelegt.";
		}
		else {
			response = "Es existiert bereits ein Notenschema.";
		}
		
		return response;
	}
	
	// Gibt einem Schüler alle Notenschemata seiner Klasse aus
	@GetMapping("/lernender/noten/notenschema/{studentId}")
	@ResponseBody
	public List<GradingSchemeDto> getGradingSchemeStudent(@PathVariable("studentId") int studentId) {
		
		return gradesService.getAllGradeSchemesDto(studentId);
	}
	
	// Gibt einem Elternteil alle Notenschemata der Klasse seines Kindes aus
	@GetMapping("/eltern/noten/notenschema/{parentId}")
	@ResponseBody
	public List<GradingSchemeDto> getGradingSchemeChild(@PathVariable("parentId") int parentId) {
		
		return gradesService.getAllGradeSchemesDto(parentId);
	}

}
