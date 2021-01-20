package com.verwaltungsplatform.service;

import java.util.List;
import com.verwaltungsplatform.dto.IllnessConfirmationDto;


public interface SchoolClassService {    
	List<IllnessConfirmationDto> getAllIllnessConfirmation(String klassenid); 
	
}

