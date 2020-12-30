package com.verwaltungsplatform.service;

import java.util.List;

import com.verwaltungsplatform.dto.LessonDto;
import com.verwaltungsplatform.dto.SavingLessonDto;

public interface WeeklyScheduleService {    
	
	void saveLesson (SavingLessonDto savingLessonDto);
	List<LessonDto> getWeeklyScheduleTeacher(int teacherId);
	List<LessonDto> getWeeklyScheduleStudent(int userId);
 
	
}

