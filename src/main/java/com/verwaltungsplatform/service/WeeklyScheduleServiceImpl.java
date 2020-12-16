//package com.verwaltungsplatform.service;
//
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.verwaltungsplatform.repositories.AppointmentRepository;
//import com.verwaltungsplatform.repositories.SchoolClassRepository;
//import com.verwaltungsplatform.repositories.WeeklyScheduleRepository;
//import com.verwaltungsplatform.dto.LessonDto;
//import com.verwaltungsplatform.model.Appointment;
//import com.verwaltungsplatform.model.Lesson;
//
//
//@Service
//public class WeeklyScheduleServiceImpl implements WeeklyScheduleService {
//
//	
//	
//	@Autowired
//	private WeeklyScheduleRepository weeklyScheduleRepository;
//	@Autowired
//	private SchoolClassRepository schoolClassRepository;
//	@Autowired
//	private AppointmentRepository appointmentRepository;
//	
//	public List<LessonDto> getWeeklyScheduleTeacher(int lehrenderId) {
//		return ((List<Lesson>) weeklyScheduleRepository
//				.findByIdLehrender(lehrenderId))
//				.stream()
//				.map(this::convertToLessonDto).collect(Collectors.toList());
//		
//	}
//	
//	public List<LessonDto> getWeeklyScheduleStudent(int schuelerId) {
//		String klassenId = schoolClassRepository.findBySchuelerId(schuelerId);
//		return ((List<Lesson>) weeklyScheduleRepository
//				.findByIdKlasse(klassenId))
//				.stream()
//				.map(this::convertToLessonDto).collect(Collectors.toList());
//		
//	}
//	
//
//	
//	//@param Lesson
//	/*@return LessonDto with day, hour, subject, classid
//	 */
//	private LessonDto convertToLessonDto(Lesson lesson) {
//		LessonDto lessonDto = new LessonDto();
//		int lessonid = lesson.getId();
//		Appointment appointment = appointmentRepository.getOne(lessonid);
//		lessonDto.setDay(appointment.getWeekday());
//		lessonDto.setHour(appointment.getHour());
//		lessonDto.setSubject(lesson.getSubject());
//		lessonDto.setClassid(lesson.getClassid());		
//		return lessonDto;
//	}
// 	
//	
//	
//}
