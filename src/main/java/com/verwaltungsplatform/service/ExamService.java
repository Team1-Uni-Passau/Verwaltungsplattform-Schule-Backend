package com.verwaltungsplatform.service;

import java.sql.Date;
import java.util.List;

import com.verwaltungsplatform.dto.ExamDto;
import com.verwaltungsplatform.model.Exam;

public interface ExamService {
	
//	void saveNewExam (ExamDto examDto);
	List<ExamDto> getAllExamsDto(int userId);
	ExamDto getOneExam(int examId);
//	Exam editExam(int examId, Date date, String day, int hour, String classId, String type);
}
