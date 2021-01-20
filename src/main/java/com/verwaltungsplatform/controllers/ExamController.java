package com.verwaltungsplatform.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verwaltungsplatform.dto.ExamDto;
import com.verwaltungsplatform.model.Appointment;
import com.verwaltungsplatform.model.Exam;
import com.verwaltungsplatform.repositories.ExamsRepository;
import com.verwaltungsplatform.service.ExamService;

@Controller
public class ExamController {
	
	// „Lehrende“ können Prüfungstermine verwalten
	
	@Autowired
	private ExamsRepository examRepo;
	
	@Autowired
	private ExamService examService;
	
	
	// Zeigt einem Lehrenden alle seine Prüfungen an
	@GetMapping("/lehrender/pruefungen/{teacherId}")
	@ResponseBody
	public List<ExamDto> getExams(@PathVariable("teacherId") int teacherId) {
		
		List<ExamDto> exams = examService.getAllExamsDto(teacherId);
		
		return exams;
	}
	
	// Fügt einem Lehrenden eine neue Prüfung hinzu
	@PostMapping("/lehrender/neuepruefung")
	@ResponseBody
	public ExamDto addExam(@RequestBody Map<String,String> ExamData) {
		
		ExamDto newExam = new ExamDto();
		
		newExam.setClassId(ExamData.get("classId"));
		newExam.setSubject(ExamData.get("subject"));
		newExam.setHour(Integer.valueOf(ExamData.get("hour")));
		newExam.setUserId(Integer.valueOf(ExamData.get("teacherId")));
		newExam.setDate(ExamData.get("date"));
		newExam.setType(ExamData.get("type"));

		examService.saveNewExam(newExam);
		return newExam;
	}
	
//	// getOneExam() Gibt durch die Übergabe der examId die ExamDto der jeweiligen Prüfung aus
//	// Gibt einem Lehrenden eine bestimmte Prüfung aus
	@GetMapping("/lehrender/pruefung/{examId}")
	@ResponseBody
	public ExamDto getOneExam(@PathVariable("examId") int examId) {
		
		ExamDto exam = examService.getOneExam(examId);
		return exam;
	}
	
//	// editExam() sucht mithilfe der examId die jeweilige Prüfung, aktualisiert die ExamDto und überschreibt die alten Werte der Prüfung
//	// Editiert eine bestimmte Prüfung
	//Problem: ich weiß nicht, wie subject geändert werden kann, weil es nicht in exam gespeichert ist, sondern durch Stunde und Wochentag gesucht wird
	@PutMapping("/lehrender/pruefung/edit/{examId}")
	@ResponseBody
	public ExamDto editExam(@PathVariable("examId") int examId, Date date, String day, int hour, String classId, String type) {
		
		Exam editExam = examService.editExam(examId, date, day, hour, classId, type);
		ExamDto exam = examService.getOneExam(examId);
		
		return exam;
	}
	
	// Löscht eine bestimmte Prüfung
	@DeleteMapping("/lehrender/pruefung/delete/{examId}")
	@ResponseBody
	public String deleteExam(@PathVariable("examId") int examId) {
		
		Exam exam = examRepo.getOne(examId);
		examRepo.delete(exam);
		String response = "Die Prüfung wurde gelöscht.";
		
		return response;
	}

}
