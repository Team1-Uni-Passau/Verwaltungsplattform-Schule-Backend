package com.verwaltungsplatform.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.repositories.AppointmentRepository;
import com.verwaltungsplatform.repositories.FamilyRepository;
import com.verwaltungsplatform.repositories.IllnessNotificationRepository;
import com.verwaltungsplatform.repositories.LessonRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.repositories.UserRepository;
import com.verwaltungsplatform.dto.FamilyDto;
import com.verwaltungsplatform.dto.IllnessDto;
import com.verwaltungsplatform.model.IllnessNotification;
import com.verwaltungsplatform.model.User;



@Service
public class IllnessNotificationServiceImpl implements IllnessNotificationService {

	
	
	@Autowired
	private IllnessNotificationRepository illnessNotificationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private FamilyRepository familyRepository;
	@Autowired
	private FamilyServiceImpl familyServiceImpl;
	
	
	//saves new illness notification with illnessDto and date
		public void saveIllnessNotification (IllnessDto illnessDto, Date date) {
		
	        //Creates a new notification entity
			IllnessNotification illnessNotification = new IllnessNotification(illnessDto.getAffectedUserId(), 
					date);
			

			// Saves the notification entity in the database
			illnessNotificationRepository.save(illnessNotification);
		}
	
	//create a new illnessDto with userId of teacher or parent
		public IllnessDto createIllnessNotification(int userId) {
			
			IllnessDto illnessDto = new IllnessDto(userId);
			User user = userRepository.getOne(userId);
			illnessDto.setFirstName(user.getFirstName());
			illnessDto.setLastName(user.getLastName());
			illnessDto.setRolle(user.getRoleRegisterCodeMapper().getRole());
			return illnessDto;
		}
		
		public IllnessDto createIllnessNotificationParent(int parentId) {

			FamilyDto familyDto = new FamilyDto();
			familyDto = familyServiceImpl.getFamilyDto(parentId);
			int studentId = familyDto.getStudentId();
			
			IllnessDto illnessDto = new IllnessDto(studentId);
			User user = userRepository.getOne(studentId);
			illnessDto.setFirstName(user.getFirstName());
			illnessDto.setLastName(user.getLastName());
			illnessDto.setRolle(user.getRoleRegisterCodeMapper().getRole());
			return illnessDto;
		}
		
	//@param klassen-id
	//@return List of all IllnessNotifications today with id, affectedUserId, firstName, lastName and role
	public List<IllnessDto> getAllIllnessDay() {
		return ((List<IllnessNotification>) illnessNotificationRepository
				.findAll())
				.stream()
				.map(this::convertToIllnessDto).collect(Collectors.toList());
		
	}
	
	private IllnessDto convertToIllnessDto(IllnessNotification illnessNotification) {
		IllnessDto illnessDto = new IllnessDto();
		illnessDto.setAffectedUserId(illnessNotification.getAffectedUser());
		User user = userRepository.getOne(illnessNotification.getAffectedUser());
		illnessDto.setFirstName(user.getFirstName());
		illnessDto.setLastName(user.getLastName());
		illnessDto.setRolle(user.getRoleRegisterCodeMapper().getRole());
		illnessDto.setSicknessNoteId(illnessNotification.getId());
		return illnessDto;
	}
 	
	

	
	//@param teacherId
	//@return List of e-mail addresses of parents with students having teacher today
	public List<String> getEmailsByTeacher(int teacherId) {
		
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		//String weekday = getWeekDay(date);
		String weekday = getWeekDay(date);
		List<Integer> appointments = appointmentRepository.findByDay(weekday);
		System.out.println("Zeile 1");
		List<String> classIds = lessonRepository.getClassIdByAppointmentsAndTeacherId(appointments, teacherId);
		System.out.println("Zeile 2");
		List<Integer> studentIds = schoolClassRepository.getStudentIdsByClassIds(classIds);
		System.out.println("Zeile 3");
		List<Integer> familyIds = familyRepository.findFamilyIds(studentIds);
		System.out.println("Zeile 4");
		List<Integer> parentIds = familyRepository.getUserIdByFamilyId(familyIds);
		System.out.println("Zeile 5");
		
		List<String> email = userRepository.getEmailByUserId(parentIds);
		System.out.println("Zeile 6");
	return email;
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
