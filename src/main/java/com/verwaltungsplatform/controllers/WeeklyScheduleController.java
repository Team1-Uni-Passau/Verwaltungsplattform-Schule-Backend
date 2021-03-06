package com.verwaltungsplatform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private LessonRepository lessonRepo;
	
	// Null return value from advice does not match primitive return type for: public abstract int com.verwaltungsplatform.repositories.FamilyRepository.findByUserId(int)
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
		
		List<Lesson> schedule = lessonRepo.findWeeklyScheduleByIdKlasse(classId);
		
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
	
	// Null return value from advice does not match primitive return type for: public abstract int com.verwaltungsplatform.repositories.FamilyRepository.findByUserId(int)
	// Gibt einem Elternteil den Wochenplan eines bestimmten Kindes aus
	// Benötigt noch eine Fehlermeldung, wenn die eingegebene Id keinem Familienmitglied gehört
	@GetMapping("/eltern/wochenplan/{studentId}")
	@ResponseBody
	public List<LessonDto> getWeeklyScheduleChild(@PathVariable("studentId") int studentId) {
		
		List<LessonDto> schedule = weeklySchedule.getWeeklyScheduleStudent(studentId);
		System.out.println(schedule);
		return schedule;
		
	}
	
	@GetMapping("/hello")
	public ResponseEntity test () {
		System.out.println("hi");
	    return new ResponseEntity<>(
	    	      "SERVUS", HttpStatus.CONFLICT);
	}

	// Erstellt eine neue Unterrichtsstunde
	@PostMapping("/sekretariat/wochenplan/neuestunde")
	@ResponseBody
	public Lesson addLessonClass(Lesson lesson) {
		
		lessonRepo.save(lesson);
		
		return lesson;
	}
	
	// Gibt dem Sekretariat eine bestimmte Unterrichtsstunde aus
	// Benötigt noch eine Fehlermeldung, wenn die Unterrichtsstunde nicht existiert
	@GetMapping("/sekretariat/wochenplan/stunde/{lessonId}")
	@ResponseBody
	public Lesson getOneLesson(@PathVariable("lessonId") int lessonId) {
		
		Lesson lesson = lessonRepo.findById(lessonId);
		
		return lesson;
	}
	
	// Ändert den Termin einer bestimmten Unterrichtsstunde
	@PutMapping("/sekretariat/wochenplan/stunde/{lessonId}/termin")
	@ResponseBody
	public Lesson changeAppointment(@PathVariable("lessonId") int lessonId, int appointment) {
		
		Lesson lesson = lessonRepo.findById(lessonId);
		lesson.setAppointment(appointment);
		lessonRepo.save(lesson);
		
		return lesson;
		
	}
	
	// Ändert die Klasse einer bestimmten Unterrichtsstunde
	@PutMapping("/sekretariat/wochenplan/stunde/{lessonId}/klasse")
	@ResponseBody
	public Lesson changeClass(@PathVariable("lessonId") int lessonId, String classId) {
		
		Lesson lesson = lessonRepo.findById(lessonId);
		lesson.setClassId(classId);
		lessonRepo.save(lesson);
		
		return lesson;
	}
	
	// Ändert den Lehrer einer bestimmten Unterrichtsstunde
	@PutMapping("/sekretariat/wochenplan/stunde/{lessonId}/lehrender")
	@ResponseBody
	public Lesson changeTeacher(@PathVariable("lessonId") int lessonId, int teacherId) {
		
		Lesson lesson = lessonRepo.findById(lessonId);
		lesson.setTeacherId(teacherId);
		lessonRepo.save(lesson);
		
		return lesson;
		
	}
	
	// Ändert das Fach einer bestimmten Unterrichtsstunde
	@PutMapping("/sekretariat/wochenplan/stunde/{lessonId}/fach")
	@ResponseBody
	public Lesson changeSubject(@PathVariable("lessonId") int lessonId, String subject) {
		
		Lesson lesson = lessonRepo.findById(lessonId);
		lesson.setSubject(subject);
		lessonRepo.save(lesson);
		
		return lesson;
	}
	
	// Ändert alle Attribute einer bestimmten Unterrichtsstunde
	@PutMapping("/sekretariat/wochenplan/stunde/edit/{lessonId}")
	@ResponseBody
	public Lesson editLesson(@PathVariable("lessonId") int lessonId, int appointment, String classId, int teacherId, String subject) {
		
		Lesson lesson = lessonRepo.findById(lessonId);
		lesson.setAppointment(appointment);
		lesson.setClassId(classId);
		lesson.setTeacherId(teacherId);
		lesson.setSubject(subject);
		lessonRepo.save(lesson);
		
		return lesson;
	}
	
	// Löscht eine bestimmte Unterrichtsstunde
	@DeleteMapping("/sekretariat/wochenplan/stunde/{lessonId}/loeschen")
	@ResponseBody
	public void deleteLesson(@PathVariable("lessonId") int lessonId) {
		
		Lesson lesson = lessonRepo.findById(lessonId);
		lessonRepo.delete(lesson);
	}
	
	
	

}
