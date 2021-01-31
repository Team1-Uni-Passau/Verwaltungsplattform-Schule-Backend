package com.verwaltungsplatform.service;


import java.sql.Date;
import java.util.Calendar;
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
			Date date = java.sql.Date.valueOf(examDto.getDate());
			String weekday = getWeekDay(date);
			int appointment = appointmentRepository.findId(weekday, examDto.getHour());
			Exam exam = new Exam(examDto.getUserId(), examDto.getClassId(), appointment, date, examDto.getType());
		
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
		examDto.setExamId(exam.getId());
		examDto.setUserId(exam.getTeacherId());
		String date = exam.getDate().toString();
		examDto.setDate(date);
		Appointment appointment = appointmentRepository.findById(exam.getAppointment());
		examDto.setDay(appointment.getWeekday());
		examDto.setHour(appointment.getHour());
		String subject = lessonRepository.getSubject(exam.getClassId(), exam.getAppointment());
		examDto.setSubject(subject);
		examDto.setClassId(exam.getClassId());
		examDto.setType(exam.getType());

		return examDto;
	}
	
	//@return one examDto
	public ExamDto getOneExam(int examId) {
		Exam exam = examsRepository.getOneById(examId);
		Appointment appointment = appointmentRepository.findById(exam.getAppointment());
		String day = appointment.getWeekday();
		int hour = appointment.getHour();
		String subject = lessonRepository.getSubject(exam.getClassId(), exam.getAppointment());
		String date = exam.getDate().toString();
		ExamDto examDto = new ExamDto(exam.getId(), exam.getTeacherId(), date, day, hour, subject, exam.getClassId(), exam.getType());
	
		return examDto;
	}

	//updates an exam
	public Exam editExam(int examId, Date date, String day, int hour, String classId, String type) {
		Exam exam =examsRepository.getOneById(examId);
		exam.setDate(date);
		int appointment = appointmentRepository.findId(day, hour);
		exam.setAppointment(appointment);
		exam.setClassId(classId);
		exam.setType(type);
		
		examsRepository.save(exam);
		return exam;
	}
 	
	//returns String weekday for date
		private String getWeekDay(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			String weekday = "";
			
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
			return weekday;
		}
	    
}


