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
//import com.verwaltungsplatform.repositories.LessonRepository;
//import com.verwaltungsplatform.repositories.SchoolClassRepository;
//import com.verwaltungsplatform.repositories.WeeklyScheduleRepository;
//import com.verwaltungsplatform.dto.FamilyDto;
//import com.verwaltungsplatform.dto.LessonDto;
//import com.verwaltungsplatform.dto.NotificationDto;
//import com.verwaltungsplatform.dto.SavingLessonDto;
//import com.verwaltungsplatform.model.Appointment;
//import com.verwaltungsplatform.model.Lesson;
//import com.verwaltungsplatform.model.Notification;
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
//	@Autowired
//	private LessonRepository lessonRepository;
//	@Autowired
//	private FamilyServiceImpl familyServiceImpl;
//	
//	
//	//saves new lesson
//		public void saveLesson (SavingLessonDto savingLessonDto) {
//			int appointment = appointmentRepository.findAppointment(savingLessonDto.getDay(), savingLessonDto.getHour());
//			Lesson lesson = new Lesson(appointment, savingLessonDto.getClassId(), savingLessonDto.getTeacherId(), savingLessonDto.getSubject());
//			
//
//			// Saves the notification entity in the database
//			lessonRepository.save(lesson);
//		}
//	
//	//@param teacher-Id
//	/*@return List with all lessons
//	 * method for student/parent and teacher separate
//	 */
//	public List<LessonDto> getWeeklyScheduleTeacher(int teacherId) {
//		return ((List<Lesson>) weeklyScheduleRepository
//				.findByIdLehrender(teacherId))
//				.stream()
//				.map(this::convertToLessonDto).collect(Collectors.toList());
//		
//	}
//	
//	/*@param user-Id if user is student or parent
//	 * @return List with all lessons
//	 */
//	public List<LessonDto> getWeeklyScheduleStudent(int userId) {
//		String klassenId;
//		if (schoolClassRepository.existsById(userId)) {
//			klassenId = schoolClassRepository.findBySchuelerId(userId);
//		}
//		else {
//			FamilyDto familyDto = new FamilyDto();
//			familyDto = familyServiceImpl.getFamilyDto(userId);
//			klassenId = familyDto.getClassId();
//		}
//		return ((List<Lesson>) weeklyScheduleRepository
//				.findByIdKlasse(klassenId))
//				.stream()
//				.map(this::convertToLessonDto).collect(Collectors.toList());
//		
//	}
//
//	
//	//method supports getWeeklyScheduleTeacher and getWeeklyScheduleStudent
//	private LessonDto convertToLessonDto(Lesson lesson) {
//		LessonDto lessonDto = new LessonDto();
//		int lessonid = lesson.getId();
//		Appointment appointment = appointmentRepository.getOne(lessonid);
//		lessonDto.setDay(appointment.getWeekday());
//		lessonDto.setHour(appointment.getHour());
//		lessonDto.setSubject(lesson.getSubject());
//		lessonDto.setClassid(lesson.getClassId());		
//		return lessonDto;
//	}
// 	
//	
//	
//}
