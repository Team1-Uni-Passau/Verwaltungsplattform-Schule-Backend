package com.verwaltungsplatform.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.verwaltungsplatform.dto.LessonDto;
import com.verwaltungsplatform.model.Lesson;
import com.verwaltungsplatform.repositories.AppointmentRepository;
import com.verwaltungsplatform.repositories.HourRepository;
import com.verwaltungsplatform.repositories.LessonRepository;
import com.verwaltungsplatform.service.WeeklyScheduleService;

@Controller
public class WeeklyScheduleController {
	
	@Autowired
	private WeeklyScheduleService weeklySchedule;
	
	@Autowired
	private LessonRepository lessonRepo;
	
	@Autowired
	private HourRepository hourRepo;
	
	@Autowired
	private AppointmentRepository appointmentRepo;
	
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
	@GetMapping("/sekretariat/wochenplan/klasse/{classId}")
	@ResponseBody
	public List<LessonDto> getSecretariatWeeklyScheduleClass(@PathVariable("classId") String classId) {
		
		List<LessonDto> schedule = weeklySchedule.findWeeklyScheduleByIdKlasse(classId);
		
		return schedule;
		
	}
	
	//  Gibt dem Sekretariat den Wochenplan eines bestimmten Lehrers aus
	@GetMapping("/sekretariat/wochenplan/{teacherId}")
	@ResponseBody
	public List<LessonDto> getSecretariatWeeklyScheduleTeacher(@PathVariable("teacherId") int teacherId) {
		
		List<LessonDto> schedule = weeklySchedule.getWeeklyScheduleTeacher(teacherId);
		
		return schedule;
	}
	
	// Gibt einem Elternteil den Wochenplan eines bestimmten Kindes aus
	@GetMapping("/eltern/wochenplan/{studentId}")
	@ResponseBody
	public List<LessonDto> getWeeklyScheduleChild(@PathVariable("studentId") int studentId) {
		
		List<LessonDto> schedule = weeklySchedule.getWeeklyScheduleStudent(studentId);
		
		return schedule;
		
	}
	
	// Erstellt eine neue Unterrichtsstunde
	@PostMapping("/sekretariat/wochenplan/neuestunde")
	@ResponseBody
	public LessonDto addLessonClass(@RequestBody Map<String,String> newHourData) {
		int teacherId = Integer.valueOf(newHourData.get("teacherId"));
		String day = newHourData.get("day");
		String subject = newHourData.get("subject");
		System.out.println(newHourData.get("startTime"));
		String startTime = newHourData.get("startTime")+":00";
		String classId = newHourData.get("classId");

		Lesson lesson = weeklySchedule.saveLesson(teacherId, day, startTime, subject, classId);
		LessonDto lessonDto = weeklySchedule.convertToLessonDto(lesson);
		
		return lessonDto;
	}
	
	// Gibt dem Sekretariat eine bestimmte Unterrichtsstunde aus
	@GetMapping("/sekretariat/wochenplan/stunde/{lessonId}")
	@ResponseBody
	public Lesson getOneLesson(@PathVariable("lessonId") int lessonId) {
		
		Lesson lesson = lessonRepo.findById(lessonId);
		
		return lesson;
	}
	
	// Ändert den Termin einer bestimmten Unterrichtsstunde
	@PutMapping("/sekretariat/wochenplan/stunde/{lessonId}/termin")
	@ResponseBody
	public Lesson changeAppointment(@PathVariable("lessonId") int lessonId, String day, String startTime) {
		
		Lesson lesson = lessonRepo.findById(lessonId);
		int hour = hourRepo.findIdHour(startTime);
		int appointment = appointmentRepo.findId(day, hour);
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
	public Lesson editLesson(@PathVariable("lessonId") int lessonId, String day, String startTime, String classId, int teacherId, String subject) {
		
		Lesson lesson = lessonRepo.findById(lessonId);
		int hour = hourRepo.findIdHour(startTime);
		int appointment = appointmentRepo.findId(day, hour);
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
