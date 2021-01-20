package com.verwaltungsplatform.service;

import java.util.List;

import com.verwaltungsplatform.dto.LessonDto;
import com.verwaltungsplatform.dto.SavingLessonDto;
import com.verwaltungsplatform.model.Lesson;

public interface WeeklyScheduleService {    
	
	List<LessonDto> getWeeklyScheduleTeacher(int teacherId);
	List<LessonDto> getWeeklyScheduleStudent(int userId);
	List<LessonDto> findWeeklyScheduleByIdKlasse(String classId);
	Lesson saveLesson(int teacherId, String day, String startTime, String subject, String classId);
	LessonDto convertToLessonDto(Lesson lesson);
}

