package com.verwaltungsplatform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verwaltungsplatform.dto.GettingGradesDto;
import com.verwaltungsplatform.dto.GivingGradesDto;
import com.verwaltungsplatform.dto.GradingSchemeDto;
import com.verwaltungsplatform.service.GradesService;

@Controller
public class GradeController {
	
	@Autowired
	GradesService gradesService;
	
	// Ein Lehrer trägt einem Schüler eine neue Note ein
	@PostMapping("/lehrender/noten/eintragen")
	@ResponseBody
	public String addGrade(int affectedUserId, int examId, int grade) {
		
		GivingGradesDto newGrade = new GivingGradesDto(affectedUserId, examId, grade);
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
	public String addGradingScheme(String classId, int teacherId, double writtenEvaluation, double oralEvaluation, int writtenNumber, int oralNumber) {
		
		GradingSchemeDto scheme = new GradingSchemeDto(classId, teacherId, writtenEvaluation, oralEvaluation, writtenNumber, oralNumber);
		gradesService.saveNewGradingScheme(scheme);
		
		String response = "Das Notenschema wurde angelegt.";
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
