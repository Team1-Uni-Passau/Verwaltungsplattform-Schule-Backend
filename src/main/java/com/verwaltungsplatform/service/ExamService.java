package com.verwaltungsplatform.service;

import java.util.List;

import com.verwaltungsplatform.dto.ExamDto;

public interface ExamService {
	
	void saveNewExam (ExamDto examDto);
	List<ExamDto> getAllExamsDto(int userId);

}
