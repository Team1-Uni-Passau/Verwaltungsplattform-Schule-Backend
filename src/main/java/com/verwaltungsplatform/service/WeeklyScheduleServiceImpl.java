package com.verwaltungsplatform.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.repositories.AppointmentRepository;
import com.verwaltungsplatform.repositories.HourRepository;
import com.verwaltungsplatform.repositories.LessonRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.dto.FamilyDto;
import com.verwaltungsplatform.dto.LessonDto;
import com.verwaltungsplatform.dto.SavingLessonDto;
import com.verwaltungsplatform.model.Appointment;
import com.verwaltungsplatform.model.Hour;
import com.verwaltungsplatform.model.Lesson;
import com.verwaltungsplatform.model.SchoolClass;


@Service
public class WeeklyScheduleServiceImpl implements WeeklyScheduleService {

	
	
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private FamilyServiceImpl familyServiceImpl;
	@Autowired
	private HourRepository hourRepository;
	
	
	//saves new lesson
		public Lesson saveLesson(int teacherId, String day, String startTime, String subject, String classId) {
			int hour = hourRepository.findIdHour(startTime);
			int appointment = appointmentRepository.findId(day, hour);
			Lesson lesson = new Lesson(appointment, classId, teacherId, subject);
			

			// Saves the notification entity in the database
			lessonRepository.save(lesson);
			return lesson;
		}
	
	//@param teacher-Id
	/*@return List with all lessons
	 * method for student/parent and teacher separate
	 */
	public List<LessonDto> getWeeklyScheduleTeacher(int teacherId) {
		return ((List<Lesson>) lessonRepository
				.findWeeklyScheduleByIdLehrender(teacherId))
				.stream()
				.map(this::convertToLessonDto).collect(Collectors.toList());
		
	}
	
	/*@param user-Id if user is student or parent
	 * @return List with all lessons
	 */
	public List<LessonDto> getWeeklyScheduleStudent(int userId) {
		String klassenId;
		if (schoolClassRepository.existsById(userId)) {
			SchoolClass schoolClass = schoolClassRepository.getOne(userId);
			klassenId = schoolClass.getName();
		}
		else {
			FamilyDto familyDto = familyServiceImpl.getFamilyDto(userId);
			klassenId = familyDto.getClassId();
		}
		return ((List<Lesson>) lessonRepository
				.findWeeklyScheduleByIdKlasse(klassenId))
				.stream()
				.map(this::convertToLessonDto).collect(Collectors.toList());
		
	}
	
	public List<LessonDto> findWeeklyScheduleByIdKlasse(String classId) {
		return ((List<Lesson>) lessonRepository
				.findWeeklyScheduleByIdKlasse(classId))
				.stream()
				.map(this::convertToLessonDto).collect(Collectors.toList());
		
	}

	
	//method supports getWeeklyScheduleTeacher and getWeeklyScheduleStudent
	public LessonDto convertToLessonDto(Lesson lesson) {
		LessonDto lessonDto = new LessonDto();
		int lessonid = lesson.getAppointment();
		Appointment appointment = appointmentRepository.findById(lessonid);
		lessonDto.setAffectedUserId(lesson.getTeacherId());
		lessonDto.setLessonId(lesson.getId());
		lessonDto.setDay(appointment.getWeekday());
		lessonDto.setHour(appointment.getHour());
		Hour hour = hourRepository.findHour(appointment.getHour());
		lessonDto.setStartTime(hour.getStartTime());
		lessonDto.setEndTime(hour.getEndTime());
		lessonDto.setSubject(lesson.getSubject());
		lessonDto.setClassid(lesson.getClassId());		
		return lessonDto;
	}
 	
	
	
}
