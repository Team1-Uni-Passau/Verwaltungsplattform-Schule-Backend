package com.verwaltungsplatform.service;

import java.util.List;

import com.verwaltungsplatform.dto.PresenceDto;

public interface PresenceService {
	
	void savePresenceEntry (PresenceDto presenceDto);
	List<PresenceDto> getPresenceEntriesLastWeek(int userId);

}
