package com.verwaltungsplatform.service;

import java.util.List;

import com.verwaltungsplatform.dto.GettingGradesDto;
import com.verwaltungsplatform.dto.GivingGradesDto;
import com.verwaltungsplatform.dto.GradingSchemeDto;

public interface GradesService {    
	void saveNewGrade (GivingGradesDto givingGradesDto);
	void saveNewGradingScheme (GradingSchemeDto gradingSchemeDto);
	List<GettingGradesDto> getAllGradesDto(int userId);
	List<GradingSchemeDto> getAllGradeSchemesDto(int userId);
	
	
}

