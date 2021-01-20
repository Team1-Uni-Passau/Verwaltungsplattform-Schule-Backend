package com.verwaltungsplatform.service;

import java.util.Date;
import java.util.List;

import com.verwaltungsplatform.dto.IllnessDto;

public interface IllnessNotificationService {
	
	void saveIllnessNotification (IllnessDto illnessDto);
	IllnessDto createIllnessNotification(int userId);
	IllnessDto createIllnessNotificationParent(int userId);
	List<IllnessDto> getAllIllnessDay();
	List<String> getEmailsByTeacher(int teacherId);

}
