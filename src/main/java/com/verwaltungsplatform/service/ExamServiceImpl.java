package com.verwaltungsplatform.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.repositories.AppointmentRepository;
import com.verwaltungsplatform.repositories.ExamsRepository;
import com.verwaltungsplatform.repositories.GradesRepository;
import com.verwaltungsplatform.repositories.GradingSchemeRepository;
import com.verwaltungsplatform.repositories.LessonRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.dto.FamilyDto;
import com.verwaltungsplatform.dto.GettingGradesDto;
import com.verwaltungsplatform.dto.GivingGradesDto;
import com.verwaltungsplatform.dto.GradingSchemeDto;
import com.verwaltungsplatform.dto.ExamDto;
import com.verwaltungsplatform.model.Appointment;
import com.verwaltungsplatform.model.Exam;
import com.verwaltungsplatform.model.Grades;
import com.verwaltungsplatform.model.GradingScheme;


@Service
public class ExamServiceImpl implements ExamService {

	
	@Autowired
	private ExamsRepository examsRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private LessonRepository lessonRepository;

	
	
	//saves new exam
		public void saveNewExam (ExamDto examDto) {
			int appointment = appointmentRepository.findAppointment(examDto.getDay(), examDto.getHour());
			Exam exam = new Exam(examDto.getUserId(), examDto.getClassId(), appointment, examDto.getDate(), examDto.getType());
		
			examsRepository.save(exam);
		}
		

		
	//@param userId of a teacher
	//@return List of all future (or today) exams with Date, day, hour, subject, classId, type
		public List<ExamDto> getAllExamsDto(int userId) {			
		return ((List<Exam>) examsRepository
				.getAllExam(userId))
				.stream()
				.map(this::convertToExamDto).collect(Collectors.toList());
		
	}
	//method for getAllGradesDto
	private ExamDto convertToExamDto(Exam exam) {
		ExamDto examDto = new ExamDto();
		examDto.setUserId(exam.getTeacherId());
		examDto.setDate(exam.getDate());
		Appointment appointment = appointmentRepository.findById(exam.getAppointment());
		examDto.setDay(appointment.getWeekday());
		examDto.setHour(appointment.getHour());
		String subject = lessonRepository.getSubject(exam.getClassId(), exam.getAppointment());
		examDto.setSubject(subject);
		examDto.setClassId(exam.getClassId());
		examDto.setType(exam.getType());

		return examDto;
	}
}
 	
	








