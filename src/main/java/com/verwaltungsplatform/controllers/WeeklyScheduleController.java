package com.verwaltungsplatform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.verwaltungsplatform.dto.LessonDto;
import com.verwaltungsplatform.model.Lesson;
import com.verwaltungsplatform.repositories.LessonRepository;
import com.verwaltungsplatform.service.WeeklyScheduleService;

@Controller
public class WeeklyScheduleController {
	
	@Autowired
	private WeeklyScheduleService weeklySchedule;
	
	@Autowired
	private LessonRepository lesson;
	
	// Gibt einem Schüler seinen eigenen Wochenplan aus
	@GetMapping("/lernender/wochenplan/{studentId}")
	@ResponseBody
	public List<LessonDto> getWeeklyScheduleStudent(@PathVariable("studentId") int studentId) {
		
		List<LessonDto> schedule = weeklySchedule.getWeeklyScheduleStudent(studentId);
		
		return schedule;
		
	}
	
	// Gibt einem Lehrer seinen eigenen Wochenplan aus
	@GetMapping("/lehrender/wochenplan/{teacherId}")
	@ResponseBody
	public List<LessonDto> getWeeklyScheduleTeacher(@PathVariable("teacherId") int teacherId) {
		
		List<LessonDto> schedule = weeklySchedule.getWeeklyScheduleTeacher(teacherId);
		
		return schedule;
		
	}
	
	// Gibt dem Sekretariat den Wochenplan einer bestimmten Klasse aus
	// Benötigt eine Fehlermeldung, wenn die eingegebene Klasse nicht existiert
	@GetMapping("/sekretariat/wochenplan/klasse/{classId}")
	@ResponseBody
	public List<Lesson> getSecretariatWeeklyScheduleClass(@PathVariable("classId") String classId) {
		
		List<Lesson> schedule = lesson.findWeeklyScheduleByIdKlasse(classId);
		
		return schedule;
		
	}
	
	//  Gibt dem Sekretariat den Wochenplan eines bestimmten Lehrers aus
	// Benötigt noch eine Fehlermeldung, wenn die eingegebene Id kein Teacher ist
	@GetMapping("/sekretariat/wochenplan/{teacherId}")
	@ResponseBody
	public List<LessonDto> getSecretariatWeeklyScheduleTeacher(@PathVariable("teacherId") int teacherId) {
		
		List<LessonDto> schedule = weeklySchedule.getWeeklyScheduleTeacher(teacherId);
		
		return schedule;
	}
	

}
